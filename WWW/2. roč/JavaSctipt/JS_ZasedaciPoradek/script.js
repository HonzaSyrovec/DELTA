const students = [
    "Jan Novák", "Eliška Svobodová", "Tomáš Dvořák", "Anna Černá", 
    "Jakub Procházka", "Tereza Kučerová", "Lukáš Veselý", "Klára Horáková",
    "Martin Němec", "Veronika Pokorná", "Filip Marek", "Lucie Hájková"
];
const tlacitko = document.getElementById("generate-btn")
const classroom = document.getElementById("classroom")
const studentPool = document.getElementById("student-pool")


let tahanyStudent = null

students.forEach(jmeno => {
    const student = document.createElement("div")
    student.classList.add("student")
    student.textContent = jmeno
    student.setAttribute("draggable", "true")
    studentPool.appendChild(student)
})

tlacitko.addEventListener("click", () => {
    const pocetRad = document.getElementById("rows").value
    const pocetLavic = document.getElementById("desks").value

    classroom.innerHTML = ""

    for (let r = 0; r < pocetRad; r++) {
        const rada = document.createElement("div")
        rada.classList.add("row")

        for (let l = 0; l < pocetLavic; l++) {
            const lavice = document.createElement("div")
            lavice.classList.add("desk")

            rada.appendChild(lavice)
        }

        classroom.appendChild(rada)
    }

    nastavDragAndDrop()
})

function nastavDragAndDrop() {
    const studenti = document.querySelectorAll(".student")
    const lavice = document.querySelectorAll(".desk")

    studenti.forEach(student => {
        student.addEventListener("dragstart", (e) => {
            tahanyStudent = e.target
        })
    })

    lavice.forEach(desk => {

        desk.addEventListener("dragover", (e) => {
            e.preventDefault()
        })

        desk.addEventListener("dragenter", () => {
            desk.classList.add("drag-over")
        })

        desk.addEventListener("dragleave", () => {
            desk.classList.remove("drag-over")
        })

        desk.addEventListener("drop", () => {
            desk.classList.remove("drag-over")

            if (!tahanyStudent) return

            const staryStudent = desk.querySelector(".student")

            if (staryStudent) {
                studentPool.appendChild(staryStudent)
            }

            desk.appendChild(tahanyStudent)
            tahanyStudent = null
        })
    })
}
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