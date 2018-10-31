function validatePassword() {
    var x = document.forms["passwordChange"]["oldPassword"].value;
    var regEx = new RegExp("[aA0-zZ9]{5,20}");
    if (x.match(regEx)) {
        return true;
    } else {
        alert("Incorrect field password");
        return false;
    }
}

function validateNewPassword() {
    var x = document.forms["passwordChange"]["newPassword"].value;
    var regEx = new RegExp("[aA0-zZ9]{5,20}");
    if (x.match(regEx)) {
        return true;
    } else {
        alert("Incorrect field password");
        return false;
    }
}

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