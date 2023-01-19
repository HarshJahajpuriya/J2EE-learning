function validateLoginForm(form) {
document.getElementById('userNameErrorSection').innerHTML = '';
document.getElementById('passwordErrorSection').innerHTML = '';
if(form.userName.value.trim().length == 0)
{
document.getElementById('userNameErrorSection').innerHTML = '* required';
return false;
}
if(form.password.value.trim().length == 0) {
document.getElementById('passwordErrorSection').innerHTML = '* required';
return false;
}
return true;
}