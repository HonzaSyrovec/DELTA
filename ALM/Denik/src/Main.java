import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT);
    static final String FILE_NAME = "denik.txt";
    static final int WIDTH = 56;

    /** One-line feedback shown under the diary after the next redraw. */
    static String message = null;

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        DayList diary = new DayList();
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        while (true) {
            printScreen(diary);
            System.out.print("  Operace > ");
            if (!scanner.hasNextLine()) return;
            String command = scanner.nextLine().trim().toLowerCase();

            // command names are fixed by the assignment (Czech)
            switch (command) {
                case "predchozi" -> diary.previous();
                case "dalsi" -> diary.next();
                case "zacatek" -> diary.first();
                case "konec" -> diary.last();
                case "novy" -> newEntry(diary, scanner);
                case "smaz" -> deleteEntry(diary, scanner);
                case "uloz" -> save(diary);
                case "nacti" -> diary = load(diary);
                case "zavri" -> {
                    System.out.println("  Na shledanou!");
                    return;
                }
                default -> message = "Neznámá operace: \"" + command + "\"";
            }
        }
    }

    // ---------- drawing ----------
    static String rule(char c) {
        return String.valueOf(c).repeat(WIDTH);
    }

    static void printScreen(DayList diary) {
        Day day = diary.getCurrent();
        clearScreen();
        System.out.println(rule('═'));

        if (day == null) {
            System.out.println("  D E N Í K");
            System.out.println(rule('═'));
            System.out.println();
            System.out.println("  Deník je zatím prázdný.");
            System.out.println("  Začněte operací \"novy\".");
            System.out.println();
            System.out.println(rule('─'));
            System.out.println("  Dnů: 0   |   Záznamů v dni: 0");
        } else {
            String position = "Den " + positionOf(diary, day) + " z " + diary.getDayCount();
            System.out.println("  D E N Í K" + " ".repeat(WIDTH - 11 - position.length() - 2) + position);
            System.out.println(rule('═'));
            System.out.println("  " + dayName(day.date) + " " + day.date.format(DATE_FORMAT));
            System.out.println(rule('─'));
            for (int i = 0; i < day.entries.size(); i++) {
                String prefix = "  [" + (i + 1) + "] ";
                String indent = " ".repeat(prefix.length());
                String text = day.entries.get(i).replace("\n", "\n" + indent);
                System.out.println(prefix + text);
            }
            System.out.println(rule('─'));
            System.out.println("  Dnů: " + diary.getDayCount() + "   |   Záznamů v dni: " + day.entries.size());
        }

        System.out.println(rule('═'));
        System.out.println("  PROCHÁZENÍ   predchozi · dalsi · zacatek · konec");
        System.out.println("  ZÁZNAMY      novy · smaz");
        System.out.println("  SOUBOR       uloz · nacti");
        System.out.println("  PROGRAM      zavri");
        System.out.println(rule('─'));

        if (message != null) {
            System.out.println("  » " + message);
            message = null;
        }
    }

    /** Clears the console (ANSI escape codes); falls back to blank lines where they are not supported. */
    static void clearScreen() {
        if (System.console() != null) {
            System.out.print("\033[H\033[2J\033[3J");
            System.out.flush();
        } else {
            System.out.println("\n".repeat(40));
        }
    }

    static int positionOf(DayList diary, Day target) {
        int position = 1;
        for (Day d = diary.getHead(); d != target; d = d.next) {
            position++;
        }
        return position;
    }

    static String dayName(LocalDate date) {
        String name = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("cs-CZ"));
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    // ---------- new entry ----------
    static void newEntry(DayList diary, Scanner scanner) {
        System.out.println();
        System.out.println("  --- Nový záznam ---");
        LocalDate date;
        while (true) {
            System.out.print("  Datum (dd.MM.yyyy): ");
            if (!scanner.hasNextLine()) return;
            try {
                date = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMAT);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("  ! Chybný formát data, zkuste znovu.");
            }
        }
        System.out.println("  Text (víc řádků, ukončete prázdným řádkem):");
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine()) {
            System.out.print("  | ");
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            if (text.length() > 0) text.append('\n');
            text.append(line);
        }
        if (text.length() == 0) {
            message = "Prázdný záznam se neukládá.";
        } else if (diary.addEntry(date, text.toString())) {
            message = "Záznam přidán.";
        } else {
            message = "Zápis odmítnut: datum musí být aktuální den, nebo ležet mezi aktuálním a následujícím dnem.";
        }
    }

    // ---------- delete entry ----------
    static void deleteEntry(DayList diary, Scanner scanner) {
        Day day = diary.getCurrent();
        if (day == null) {
            message = "Není co mazat.";
            return;
        }
        System.out.println();
        System.out.print("  Číslo záznamu ke smazání: ");
        if (!scanner.hasNextLine()) return;
        int number;
        try {
            number = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            message = "To není číslo.";
            return;
        }
        if (number < 1 || number > day.entries.size()) {
            message = "Záznam s číslem " + number + " neexistuje.";
            return;
        }
        String prefix = "  [" + number + "] ";
        System.out.println(prefix + day.entries.get(number - 1).replace("\n", "\n" + " ".repeat(prefix.length())));
        System.out.print("  Opravdu smazat? (a/n): ");
        if (scanner.hasNextLine() && scanner.nextLine().trim().equalsIgnoreCase("a")) {
            diary.removeEntry(number - 1);
            message = "Záznam smazán.";
        } else {
            message = "Mazání zrušeno.";
        }
    }

    // ---------- file ----------
    // Format: "D;dd.MM.yyyy" starts a day, "E;text" is an entry (\n and \\ are escaped).
    static void save(DayList diary) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            for (Day d = diary.getHead(); d != null; d = d.next) {
                writer.println("D;" + d.date.format(DATE_FORMAT));
                for (int i = 0; i < d.entries.size(); i++) {
                    writer.println("E;" + escape(d.entries.get(i)));
                }
            }
            message = "Deník uložen do souboru " + FILE_NAME + ".";
        } catch (IOException e) {
            message = "Chyba při ukládání: " + e.getMessage();
        }
    }

    static DayList load(DayList old) {
        DayList loaded = new DayList();
        Day current = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("D;")) {
                    current = new Day(LocalDate.parse(line.substring(2), DATE_FORMAT));
                    loaded.append(current);
                } else if (line.startsWith("E;") && current != null) {
                    current.entries.add(unescape(line.substring(2)));
                } else if (!line.isEmpty()) {
                    throw new IOException("Neplatný řádek: " + line);
                }
            }
            message = "Deník načten ze souboru " + FILE_NAME + ".";
            return loaded;
        } catch (IOException | DateTimeParseException e) {
            message = "Chyba při načítání (deník zůstává beze změny): " + e.getMessage();
            return old;
        }
    }

    static String escape(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '\\') sb.append("\\\\");
            else if (c == '\n') sb.append("\\n");
            else sb.append(c);
        }
        return sb.toString();
    }

    static String unescape(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < s.length()) {
                char next = s.charAt(++i);
                sb.append(next == 'n' ? '\n' : next);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}