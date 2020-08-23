document.addEventListener("DOMContentLoaded", function () {
    var confirm = document.querySelector("#confirm");
    var pass = document.querySelector("#pass").value;
    var passConfirm = document.querySelector("#confirmation").value;
    if (passConfirm === "") {
        var span = document.createElement("span");
        let newChild = span;
        newChild.innerText = "Pole wymagane";
        confirm.appendChild(newChild);
        if (passConfirm !== pass) {
            alert("Hasła muszą być takie same");
        }
    }
});