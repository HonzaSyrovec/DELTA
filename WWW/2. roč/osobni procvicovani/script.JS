let username = document.getElementById("jmeno");
let save = document.getElementById("ulozit");
let remove = document.getElementById("smazat");
let displayname = document.querySelector("h1");

let savedUsername = localStorage.getItem("jmeno")
pozdrav(savedUsername)

//uložení jména v textarea
save.addEventListener("click", function (){
  pozdrav(username.value);
  localStorage.setItem("jmeno", username.value)
})

//odstranění jména z paměti i akt. instance
remove.addEventListener("click", function (){
  localStorage.removeItem("jmeno")
  pozdrav(null)
});

//fce nacteni stranky
function pozdrav(jmeno){
  if(jmeno){
  displayname.textContent = "Ahoj, " + jmeno
  }else  displayname.textContent = "Ahoj, hoste";
}