# Deník (spojový seznam + seznam nad polem)

- **Jazyk:** Java 17 (nebo novější), bez externích knihoven
- **Rozhraní:** konzole

## Struktura
- `DayList` – vlastní obousměrný spojový seznam dnů
- `Day` – uzel seznamu (datum + `EntryList`)
- `EntryList` – vlastní seznam nad polem (pole se při zaplnění zdvojnásobí)
- `Main` – konzolové rozhraní, ukládání a načítání (`denik.txt`)

## Spuštění
```
javac *.java
java Main
```

## Ovládání
Operace se píší do konzole: `predchozi`, `dalsi`, `zacatek`, `konec`, `novy`, `smaz`, `uloz`, `nacti`, `zavri`.
Text nového záznamu se ukončuje prázdným řádkem. Datum zadávejte jako `DD.MM.YYYY`.