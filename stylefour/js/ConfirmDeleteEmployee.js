function deleteEmployee(employeeId) {
  let dataToSend = 'employeeId='+encodeURI(employeeId);
  sendXHR("/stylefour/deleteEmployee", 'POST', dataToSend)
  .then(response => {
    showNotification(
      'Employee Deleted',
      'Ok',
      '/stylefour/Employees.html'
    );
    // alert('Employee Deleted');
    // window.location.href = '/stylefour/Employees.html';
  }).catch(err => {
    console.log(err)
  })
}