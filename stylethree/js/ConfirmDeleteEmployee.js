function deleteEmployee(employeeId) {
  let dataToSend = 'employeeId='+encodeURI(employeeId);
  sendXHR("/stylethree/deleteEmployee", 'POST', dataToSend)
  .then(response => {
    showNotification(
      'Employee Deleted',
      'Ok',
      '/stylethree/Employees.html'
    );
    // alert('Employee Deleted');
    // window.location.href = '/stylethree/Employees.html';
  }).catch(err => {
    console.log(err)
  })
}