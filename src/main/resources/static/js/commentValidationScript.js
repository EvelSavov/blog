//get input
var comment = document.getElementById("comment");


//get help labels
var commentHelp = document.getElementById("commentHelp");



//functions
function commentValidator() {
   if(comment.value.length<1){
         commentHelp.textContent = "Comment is invalid";
         comment.classList.add("is-invalid");
    }else{
         commentHelp.textContent = "";
         comment.classList.remove("is-invalid");
    }
}

comment.onkeyup =  commentValidator;


document.getElementById("commentBtn").onclick = function() {
    commentValidator();
}


