var SUPABASE_URL = "https://ywqmdrxfxxlwasazzspq.supabase.co/";
var SUPABASE_KEY = "sb_publishable_WcWoJe5OCaLs1q77QXxC0A_5sSLTHA_";

//check if logged in
var sessionData = JSON.parse(localStorage.getItem("session"));
if (!sessionData) {
    window.location.href = "login.html";
}

//use token from sess
var AUTH_TOKEN = sessionData ? sessionData.access_token : SUPABASE_KEY;


document.addEventListener("DOMContentLoaded", function() {

    //start gets
    var newSessionBtn = document.getElementById("new-session-btn");

    //attendance gets
    var attendanceSection = document.getElementById("attendance-section");
    var sessionInfo = document.getElementById("session-info");
    var attendanceBody = document.getElementById("attendance-body");
    var sessionDesc = document.getElementById("session-desc");
    var attendanceSave = document.getElementById("attendance-save");
    var attendanceClose = document.getElementById("attendance-close");

    //student gets
    var studentList = document.getElementById("student-list");
    var studentName = document.getElementById("student-name");
    var studentAdd = document.getElementById("student-add");

    //sess search gets
    var sessionDate = document.getElementById("session-date");
    var sessionLoad = document.getElementById("session-load");
    var sessionDelete = document.getElementById("session-delete");
    var sessionClose = document.getElementById("session-close");

    //sess search result gets
    var searchResultSection = document.getElementById("search-result-section");
    var searchResultInfo = document.getElementById("session-search-result-info");
    var searchResultBody = document.getElementById("session-search-result-body");
    var searchResultSave = document.getElementById("session-search-result-save");
    var searchResultDelete = document.getElementById("session-search-result-delete");
    var searchResultClose = document.getElementById("session-search-result-close");
    var searchResultDesc = document.getElementById("session-search-result-desc");

    //logout gets
    var logoutBtn = document.getElementById("logout-btn");

    //logout
    logoutBtn.addEventListener("click", function() {
        localStorage.removeItem("session");
        window.location.href = "login.html";
    });
    //new sess btn
    newSessionBtn.addEventListener("click", function() {
        var dnes = new Date().toLocaleDateString("cs-CZ");
        var popis = sessionDesc.value;
        sessionInfo.textContent = "Lekce: " + dnes + " - " + popis;
        attendanceSection.style.display = "block";
        renderAttendance();
    });
    
    //students into attentance table
    function renderAttendance(){
        attendanceBody.innerHTML = "";

        for(var i = 0; i < students.length; i++){
            var tr = document.createElement("tr");

            var tdName = document.createElement("td");
            tdName.textContent = students[i].name;

            var tdCheck = document.createElement("td");
            var checkbox = document.createElement("input");
            checkbox.setAttribute("type", "checkbox");
            checkbox.setAttribute("data-student", students[i].name);

            tdCheck.appendChild(checkbox)
            tr.appendChild(tdName)
            tr.appendChild(tdCheck)
            attendanceBody.appendChild(tr);
        }
    }


    //ATTENDANCE SEC
    //saves attendance
    attendanceSave.addEventListener("click", function(){
        var date = new Date().toLocaleDateString("cs-CZ");
        var checkboxes = attendanceBody.querySelectorAll("input[type = 'checkbox']");
        var desc = sessionDesc.value;

        //saves sess
        fetch(SUPABASE_URL + "rest/v1/sessions",{
            method:"POST",
            headers:{
                "apikey": SUPABASE_KEY,
                "Authorization": "Bearer " + AUTH_TOKEN,
                "Content-Type": "application/json",
                "Prefer": "return=representation"
            },
            body: JSON.stringify({date: date, desc: desc})
        })
            .then(function(response){
                return response.json();
         })
            .then(function(data){
                var sessionId = data[0].id;
                var records = [];
            //attendance for student
            for(var i = 0; i < checkboxes.length; i++){
                var name = checkboxes[i].getAttribute("data-student");
                var student = students.find(function(s){ return s.name === name;});
                records.push({
                    session_id: sessionId,
                    student_id: student.id,
                    present: checkboxes[i].checked
                });
         }
            //saves attendance
            return fetch(SUPABASE_URL + "rest/v1/attendance",{
                method: "POST",
                headers: {
                    "apikey": SUPABASE_KEY,
                    "Authorization": "Bearer " + AUTH_TOKEN,
                    "Content-Type": "application/json",
                    "Prefer": "return=minimal"
                    },
                    body: JSON.stringify(records)
            });
        })
        .then(function(response){
            alert("Docházka uložena")
        })
    });

    //closes attendance writing
    attendanceClose.addEventListener("click", function(){
        attendanceSection.style.display = "none"
        attendanceBody.innerHTML = "";
        sessionInfo.textContent = "";
        sessionDesc.value = "";
    });

    //STUDENTS SEC
    var students = [];

    //load students from supabase
    function loadStudents() {
        fetch(SUPABASE_URL + "rest/v1/students?select=*", {
            method: "GET",
            headers: {
                "apikey": SUPABASE_KEY,
                "Authorization": "Bearer " + AUTH_TOKEN
            }
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            console.log("Data:", data);
            students = data;
            renderStudents()
        });
    }

    //students into list, adds deete btn to them 
    function renderStudents(){
        studentList.innerHTML = "";

        for(var i = 0; i < students.length; i++){
            var li = document.createElement ("li");
            li.textContent = students[i].name;

            var deleteBtn = document.createElement("button");
            deleteBtn.textContent = "✕";
            deleteBtn.setAttribute("data-index", i)

            deleteBtn.addEventListener("click", function(){
                var index = this.getAttribute("data-index");
                var studentId = students[index].id;

                fetch(SUPABASE_URL + "rest/v1/students?id=eq." + studentId,{
                    method: "DELETE",
                    headers: {
                        "apikey": SUPABASE_KEY,
                        "Authorization": "Bearer " + AUTH_TOKEN
                    }
                })
                .then(function(response){
                    loadStudents();
                });
            });
                li.appendChild(deleteBtn)
                studentList.appendChild(li);
           
        }
    }
    
    loadStudents();
    //new student 
    studentAdd.addEventListener("click", function() {
        var name = studentName.value.trim();

        if (name === ""){
            return;
        }
        fetch(SUPABASE_URL + "rest/v1/students",{
            method: "POST",
            headers:{
                "apikey": SUPABASE_KEY,
                "Authorization": "Bearer " + AUTH_TOKEN,
                "Content-Type": "application/json",
                "Prefer": "return=minimal"
            },
            body: JSON.stringify({name: name})
        })
        .then(function(response){
            studentName.value = "";
            loadStudents();
        })
    });

    //LOADING HISTORY SESS SEC
    //loads past sessions
    sessionLoad.addEventListener("click", function(){
        var date = sessionDate.value;

        if(date === ""){
            alert("musíš vybrat datum")
            return;
        }
        var formated = new Date(date).toLocaleDateString("cs-CZ");
       
        //sess by date
        fetch(SUPABASE_URL + "rest/v1/sessions?date=eq." + formated + "&select=*",{
            method: "GET",
            headers:{
                "apikey": SUPABASE_KEY,
                "Authorization": "Bearer " + AUTH_TOKEN
            }
        })
        .then(function(response){
            return response.json();
        })
        .then(function(data){
            if(data.length === 0){
                alert("v tomto datumu se neodehrála žádná lekce");
                return;
            }
            var session = data[0];

            //attendance for given sess
            return fetch(SUPABASE_URL + "rest/v1/attendance?session_id=eq." + session.id + "&select=*",{
                method: "GET",
                headers:{
                    "apikey": SUPABASE_KEY,
                    "Authorization": "Bearer " + AUTH_TOKEN
                }
            })
            .then(function(response){
                return response.json();
            })
            .then(function(attendance){
                searchResultInfo.textContent = "Lekce: " + session.date;
                searchResultDesc.value = session.desc;
                searchResultSection.setAttribute("data-session-id", session.id);
                searchResultSection.style.display = "block";
                searchResultBody.innerHTML = "";

                for(var i = 0; i < students.length; i++){
                    var tr = document.createElement("tr");

                    var tdName = document.createElement("td");
                    tdName.textContent = students[i].name;

                    var tdCheck = document.createElement("td");
                    var checkbox = document.createElement("input");
                    checkbox.setAttribute("type", "checkbox");
                    checkbox.setAttribute("data-student", students[i].name);

                    //attendance data for given student
                    var record = attendance.find(function(a){
                        return a.student_id === students[i].id;
                    });
                    if(record){
                        checkbox.checked = record.present
                    }

            tdCheck.appendChild(checkbox);
            tr.appendChild(tdName);
            tr.appendChild(tdCheck);
            searchResultBody.appendChild(tr);
                }
            })

        });

            

        
    });

    //closes loaded sess
    searchResultClose.addEventListener("click", function(){
        searchResultSection.style.display = "none";
        searchResultBody.innerHTML = "";
        searchResultInfo.textContent = "";
        sessionDate.value = "";
    });
    searchResultDelete.addEventListener("click", function(){
        var sessionId = searchResultSection.getAttribute("data-session-id");

        fetch(SUPABASE_URL + "rest/v1/attendance?session_id=eq." + sessionId,{
            method: "DELETE",
            headers: {
                "apikey": SUPABASE_KEY,
                "Authorization": "Bearer " + AUTH_TOKEN
            }
        })
        .then(function(response){
            return fetch(SUPABASE_URL + "rest/v1/sessions?id=eq." + sessionId,{
                method: "DELETE",
                headers: {
                    "apikey": SUPABASE_KEY,
                    "Authorization": "Bearer " + AUTH_TOKEN
                }
            });
        })
        .then(function(response){
            searchResultSection.style.display = "none";
            searchResultBody.innerHTML = "";
            searchResultInfo.textContent = "";
            sessionDate.value = "";
             alert("lekce smazána");
        });
    });

    //saves changes made
    searchResultSave.addEventListener("click", function(){
        var sessionId = searchResultSection.getAttribute("data-session-id");
        var checkboxes = searchResultBody.querySelectorAll("input[type='checkbox']");

        //delete old attendace
        fetch(SUPABASE_URL + "rest/v1/attendance?session_id=eq." + sessionId,{
            method: "DELETE",
            headers: {
                "apikey": SUPABASE_KEY,
                "Authorization": "Bearer " + AUTH_TOKEN
            }
        })
        .then(function(response){
            var records = [];

            //new attendance record
            for(var i = 0; i < checkboxes.length; i++){
                var name = checkboxes[i].getAttribute("data-student");
                var student = students.find(function(s){ return s.name === name;});
                records.push({
                    session_id: sessionId,
                    student_id: student.id,
                    present: checkboxes[i].checked
                });
            }
            //saves new attendance
            return fetch(SUPABASE_URL + "rest/v1/attendance",{
                method: "POST",
                headers: {
                    "apikey": SUPABASE_KEY,
                    "Authorization": "Bearer " + AUTH_TOKEN,
                    "Content-Type": "application/json",
                    "Prefer": "return=minimal"
                },
                body: JSON.stringify(records)
            });
        })
        //Update sess desc
        .then(function(response){
            return fetch(SUPABASE_URL + "rest/v1/sessions?id=eq." + sessionId,{
                method: "PATCH",
                headers: {
                    "apikey": SUPABASE_KEY,
                    "Authorization": "Bearer " + AUTH_TOKEN,
                    "Content-Type": "application/json",
                    "Prefer": "return=minimal"
                },
                body: JSON.stringify({desc: searchResultDesc.value})
            });
        })
        .then(function(response){
            alert("změny uloženy");
        });
    });
});
//sw register
if ("serviceWorker" in navigator) {
    navigator.serviceWorker.register("sw.js");
}