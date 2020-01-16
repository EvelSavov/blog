//get input
var firstName = document.getElementById("firstName");
var lastName = document.getElementById("lastName");
var username = document.getElementById("username");
var password = document.getElementById("password");
var confirmPassword = document.getElementById("confirm_password");
var email = document.getElementById("email");
var address = document.getElementById("address");

//get help labels
var firstNameHelp = document.getElementById("firstNameHelp");
var lastNameHelp = document.getElementById("lastNameHelp");
var usernameHelp = document.getElementById("usernameHelp");
var passwordHelp = document.getElementById("passwordHelp");
var confirmPasswordHelp = document.getElementById("confirmPasswordHelp");
var emailHelp = document.getElementById("emailHelp");
var addressHelp = document.getElementById("addressHelp");

//Regex
var nameRegex = /[A-Z][a-z]*/g;
var usernameRegex = /^([a-zA-Z0-9_]+)$/g;
var passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\w\s]).{8,}$/g;
var emaiRegex = /^([a-zA-Z0-9_]+@[a-zA-Z]+\.[a-zA-Z]+)(\.[a-zA-Z]+)*$/g;


//functions
function firstNameValidator() {
   if(!firstName.value.match(nameRegex)){
         firstNameHelp.textContent = "First name is invalid";
         firstName.classList.add("is-invalid");
    }else{
        firstNameHelp.textContent = "";
         firstName.classList.remove("is-invalid");
    }
}

function lastNameValidator(){
   if(!lastName.value.match(nameRegex)){
         lastNameHelp.textContent = "Last name is invalid";
          lastName.classList.add("is-invalid");
    }else{
        lastNameHelp.textContent = "";
     lastName.classList.remove("is-invalid");
    }
}
function usernameValidator() {
   if(!username.value.match(usernameRegex)){
         usernameHelp.textContent = "Username is invalid";
          username.classList.add("is-invalid");
    }else{
        usernameHelp.textContent = "";
        username.classList.remove("is-invalid");
    }
}

function passwordValidator() {
   if(!password.value.match(passwordRegex)){
         passwordHelp.textContent = "Password is invalid";
          password.classList.add("is-invalid");
    }else{
        passwordHelp.textContent = "";
        password.classList.remove("is-invalid");
    }
}
function confirmPasswordValidator() {
   if(confirmPassword.value == password.value){
         confirmPasswordHelp.textContent = "";
          confirmPassword.classList.remove("is-invalid");
    }else{
        confirmPasswordHelp.textContent = "Confirm password is invalid";
        confirmPassword.classList.add("is-invalid");
    }
}
function emailValidator() {
     if(!email.value.match(emaiRegex)){
            emailHelp.textContent = "Password is invalid";
             email.classList.add("is-invalid");
       }else{
           emailHelp.textContent = "";
           email.classList.remove("is-invalid");
       }
}




firstName.onkeyup =  firstNameValidator;
lastName.onkeyup =  lastNameValidator;
username.onkeyup =  usernameValidator;
password.onkeyup = passwordValidator;
confirmPassword.onkeyup = confirmPasswordValidator;
email.onkeyup = emailValidator;

document.getElementById("regBtn").onclick = function() {
    firstNameValidator();
    lastNameValidator();
    usernameValidator();
    passwordValidator();
    confirmPasswordValidator();
    emailValidator();
}


