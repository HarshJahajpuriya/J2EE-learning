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

  let dataToSend = {
    code, 
    title
  }

  sendXHR('/stylefour/updateDesignation', 'POST', dataToSend)
  .then(response => {

    if(response.status === 'SUCCESS') {
      showNotification(
        response.data,
        'Ok',
        "/stylefour/Designations.html"
      );
    } else if (response.status === 'UNAUTHORIZED') {
      window.location.href = response.data;
    } else if(response.data === 'DATABASE_ERROR') {
      designationErrorFormSection.innerHTML = response.data;
    }


    // alert('Designation updated')
  }).catch(err => {
    // designationErrorFormSection.innerText = title + ' exists.';
    console.log(err)
  })

}