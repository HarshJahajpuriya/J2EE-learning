function deleteDesignation(code) {
  let dataToSend = 'code='+encodeURI(code);
  sendXHR("/stylethree/deleteDesignation", 'POST', dataToSend)
  .then(response => {
    showNotification(
      'Designation Deleted',
      'Ok',
      '/stylethree/Designations.html'
    )
    // alert('Designation Deleted');
    // window.location.href = '/stylethree/Designations.html';
  }).catch(err => {
    console.log('Here')
    showNotification(
      'Cannot Delete Designation, beacause it is alloted to an employee', 
      'Ok', 
      '/stylethree/Designations.html'
    );
    // alert('Cannot Delete Designation, beacause it is alloted to an employee');
    // window.location.href = '/stylethree/Designations.html';
  })
}