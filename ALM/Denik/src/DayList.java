import java.time.LocalDate;

/** Custom doubly linked list of days with a pointer to the current day. */
public class DayList {
    private Day head;
    private Day tail;
    private Day current;
    private int dayCount = 0;

    public int getDayCount() { return dayCount; }
    public Day getCurrent() { return current; }
    public Day getHead() { return head; }
    public boolean isEmpty() { return head == null; }

    // --- navigation ---
    public void previous() { if (current != null && current.previous != null) current = current.previous; }
    public void next()     { if (current != null && current.next != null) current = current.next; }
    public void first()    { current = head; }
    public void last()     { current = tail; }

    /** Adds an entry according to the assignment rules. Returns false if the entry is rejected. */
    public boolean addEntry(LocalDate date, String text) {
        if (current == null) {                        // empty diary
            Day created = new Day(date);
            created.entries.add(text);
            head = tail = current = created;
            dayCount = 1;
            return true;
        }
        int cmp = date.compareTo(current.date);
        if (cmp == 0) {                               // current day
            current.entries.add(text);
            return true;
        }
        if (cmp > 0 && (current.next == null || date.isBefore(current.next.date))) {
            Day created = new Day(date);              // new day right after the current one
            created.entries.add(text);
            created.previous = current;
            created.next = current.next;
            if (current.next != null) current.next.previous = created; else tail = created;
            current.next = created;
            current = created;
            dayCount++;
            return true;
        }
        return false;
    }

    /** Removes an entry of the current day; an empty day disappears (next day is shown, else previous). */
    public void removeEntry(int index) {
        current.entries.remove(index);
        if (current.entries.size() == 0) {
            Day removed = current;
            if (removed.previous != null) removed.previous.next = removed.next; else head = removed.next;
            if (removed.next != null) removed.next.previous = removed.previous; else tail = removed.previous;
            current = (removed.next != null) ? removed.next : removed.previous;
            dayCount--;
        }
    }

    /** Appends a ready-made day to the end (used when loading from a file). */
    public void append(Day day) {
        day.previous = tail;
        day.next = null;
        if (tail != null) tail.next = day; else head = day;
        tail = day;
        dayCount++;
        current = head;
    }
}