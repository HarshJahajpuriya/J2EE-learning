const form = document.getElementById('employeeEditForm');
let designations= [];

function validateForm() {
  var isValid = true;
  var inFocus = null;

  form.name.value = form.name.value.trim();
  document.getElementById('nameErrorSection').innerHTML = '';
  if (form.name.value.length == 0) {
    isValid = false;
    inFocus = form.name;
    document.getElementById('nameErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Name Required';
  }

  document.getElementById('designationCodeErrorSection').innerHTML = '';
  if (form.designationCode.value == -1) {
    isValid = false;
    if (inFocus == null) inFocus = form.designationCode;
    document.getElementById('designationCodeErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Select Designation';
  }

  document.getElementById('dateOfBirthErrorSection').innerHTML = '';
  if (form.dateOfBirth.value.length == 0) {
    isValid = false;
    if (inFocus == null) inFocus = form.dateOfBirth;
    document.getElementById('dateOfBirthErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Date of Birth Required';
  }

  document.getElementById('genderErrorSection').innerHTML = '';
  if (form.male.checked == false && form.female.checked == false) {
    isValid = false;
    document.getElementById('genderErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Select Gender';
  }

  document.getElementById('basicSalaryErrorSection').innerHTML = '';
  form.basicSalary.value = form.basicSalary.value.trim();
  if (form.basicSalary.value.length == 0) {
    isValid = false;
    if (inFocus == null) inFocus = form.basicSalary;
    document.getElementById('basicSalaryErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Basic Salary Required';
  } else {
    var e = '1234567890.';
    var validBasicSalary = true;
    for (var i = 0; i < basicSalary.value.length; i++) {
      if (e.indexOf(basicSalary.value.charAt(i)) == -1) {
        isValid = false;
        if (inFocus == null) inFocus = form.basicSalary;
        document.getElementById('basicSalaryErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid Basic Salary';
        validBasicSalary = false;
        break;
      }
    }
    if (validBasicSalary) {
      var dot = basicSalary.value.indexOf('.');
      if (dot != -1) {
        var fractionalPartLength = basicSalary.value.length - (dot + 1);
        if (fractionalPartLength > 2) {
          isValid = false;
          if (inFocus == null) inFocus = form.basicSalary;
          document.getElementById('basicSalaryErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid Basic Salary';
        }
      }
    }
  }

  form.panNumber.value = form.panNumber.value.trim();
  document.getElementById('panNumberErrorSection').innerHTML = '';
  if (form.panNumber.value.length == 0) {
    isValid = false;
    if (inFocus == null) inFocus = form.panNumber;
    document.getElementById('panNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;PAN Number Required';
  } else if (form.panNumber.value.length != 10) {
    isValid = false;
    if (inFocus == null) inFocus = form.panNumber;
    document.getElementById('panNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid PAN Number';
  }

  form.aadharCardNumber.value = form.aadharCardNumber.value.trim();
  document.getElementById('aadharCardNumberErrorSection').innerHTML = '';
  if (form.aadharCardNumber.value.length == 0) {
    isValid = false;
    if (inFocus == null) inFocus = form.aadharCardNumber;
    document.getElementById('aadharCardNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Aadhar Card Number Required';
  } else if (form.aadharCardNumber.value.length != 12) {
    isValid = false;
    if (inFocus == null) inFocus = form.aadharCardNumber;
    document.getElementById('aadharCardNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid Aadhar Card Number';
  }

  if(inFocus)
    inFocus.focus();
  if(isValid) {
    updateEmployee();
  }
}

function updateEmployee() {
  let dataToSend = "employeeId="+encodeURI(form.employeeId.value);
  dataToSend += "&name="+encodeURI(form.name.value);
  dataToSend += "&designationCode="+encodeURI(form.designationCode.value);
  dataToSend += "&dateOfBirth="+encodeURI(form.dateOfBirth.value);
  dataToSend += "&gender="+encodeURI(form.gender.value);
  dataToSend += "&isIndian="+encodeURI(form.isIndian.value);
  dataToSend += "&basicSalary="+encodeURI(form.basicSalary.value);
  dataToSend += "&panNumber="+encodeURI(form.panNumber.value);
  dataToSend += "&aadharCardNumber="+encodeURI(form.aadharCardNumber.value);

  sendXHR("/stylefour/updateEmployee", 'POST', dataToSend)
  .then(response => {
    showNotification(
      'Employee updated',
      'Ok',
      '/stylefour/Employees.html'
    )
    // alert("Employee updated");
    // window.location.href = '/stylefour/Employees.html'
  }).catch(err => {
    console.log(err);
    redirect('/stylefour/Employees.html');
  })
}

sendXHR("/stylefour/getDesignations", 'GET')
.then(response => {
  let desginationStr = response.split(':');

  for (str of desginationStr) {
    let designation = {};
    designation.code = str.split(',')[0];
    designation.title = str.split(',')[1];
    designations.push(designation);
  }
  designations.pop();
  fillDesignations();

  sendXHR("/stylefour/getEmployee", 'POST', "employeeId="+encodeURI(employeeId))
  .then(response => {
    console.log(form);
    let splits = response.split(",");
    form.employeeId.value = splits[0];
    form.name.value = splits[1];
    form.designationCode.value = splits[2];
    form.dateOfBirth.value = splits[4];
    if(splits[5] === 'M') {
      document.getElementById("male").checked = true;
    } else {
      document.getElementById("female").checked = true;
    }
    if(splits[6] === 'true') {
      document.getElementById("isIndian").checked = true;
    }

    form.basicSalary.value = splits[7];
    form.panNumber.value = splits[8];
    form.aadharCardNumber.value = splits[9].replace(":","");
    console.log(splits);

  }).catch(err => {
    console.log(err)
  })

}).catch(err => {
  console.log(err);
})

function fillDesignations() {
  for(let designation of designations)  {
    form.designationCode.insertAdjacentHTML('beforeend', createSelectOption(designation));
  }
}

function createSelectOption(designation) {
  return `
    <option value='${designation.code}'>${designation.title}</option>
  `
}

