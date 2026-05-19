let input = document.getElementById('search');
let datalist = document.getElementById('charlist');

window.addEventListener('load', async function(){
    const response = fetch("https://thronesapi.com//api/v2/Characters");
    const data = await response.json;
    for(let char of data ){
        let option = document.createElement('option');
        option.innerHTML = char.FullName;
        datalist.appendChild(option)
        window.sessionStorage.setItem(char.FullName, char.id );
    }
})

input.addEventListener('keydown', async function(e){
    if (e.key == "Enter"){
        if(window.sessionStorage.getItem(input.value)){


        }
    }

    
})