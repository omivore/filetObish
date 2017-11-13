window.onload = function() {
	var ghostForm = document.forms["twitterGateway"];
	ghostForm.submit();
	ghostForm.parentNode.removeChild(ghostForm);
}