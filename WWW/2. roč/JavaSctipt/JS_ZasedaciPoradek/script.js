const students = [
    "Jan Novák", "Eliška Svobodová", "Tomáš Dvořák", "Anna Černá", 
    "Jakub Procházka", "Tereza Kučerová", "Lukáš Veselý", "Klára Horáková",
    "Martin Němec", "Veronika Pokorná", "Filip Marek", "Lucie Hájková"
];

/**
 * ============================================================================
 * ZADÁNÍ: Tvorba a zasedací pořádek učebny
 * ============================================================================
 * Tvým úkolem je vdechnout život připravené HTML šabloně. Vytvoříš aplikaci,
 * která vygeneruje učebnu a umožní do ní pomocí myši (Drag & Drop) usadit studenty.
 * * ÚKOLY:
 * 
 * * 1. Generování učebny (DOM, Events):
 * - Naslouchej na kliknutí tlačítka "Vytvořit učebnu".
 * - Načti hodnoty z inputů (počet řad a počet lavic v řadě).
 * - Pomocí JS vytvoř odpovídající počet elementů `div` s třídou `row` (řada) a do nich vlož elementy `div` s třídou `desk` (lavice). 
 * - Tyto řady vlož do kontejneru `#classroom`.
 * - Tip: Nezapomeň kontejner před novým generováním vyčistit.
 * 
 * * 2. Seznam studentů (DOM):
 * - Projdi pole `students` a pro každého vytvoř element `div` s třídou `student`.
 * - Přidej do něj jméno studenta a nastav mu atribut `draggable="true"`.
 * - Vlož všechny studenty do kontejneru `#student-pool`.
 * 
 * * 3. Přetahování studentů (Drag & Drop Events):
 * - Implementuj Drag & Drop (události: dragstart, dragover, dragenter, dragleave, drop).
 * - Když uživatel táhne studenta nad lavici, přidej lavici CSS třídu `.drag-over` pro vizuální odezvu (a nezapomeň ji odstranit, když ji opustí nebo po puštění).
 * - Studenta lze pustit pouze do elementu s třídou `desk`.
 * 
 * * 4. Logika usazování a výměny:
 * - Pokud uživatel pustí studenta do *prázdné* lavice, student se přesune ze zásobníku do lavice.
 * - Pokud uživatel pustí studenta do *obsazené* lavice, PŮVODNÍ student se vrátí zpět do `#student-pool` a nový student zaujme jeho místo v lavici.
 * ============================================================================
 */