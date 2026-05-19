/*
Máš k dispozici připravené uživatelské rozhraní pro správu školního systému (studenti, učitelé, třídy). Tvým cílem je napsat JavaScript, který aplikaci oživí a napojí ji na vzdálený server (API).

Informace o API:
Základní URL: https://delta-api-lovat.vercel.app
Pozor: API obsahuje "pevná" data, která nejdou upravit ani smazat. Smazat/upravit můžeš jen studenty, které sám přes API vytvoříš. Pokud API vrátí chybu, ukaž ji uživateli!

Tvé úkoly krok za krokem:
Zobrazení dat (GET): Napiš logiku uvnitř funkce loadUsers(). Zavolej API (endpoint /users), stáhni JSON data a pro každého uživatele vytvoř řádek v tabulce (<tr>).
Přidání studenta (POST): Zprovozni formulář nalevo. Po kliknutí na uložení pošli nová data na endpoint /students. Nezapomeň poslat API klíč v hlavičkách! Po uložení tabulku aktualizuj.
Mazání studenta (DELETE): Naprogramuj funkci deleteUser(id), která se zavolá po kliknutí na tlačítko "Smazat" u konkrétního záznamu. Vždy se uživatele nejprve zeptej (pomocí confirm()), zda chce záznam opravdu smazat.
Úprava (PUT/PATCH): Po kliknutí na "Upravit" naplň formulář existujícími daty studenta. Při odeslání takového formuláře nevytvářej nového studenta, ale odešli požadavek PUT na /users/{id}.
Filtrování: Nahoře je připravený filtr. Zprovozni ho tak, že pokud uživatel vybere "Filtrovat podle třídy" a napíše "10A", zavoláš API na adrese /classes/10A/students a překreslíš tabulku.
*/


// --- KONFIGURACE API --- doplň si ;-)
const API_URL = 'https://delta-api-lovat.vercel.app';
const API_KEY = '73d96de7fedb250f05eb1c301ca1a3203463ae8fbec08f93';

// --- VÝBĚR HTML ELEMENTŮ ---
// Elementy formuláře a tabulky
const tableBody = document.getElementById('table-body');
const form = document.getElementById('user-form');
const formTitle = document.getElementById('form-title');
const btnSubmit = document.getElementById('btn-submit');
const btnCancel = document.getElementById('btn-cancel');
const formMsg = document.getElementById('form-msg');

// Elementy pro filtrování
const filterClassInput = document.getElementById('filter-class');
const btnFilterClass = document.getElementById('btn-filter-class');
const filterSubjectInput = document.getElementById('filter-subject');
const btnFilterSubject = document.getElementById('btn-filter-subject');
const btnFilterReset = document.getElementById('btn-filter-reset');

// Místo pro uložení právě zobrazených uživatelů (hodí se pro předvyplnění formuláře při úpravě)
let currentUsers = [];

// --- ÚKOL 1: ZOBRAZENÍ DAT (GET) ---
async function loadUsers(endpoint = '/users') {
    try {
        // ZDE PIŠ KÓD:
        currentUsers = fetch('https://delta-api-lovat.vercel.app/api/users');
        JSON.parse(currentUsers);
        
        // 1. Zavolej fetch() na celou adresu (API_URL + endpoint)
        // 2. Odpověď převeď na JSON. Data si ulož do proměnné currentUsers.
        // 3. Vymaž obsah tableBody.innerHTML.
        // 4. Pomocí cyklu (forEach) projdi stažená data a pro každého uživatele vytvoř HTML řádek (<tr>).
        // Tip: Do HTML tlačítek rovnou přidej události pro úpravu a smazání, např.: 
    } catch (error) {
        console.error('Chyba načítání dat:', error);
        tableBody.innerHTML = '<tr><td colspan="4" class="p-4 text-center text-red-500">Došlo k chybě při načítání.</td></tr>';
    }
}

