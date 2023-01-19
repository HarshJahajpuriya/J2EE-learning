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
  var dataToSend = { 
    'title': designationTitle 
  };
  sendXHR("/stylefour/addDesignation", 'POST', dataToSend)
    .then(response => {
      console.log(response);
      if(response.status === 'UNAUTHORIZED') {
        window.location.href = response.data;
      } else if(response.status === 'SUCCESS') {

        showNotification(
          response.data +" Add more?",
          'Ok',
          '/stylefour/DesignationAddForm.html',
          'No',
          '/stylefour/Designations.html'
        )
      } else if(response.status === 'DATABASE_ERROR') {
        addDesignationErrorSection.innerHTML = response.data;
      }

    }).catch(err => {
      console.log(err)
    })
}