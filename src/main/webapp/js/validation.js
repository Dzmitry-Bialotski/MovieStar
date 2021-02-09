function writeError(error_message) {
    const errors = document.getElementsByClassName("error");
    for(let i = 0; i < errors.length; i++){
        errors.item(i).innerHTML = error_message;
    }
}

function validateLogin(login){
    if(/^[a-zA-Z1-9]+$/.test(login) === false) {
        writeError('The login must contain only numbers and Latin letters');
        return false;
    }
    if(login.length < 4 || login.length > 20) {
        const errors = document.getElementsByClassName("error");
        for(let i = 0; i < errors.length; i++){
            errors.item(i).innerHTML = 'Login must be between 4 and 20 characters';
        }
        return false;
    }
    if(parseInt(login.substr(0, 1))) {
        writeError('Login must start with a letter');
        return false;
    }
    return true;
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(re.test(String(email).toLowerCase())){
        return true;
    }else{
        alert('Incorrect email');
        return false;
    }
}

function validatePassword(password) {
    if(password == "") {
        writeError('Fill the password please!')
        return false;
    }
    if(password.length < 6) {
        writeError('Password length must be at least 6 characters')
        return false;
    }
    if(password.length > 30) {
        writeError('Password length must not exceed 30 characters')
        return false;
    }
    return true;
}

function isPasswordMatches(password1, password2) {
    if(password1 != password2){
        writeError('Password do not match')
        return false;
    }
    return true;
}

function registerValidate(login, password1, password2) {
    return validateLogin(login) && validatePassword(password1) && isPasswordMatches(password1, password2);
}

function loginValidate(login, password) {
    return validateLogin(login) && validatePassword(password);
}


function validateName(firstName, secondName){
    if(firstName.length < 2 || firstName.length > 30) {
        alert("First Name must be between 2 and 30 character")
        return false;
    }
    if(secondName.length < 2 || secondName.length > 30) {
        alert("secondName must be between 2 and 30 character")
        return false;
    }
    if(/^[a-zA-Z]+$/.test(firstName) === false) {
        alert('First Name must contain only Latin letters');
        return false;
    }
    if(/^[a-zA-Z]+$/.test(secondName) === false) {
        alert('Second Name must contain only Latin letters');
        return false;
    }
    return true;
}
