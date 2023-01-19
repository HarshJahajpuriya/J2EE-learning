const employeesTableBodyRef = document.getElementById('employeesTableBody');

console.log('HEre')

var employees = [];
var selectedRow = null;

fetchEmployees();

function fetchEmployees() {
  employees = []
  sendXHR("/stylefour/getEmployees", 'GET')
    .then(response => {
      if(response.status === 'UNAUTHORIZED') {
        window.location.href = response.data;
      } else if(response.status === 'SUCCESS') {
        employees = response.data;
      }
      
      // for (str of employeeStrs) {
      //   let employee = {};
      //   employee.employeeId = str.split(',')[0];
      //   employee.name = str.split(',')[1];
      //   employee.designationCode = str.split(',')[2];
      //   employee.designation = str.split(',')[3];
      //   employee.dateOfBirth = str.split(',')[4];
      //   employee.gender = str.split(',')[5];
      //   employee.isIndian = str.split(',')[6];
      //   employee.basicSalary = str.split(',')[7];
      //   employee.panNumber = str.split(',')[8];
      //   employee.aadharCardNumber = str.split(',')[9];
      //   employees.push(employee);
      // }
      // employees.pop();
      
      showEmployees();
    }).catch(err => {
      console.log(err);
    })


}


function showEmployees() {
  employeesTableBodyRef.innerHTML = '';
  let index = 1;
  for (let employee of employees) {
    // console.log(employee)
    let htmlStr = `<tr style='cursor:pointer' onclick="selectEmployee(this, '${employee.employeeId}')">
                    <td style='text-align:right'>${index}</td>
                    <td>${employee.employeeId}</td>
                    <td>${employee.name}</td>
                    <td>${employee.designation}</td>
                    <td style='text-align:center'><a href="/stylefour/editEmployee?employeeId=${employee.employeeId}">Edit</a></td>
                    <td style='text-align:center'><a href="/stylefour/confirmDeleteEmployee?employeeId=${employee.employeeId}">Delete</a></td>
                  </tr>`
    index++;
    employeesTableBodyRef.insertAdjacentHTML('beforeend', htmlStr);
  }
}

function selectEmployee(row, employeeId) {
  if (selectedRow != null) {
    selectedRow.style.background = 'white';
    selectedRow.style.color = 'black';
  }
  row.style.background = '#7f8185';
  row.style.color = 'white';
  selectedRow = row;
  for (var i = 0; i < employees.length; i++) {
    if (employees[i].employeeId == employeeId) {
      break;
    }
  }
  var emp = employees[i];
  document.getElementById('detailPanel_employeeId').innerHTML = emp.employeeId;
  document.getElementById('detailPanel_name').innerHTML = emp.name;
  document.getElementById('detailPanel_designation').innerHTML = emp.designation;
  document.getElementById('detailPanel_dateOfBirth').innerHTML = emp.dateOfBirth;
  document.getElementById('detailPanel_basicSalary').innerHTML = emp.basicSalary;
  document.getElementById('detailPanel_isIndian').innerHTML = emp.isIndian === true ? 'Yes' : 'No';
  document.getElementById('detailPanel_panNumber').innerHTML = emp.panNumber;
  document.getElementById('detailPanel_aadharCardNumber').innerHTML = emp.aadharCardNumber;
  document.getElementById('detailPanel_gender').innerHTML = emp.gender;
}
