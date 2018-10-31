function validateLengthLogin() {
    var login = document.getElementsByName("login").length;
    if (login <= 25) {
        return true;
    } else {
        alert("Too long login");
        return false;
    }
}

function validateLengthPassword() {
    var pass = document.getElementsByName("password").length;
    if (pass <= 20){
        return true;
    }else {
        alert("Too big password");
        return false;
    }
}