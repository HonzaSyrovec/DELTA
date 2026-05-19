let textArea = document.getElementById("textArea");
let getBtn = document.getElementById("getBtn");
let searchBtn = document.getElementById("searchBtn");
let searchRes = document.getElementById("searchRes");

getBtn.addEventListener("click", getip);
searchBtn.addEventListener("click", searchip);

function getip() {
    fetch("https://api.ipify.org/?format=json")
        .then(function(response) {
            return response.json()
        })
        .then(function(ip) {
            writeip(ip)
        })
}

function writeip(ip) {
    textArea.value = ip.ip
}

function searchip(){
    let ip = textArea.value
    
    fetch("https://ipinfo.io/" + ip + "/geo")
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        
    })
}