//sem přijde váš kód
let toggleclass = document.getElementById("toggleClass")
let togglefancy = document.getElementById("toggleFancy")

let multiclass = document.getElementById("multiclass")

let addfat = document.getElementById("addFat")
let removefat = document.getElementById("removeFat")

let addredish = document.getElementById("addRedish")
let removeredish = document.getElementById("removeRedish")

let addhuge = document.getElementById("addHuge")
let removehuge = document.getElementById("removeHuge")

let classlist = document.getElementById("classList")

function vypis() {
    classlist.innerHTML = ""
    multiclass.classList.forEach(trida => {
        let li = document.createElement("li")
        li.textContent = trida
        classlist.appendChild(li)
    })
}

togglefancy.onclick = function() {
    toggleclass.classList.toggle("fancy")
}

addfat.onclick = function() {
    multiclass.classList.add("fat")
    vypis()
}

removefat.onclick = function() {
    multiclass.classList.remove("fat")
    vypis()
}

addredish.onclick = function() {
    multiclass.classList.add("redish")
    vypis()
}

removeredish.onclick = function() {
    multiclass.classList.remove("redish")
    vypis()
}

addhuge.onclick = function() {
    multiclass.classList.add("huge")
    vypis()
}

removehuge.onclick = function() {
    multiclass.classList.remove("huge")
    vypis()
}

vypis()