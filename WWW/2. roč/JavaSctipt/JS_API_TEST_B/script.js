// API dokumentace: https://mottl.delta-www.cz/tada/index.php
let addBtn = document.querySelector('btn');
let moveBtn = document.querySelector('move-btn');
let jmeno = document.getElementById('taskDev')
let tasks = [];

//vytvoreni karet
const card = document.createElement()
card.innerHTML = 



//get 
async function getTasks(){
    const url = "https://mottl.delta-www.cz/tada/index.php";
    const response = await fetch(url)
    const res = await response.json();

    tasks = response.json
}
//POST
async function createTask(params) {
    const url = "https://mottl.delta-www.cz/tada/api.php?path=users";
        const response = await fetch(url, {
    method: "POST",
    header: X-API-Key: u7Tsz3E0jEZKKAMyepTG0Tr7,
    body: JSON.stringify()
    })
}
//patch 
async function moveUp() {
    const url = "https://mottl.delta-www.cz/tada/api.php?path=users/1";
    a

    
}
moveBtn.addEventListener('click', moveUp);
addBtn.addEventListener('click', createTask);
 
//naseptavani


