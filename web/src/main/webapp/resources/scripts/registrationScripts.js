function validateFIO() {
    var x = document.forms["register"]["nameSurname"].value;
    var regEx = new RegExp("[A-Z]{1}[a-z]{1,11} [A-Z]{1}[a-z]{1,11}");
    if (x.match(regEx)) {
        return true;
    } else {
        alert("Incorrect field Name and Surname");
        return false;
    }
}

function validateEmail() {
    var x = document.forms["register"]["email"].value;
    var regEx = new RegExp("[aA0-zZ9]{1,10}-[0aA-zZ9]{1,10}@(gmail|tut|yahoo|mail|yandex).[a-z]{2,3}");
    if (x.match(regEx)) {
        return true;
    } else {
        alert("Incorrect field Email");
        return false;
    }
}

function validatePhone() {
    var x = document.forms["register"]["phone"].value;
    var regEx = new RegExp("\+375(29|44|17|25) [0-9]{7}");
    if (x.match(regEx)) {
        return true;
    } else {
        alert("Incorrect field phone number");
        return false;
    }
}

function validateStreet() {
    var x = document.forms["register"]["address"].value;
    var regEx = new RegExp("[aA-zZ]{3,15} [0-9]{1,3}-[0-9]{1,3} [aA-zZ]{6,8}");
    if (x.match(regEx)) {
        return true;
    } else {
        alert("Incorrect field Street");
        return false;
    }
}

function validatePassword() {
    var x = document.forms["register"]["password"].value;
    var regEx = new RegExp("[aA0-zZ9]{5,20}");
    if (x.match(regEx)) {
        return true;
    } else {
        alert("Incorrect field password");
        return false;
    }
}

function validateFirstAndSecondPassword() {
    var pass1 = document.forms["register"]["password"].value;
    var pass2 = document.forms["register"]["repeatedPassword"].value;
    if (pass1===pass2){
        return true;
    }else {
        alert("Passwords do not match");
        return false;
    }
}
