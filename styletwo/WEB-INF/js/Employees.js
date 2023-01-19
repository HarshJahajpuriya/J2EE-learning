function Employee()
{
this.employeeId='';
this.name='';
this.designationCode=0;
this.desingation='';
this.dateOfBirth='';
this.gender='';
this.isIndian='';
this.basicSalary=0;
this.panNumber='';
this.aadharCardNumber='';
}
var selectedRow = null;
var employees = [];
function selectEmployee(row, employeeId)
{
if(selectedRow != null)
{
selectedRow.style.background='white';
selectedRow.style.color='black';
}
row.style.background='#7f8185';
row.style.color='white';
selectedRow = row;
for(var i=0; i<employees.length; i++)
{
if(employees[i].employeeId==employeeId)
{
break;
}
}
var emp = employees[i];
document.getElementById('detailPanel_employeeId').innerHTML = emp.employeeId;
document.getElementById('detailPanel_name').innerHTML = emp.name;
document.getElementById('detailPanel_designation').innerHTML = emp.designation;
document.getElementById('detailPanel_dateOfBirth').innerHTML = emp.dateOfBirth;
document.getElementById('detailPanel_basicSalary').innerHTML = emp.basicSalary;
document.getElementById('detailPanel_isIndian').innerHTML = emp.isIndian===true?'Yes':'No';
document.getElementById('detailPanel_panNumber').innerHTML = emp.panNumber;
document.getElementById('detailPanel_aadharCardNumber').innerHTML = emp.aadharCardNumber;
document.getElementById('detailPanel_gender').innerHTML = emp.gender;
}
