document.addEventListener("DOMContentLoaded", function () {
    /**
     * message for empty "confirmPassword" input and alert for different passwords
     */

    var nameL = document.querySelector("#nameLabel");
    var mailL = document.querySelector("#mailLabel");
    var passL = document.querySelector("#passLabel");
    var confirmL = document.querySelector("#confirmLabel");
    var name = document.querySelector("#companyName").value;
    var mail = document.querySelector("#companyMail").value;
    var pass = document.querySelector("#pass").value;
    var passConfirm = document.querySelector("#confirmation").value;
    if (name === "") {
        message(nameL);
    } else {
        blur();
    }
    if (mail === "") {
        message(mailL);
    }
    if (pass === "") {
        message(passL);
    }
    if (passConfirm === "") {
        message(confirmL);
        if (passConfirm !== pass) {
            passAlert();
        }
    }

    function message(label) {
        let newChild = document.createElement("span");
        newChild.innerText = "Pole wymagane";
        return label.appendChild(newChild);
    }


    function passAlert() {
        alert("Hasła muszą być takie same");
    }


    /**
     * validation password and message box
     */

    var myInput = document.getElementById("pass");
    var letter = document.getElementById("letter");
    var capital = document.getElementById("capital");
    var number = document.getElementById("number");
    var length = document.getElementById("length");
    var mark = document.getElementById("mark");
    var msg = document.getElementById("message");

// When the user clicks on the password field, show the message box
    myInput.onfocus = function () {
        msg.style.display = "block";
    }

// When the user clicks outside of the password field, hide the message box
    myInput.onblur = function () {
        msg.style.display = "none";
    }

// When the user starts to type something inside the password field
    myInput.onkeyup = function () {
        // Validate lowercase letters
        var lowerCaseLetters = /[a-z]/g;
        if (myInput.value.match(lowerCaseLetters)) {
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        } else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }

        // Validate capital letters
        var upperCaseLetters = /[A-Z]/g;
        if (myInput.value.match(upperCaseLetters)) {
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }

        // Validate numbers
        var numbers = /[0-9]/g;
        if (myInput.value.match(numbers)) {
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }

        // validate special marks
        var marks = /[@$!%*?&]/g;
        if (myInput.value.match(marks)) {
            mark.classList.remove("invalid");
            mark.classList.add("valid");
        } else {
            mark.classList.remove("valid");
            mark.classList.add("invalid");
        }

        // Validate length
        if (myInput.value.length >= 8) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }

    }

});