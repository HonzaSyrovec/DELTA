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



    newSessionBtn.addEventListener("click", function() {
        var dnes = new Date().toLocaleDateString("cs-CZ");
        var popis = sessionDesc.value;
        sessionInfo.textContent = "Lekce: " + dnes + " - " + popis;
        attendanceSection.style.display = "block";
    });

    //load students local storage
    var students = JSON.parse(localStorage.getItem("students")) || [];

    //students into list 
    function renderStudents(){
        studentList.innerHTML = "";

        for(var i = 0; i < students.length; i++){
            var li = document.createElement ("li");
            li.textContent = students[i];
            studentList.appendChild(li);
        }
    }
    
    renderStudents();

    studentAdd.addEventListener("click", function() {
        var name = studentName.value.trim();
        
});