function validateLogin(login){
    if(/^[a-zA-Z1-9]+$/.test(login) === false) {
        alert('The login must contain only numbers and Latin letters');
        return false;
    }
    if(login.length < 4 || login.length > 20) {
        alert('Login must be between 4 and 20 characters');
        return false;
    }
    if(parseInt(login.substr(0, 1))) {
        alert('Login must start with a letter');
        return false;
    }
    return true;
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function validatePassword(password) {
    if(password == "") {
        alert('Fill the password please!')
        return false;
    }
    if(password.length < 6) {
        alert('Password length must be at least 6 characters')
        return false;
    }
    if(password.length > 30) {
        alert('Password length must not exceed 30 characters')
        return false;
    }
    return true;
}

function isPasswordMatches(password1, password2) {
    if(password1 != password2){
        alert('Password do not match')
        return false;
    }
    return true;
}

function registerValidate(login, password1, password2) {
    return validateLogin(login) && validatePassword(password1) && isPasswordMatches(password1, password2);
}