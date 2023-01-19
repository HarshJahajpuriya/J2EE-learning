const designationErrorSection = document.getElementById("designationErrorSection");
const designationErrorFormSection = document.getElementById("designationErrorFormSection");
const form = document.getElementById('form');

function validateForm() {  
  designationErrorSection.innerHTML = '';
  designationErrorFormSection.innerHTML = '';
  if (form.title.value.trim().length == 0) {
    designationErrorSection.innerHTML = '*required';
    form.title.focus();
    return;
  }
  form.title.value = form.title.value.trim();
  updateDesignation();
}

function updateDesignation() {
  let code = form.code.value;
  let title = form.title.value;

  let dataToSend = 'code='+encodeURI(code);
  dataToSend += '&title='+encodeURI(title);

  sendXHR('/stylethree/updateDesignation', 'POST', dataToSend)
  .then(response => {
    showNotification(
      'Designation updated.',
      'Ok',
      "/stylethree/Designations.html"
    );
    // alert('Designation updated')
    // window.location.href = "/stylethree/Designations.html";
  }).catch(err => {
    designationErrorFormSection.innerText = title + ' exists.';
  })

}