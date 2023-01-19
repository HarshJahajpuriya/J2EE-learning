const addDesignationErrorSection = document.getElementById("addDesignationErrorSection");
const designationErrorSection = document.getElementById("designationErrorSection")
const form = document.getElementById("designationAddForm");

function validateForm() {
  designationErrorSection.innerHTML = '';
  addDesignationErrorSection.innerHTML = '';
  if (form.title.value.trim().length == 0) {
    designationErrorSection.innerHTML = '*required';
    form.title.focus();
    return false;
  }
  form.title.value = form.title.value.trim();
  addDesignation(form.title.value);
}

function addDesignation(designationTitle) {
  var dataToSend = 'title='+encodeURI(designationTitle);
  sendXHR("/stylethree/addDesignation", 'POST', dataToSend)
  .then(response => {
    showNotification(
      'Designation: ' + response +". Add more?",
      'Ok',
      '/stylethree/DesignationAddForm.html',
      'No',
      '/stylethree/Designations.html'
    )
    // alert('Designation: ' + response);
    // if(confirm('Add More?'))
    //   form.title.value = '';
    // else 
    //   window.location.href = '/stylethree/Designations.html';

  }).catch(err => {
    addDesignationErrorSection.innerText = designationTitle+' exists.'
  })
}