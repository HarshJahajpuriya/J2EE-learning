function validateForm(form) 
{
var designationErrorSection = document.getElementById("designationErrorSection")
designationErrorSection.innerHTML = '';
if(form.title.value.trim().length == 0)
{
designationErrorSection.innerHTML = '*required';
form.title.focus();
return false;
}
form.title.value = form.title.value.trim();
return true;
}