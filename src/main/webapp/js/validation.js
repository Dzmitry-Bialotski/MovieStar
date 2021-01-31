function TestLogin(login){

    if(/^[a-zA-Z1-9]+$/.test(login) === false)
    {alert('The login must contain only numbers and Latin letters'); return false;}
    if(login.length < 4 || login.length > 20)
    { alert('Login must be between 4 and 20 characters'); return false;}
    if(parseInt(login.substr(0, 1)))
    {alert('Login must start with a letter'); return false;}
    return true;
}