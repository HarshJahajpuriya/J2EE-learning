<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/Employees.js'></script>
<!-- <%-- <script src='/styletwo/js/Employees.js'></script> --%> -->
<!-- <jsp:include page='/EmployeesJS.jsp' />  -->

<link rel='stylesheet' href='/styletwo/css/Employees.css'>

<script>
  window.addEventListener('load', populateEmployees);

  function populateEmployees() {
    var employeeGridTable = document.getElementById('employeeGridTable')
    var employeeGridBody = employeeGridTable.getElementsByTagName("tbody")[1];
    var rowTemplate = employeeGridBody.getElementsByTagName("tr")[0];
    rowTemplate.remove();
    
    var dynamicRowTemplateCells, dynamicRowTemplate, placeHolderFor;
    for(var i=0; i<employees.length; i++) {
      dynamicRowTemplate = rowTemplate.cloneNode(true);
      dynamicRowTemplateCells = dynamicRowTemplate.getElementsByTagName("td");
      placeHolderFor = "";
      for(var j=0; j<dynamicRowTemplateCells.length; j++) {
        placeHolderFor = dynamicRowTemplateCells[j].getAttribute("placeHolder");
        if(placeHolderFor == null) continue;
        else if(placeHolderFor == "serialNumber") dynamicRowTemplateCells[j].innerHTML = (i+1);
        else if(placeHolderFor == "employeeId") dynamicRowTemplateCells[j].innerHTML = employees[i].employeeId;
        else if(placeHolderFor == "name") dynamicRowTemplateCells[j].innerHTML = employees[i].name;
        else if(placeHolderFor == "designation") dynamicRowTemplateCells[j].innerHTML = employees[i].designation;
        else if(placeHolderFor == "designationCode") dynamicRowTemplateCells[j].innerHTML = employees[i].designationCode;
        else if(placeHolderFor == "dateOfBirth") dynamicRowTemplateCells[j].innerHTML = employees[i].dateOfBirth;
        else if(placeHolderFor == "gender") dynamicRowTemplateCells[j].innerHTML = employees[i].gender;
        else if(placeHolderFor == "isIndian") dynamicRowTemplateCells[j].innerHTML = employees[i].isIndian;
        else if(placeHolderFor == "panNumber") dynamicRowTemplateCells[j].innerHTML = employees[i].panNumber;
        else if(placeHolderFor == "aadharCardNumber") dynamicRowTemplateCells[j].innerHTML = employees[i].aadharCardNumber;
        else if(placeHolderFor == "editOption") dynamicRowTemplateCells[j].innerHTML = "<a href='/styletwo/editEmployee?employeeId="+employees[i].employeeId+"'>Edit</a>";
        else if(placeHolderFor == "deleteOption") dynamicRowTemplateCells[j].innerHTML = "<a href='/styletwo/confirmDeleteEmployee?employeeId="+employees[i].employeeId+"'>Delete</a>";
      }
      dynamicRowTemplate.addEventListener('click', addSelectEmployeeFunction(dynamicRowTemplate, employees[i].employeeId));
      employeeGridBody.appendChild(dynamicRowTemplate);
    }

  }

  var selectedRow = null;
  function addSelectEmployeeFunction(row, employeeId) {
    return function() {
      selectEmployee(row, employeeId);
    }
  }

</script>


<!-- right section upper part starts here -->
<div class='employees-grid'>
<h2>Employees</h2>
<table border='1' id="employeeGridTable">
<tr>
<th colspan='6' class='employee-table-header'>
<a href='/styletwo/getAddEmployeeForm'>Add Employee</a>
</th>
</tr>
<tr>
<th class='employee-table-sno-column'>S.No.</th>
<th class='employee-table-id-column'>Id.</th>
<th class='employee-table-name-column'>Name</th>
<th class='employee-table-designation-column'>Designation</th>
<th class='employee-table-editOption-column'>Edit</th>
<th class='employee-table-deleteOption-column'>Delete</th>
</tr>

<tbody>
  <tr style='cursor:pointer'>
  <td placeHolder="serialNumber"  style='text-align:right'>a</td>
  <td placeHolder="employeeId"></td>
  <td placeHolder="name"></td>
  <td placeHolder="designation"></td>
  <td placeHolder="editOption" style='text-align:center'></td>
  <td placeHolder="deleteOption" style='text-align:center'></td>
  </tr>
</tbody>


</table>
</div> <!-- right section upper part ends here -->
<!-- right section lower part starts here -->
<div class='employee-detail-section'>
<label class='employee-detail-section-label'>Details: -</label>
<table class='employee-detail-table'>
<tr>
<td>Employee Id: <span id='detailPanel_employeeId'></span></td>
<td>Name: <span id='detailPanel_name'></span></td>
<td>Designation: <span id='detailPanel_designation'></span></td>
</tr>
<tr>
<td>Date Of Birth: <span id='detailPanel_dateOfBirth'></span></td>
<td>Gender: <span id='detailPanel_gender'></span></td>
<td>Is Indian: <span id='detailPanel_isIndian'></span></td>
</tr>
<tr>
<td>Basic Salary: <span id='detailPanel_basicSalary'></span></td>
<td>Pan Number: <span id='detailPanel_panNumber'></span></td>
<td>Aadhar Card Number: <span id='detailPanel_aadharCardNumber'></span></td>
</tr>
</table>
</div> <!-- right section lower part ends here -->

<jsp:include page='/MasterPageBottomSection.jsp'/>