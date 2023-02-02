
function validator() {
  event.preventDefault();
  const comment = document.getElementById("comment-box");

  if (comment.value.length === 0) {
    alert("Add a Comment before continuing.");
    comment.focus();
    return false;
  }

  if (comment.value.length >= 280) {
    alert("Comment can't be more than 280 chars!");
    comment.focus();
    return false;
  }

  document.forms["comment"].submit();
}