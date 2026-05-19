const btn = document.getElementById("btn");
const output = document.getElementById("output");

const cache_nazev = "pwa-cache-v1"
const soubory = [
    "/",
    "/index.html",
    "/script.js"
]

btn.addEventListener("click", () => {
    output.textContent = "Clicked!"
});

if ("serviceWorker" in navigator) {
        window.addEventListener("load", () => {
            navigator.serviceWorker.register("sw.js")
            .then(reg => {
                console.log("SW registered", reg)
            })
            .catch(err => {
                console.log("SW failed", err)
            })
   })
}

self.addEventListener("install", event => {
        event.waitUntil(
         caches.open(cache_nazev).then(cache => {
            return cache.addAll(soubory)
        })
    )
})