// --- ÚKOL 2 & 4: VYTVÁŘENÍ (POST) A ÚPRAVA (PUT) ---
form.addEventListener('submit', async (udalost) => {
    udalost.preventDefault(); // Zabrání refreshi stránky po odeslání

    // ZDE PIŠ KÓD:
    // 1. Získej hodnoty z inputů (name, classId, subject a ze skrytého pole user-id).
    // 2. Zjisti, jestli formulář vytváří nového studenta (id je prázdné), nebo upravuje existujícího (id něco obsahuje).
    // 3. Podle toho nastav správnou URL
    // 4. Zavolej fetch(). Do těla (body) dej data.
    // 5. Pokud API vrátí chybu (!response.ok), ukaž ji pomocí funkce showMessage(). 
    // 6. Pokud je vše v pořádku, ukaž zprávu o úspěchu a překresli tabulku zavoláním loadUsers().
});

// --- ÚKOL 3: MAZÁNÍ (DELETE) ---
// Tuto funkci bude volat tlačítko "Smazat" přímo z tabulky
async function deleteUser(id) {
    // ZDE PIŠ KÓD:
    // 1. Zeptej se uživatele přes confirm("Opravdu smazat?"). Pokud klikne na Zrušit, ukonči funkci (return).
    // 2. Pošli fetch.
    // 3. Pokud je mazání úspěšné, znovu načti tabulku (loadUsers()).
}


// --- ÚKOL 5: FILTROVÁNÍ (GET s parametry) ---

// Hledání podle TŘÍDY
btnFilterClass.addEventListener('click', () => {
    // ZDE PIŠ KÓD:
    // 1. Získej hodnotu z políčka filterClassInput.
    // 2. Pokud není prázdná, poskládej endpoint pro třídu.
    // 3. Zavolej funkci loadUsers(tento_novy_endpoint).
});

// Hledání podle PŘEDMĚTU
btnFilterSubject.addEventListener('click', () => {
    // ZDE PIŠ KÓD:
    // 1. Získej hodnotu z políčka filterSubjectInput.
    // 2. Pokud není prázdná, poskládej endpoint pro předmět.
    // 3. Zavolej funkci loadUsers(tento_novy_endpoint).
});

// Vrácení zobrazení na všechny uživatele
btnFilterReset.addEventListener('click', () => {
    // ZDE PIŠ KÓD:
    // 1. Vymaž texty v obou inputech pro filtr (nastav .value = '').
    // 2. Zavolej funkci loadUsers().
});


// --- POMOCNÉ FUNKCE (Už připravené) ---

// Tato funkce se zavolá, když uživatel klikne na tlačítko "Upravit" v tabulce.
// Najde správná data a předvyplní je do formuláře nalevo.
window.prepareEdit = function(id) {
    const user = currentUsers.find(u => u.id === id);
    if (!user) return;

    document.getElementById('user-id').value = user.id;
    document.getElementById('name').value = user.name;
    document.getElementById('classId').value = user.classId || '';
    document.getElementById('subject').value = user.subject || '';

    formTitle.innerText = 'Upravit uživatele';
    btnSubmit.innerText = 'Uložit změny';
    btnCancel.classList.remove('hidden');
    
    // Odscrollování nahoru k formuláři
    window.scrollTo({ top: 0, behavior: 'smooth' });
};

// Reset formuláře po úspěšné akci nebo kliknutí na "Zrušit"
btnCancel.addEventListener('click', () => {
    form.reset();
    document.getElementById('user-id').value = '';
    formTitle.innerText = 'Přidat studenta';
    btnSubmit.innerText = 'Vytvořit';
    btnCancel.classList.add('hidden');
});

// Zobrazení dočasné zprávy ve formuláři (úspěch / chyba)
function showMessage(text, colorClass) {
    formMsg.className = `text-sm mt-3 font-medium text-center ${colorClass}`;
    formMsg.innerText = text;
    setTimeout(() => formMsg.innerText = '', 4000); // Zpráva zmizí po 4 vteřinách
}

// Úplně první akce po načtení souboru: zavoláme zobrazení všech uživatelů
window.addEventListener('load',loadUsers);