//sem přijde váš kód
let oldpass = document.getElementsByName(oldpass)
let pass = document.getElementsByName(pass)
let verifypass = document.getElementsByName(pass2)

if(oldpass, pass, verifypass){
    if(oldpass =! pass){
        if(pass = verifypass){
            
        }else alert("Nové heslo se neshoduje s verifikačním heslem")
    }else alert("Staré a nové heslo jsou stejné")
}else alert("vyplňte všecha pole")
