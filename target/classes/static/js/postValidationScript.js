//get input
var title = document.getElementById("title");
var body = document.getElementById("body");

//get help labels
var titleHelp = document.getElementById("titleHelp");
var bodyHelp = document.getElementById("bodyHelp");


//functions
function titleValidator() {
   if(title.value.length<1){
         titleHelp.textContent = "Title is invalid";
         title.classList.add("is-invalid");
    }else{
         titleHelp.textContent = "";
         title.classList.remove("is-invalid");
    }
}

function bodyValidator(){
   if(body.value.length<200){
          bodyHelp.textContent = "Post is invalid";
          body.classList.add("is-invalid");
    }else{
        bodyHelp.textContent = "";
        body.classList.remove("is-invalid");
    }
}

title.onkeyup =  titleValidator;
body.onkeyup =  bodyValidator;


document.getElementById("postBtn").onclick = function() {
    titleValidator();
    bodyValidator();
}


