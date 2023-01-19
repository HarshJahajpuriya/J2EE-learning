function deleteDesignation(code) {
  let dataToSend = {
    code  
  };
  sendXHR("/stylefour/deleteDesignation", 'POST', dataToSend)
  .then(response => {
    if(response.status === 'UNAUTHORIZED') {
      window.location.href = response.data;
    } else {
      showNotification(
        response.data,
        'Ok',
        '/stylefour/Designations.html'
      )
    }

    // alert('Designation Deleted');
  }).catch(err => {
    console.log(err)
    
    // alert('Cannot Delete Designation, beacause it is alloted to an employee');
    // window.location.href = '/stylefour/Designations.html';
  })
}