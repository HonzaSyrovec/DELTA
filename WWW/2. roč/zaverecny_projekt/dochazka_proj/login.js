var SUPABASE_URL = "https://ywqmdrxfxxlwasazzspq.supabase.co/";
var SUPABASE_KEY = "sb_publishable_WcWoJe5OCaLs1q77QXxC0A_5sSLTHA_";

document.addEventListener("DOMContentLoaded", function() {

    //gets
    var loginBtn = document.getElementById("login-btn");
    var loginEmail = document.getElementById("login-email");
    var loginPassword = document.getElementById("login-password");
    var loginError = document.getElementById("login-error");

    //check if already logged in
    var session = localStorage.getItem("session");
    if (session) {
        window.location.href = "index.html";
    }

    //login on button click
    loginBtn.addEventListener("click", function() {
        console.log("Kliknuto")
        var email = loginEmail.value.trim();
        var password = loginPassword.value.trim();
         console.log("Email:", email, "Password:", password);

        if (email === "" || password === "") {
            loginError.textContent = "Vyplň email a heslo.";
            return;
        }

        fetch(SUPABASE_URL + "auth/v1/token?grant_type=password", {
            method: "POST",
            headers: {
                "apikey": SUPABASE_KEY,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email: email, password: password })
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            if (data.error) {
                loginError.textContent = "Špatný email nebo heslo.";
                return;
            }

            localStorage.setItem("session", JSON.stringify(data));
            window.location.href = "index.html";
        });
    });

});