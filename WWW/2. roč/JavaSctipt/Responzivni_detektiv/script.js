function velikost() {
    let sirka = window.innerWidth;
    let vyska = window.innerHeight;
    document.getElementById("velikost_okna").textContent = sirka + " x " + vyska;
}
velikost();

window.addEventListener('resize', velikost)
