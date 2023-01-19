<!DOCTYPE HTML>
<html lang='en'>

<head>
  <meta charset='utf-8'>
  <title> CRUD Application (Style three) </title>
  <link rel="stylesheet" href="/stylethree/css/styles.css">
</head>

<body>
  <!-- main starts here -->
  <div style='width:90hw;height:94vh;border:1px solid black'>
    <!-- header starts here -->
    <div style='width:90hw;height:auto;margin:5px;border:1px solid black'>
      <a href='/stylethree/index.html'><img src='/stylethree/images/logo.png' style='float:left'></a>
      <div style='height:60px;font-size:40px;margin:5px;margin-bottom:0'>Thinking Machines</div>
    </div> <!-- header ends here -->
    <!-- content-section starts here -->
    <div style='width:90hw;height:74vh;margin:5px;border:1px solid white'>
      <!-- left-section starts here -->
      <div style='width:auto;height:70.8vh;margin:5px;border:1px solid black;float:left;padding:4px 8px'>
        <a href='/stylethree/Designations.html'> Designations </a> <br>
        <b> Employees </b>
      </div>
      <!-- left-section ends here -->

      <!-- right-section starts here -->
      <div style='width:auto;height:72vh;margin:5px;margin-left:110px;border:1px solid black;'>
        <h3>Delete Employee (Module)</h3>
        <br>
        Are you sure you want to delete employee?<br>
        Name:
        <b>${name}</b><br>
        Designation:
        <b>${designation}</b><br>
        Date of birth:
        <b>${dateOfBirth}</b><br>
        Gender:
        <b>${gender}</b><br>
        Is Indian ?:
        <b>${isIndian}</b><br>
        Basic Salary:
        <b>${basicSalary}</b><br>
        PAN number:
        <b>${panNumber}</b><br>
        Aadhar card number:
        <b>${aadharCardNumber}</b><br>
        <br>
        <button type='button' onclick="deleteEmployee('${employeeId}')"> Yes </button>
        <a href='/stylethree/Employees.html'>
          <button type='button'> No </button>
        </a>
        </form>
      </div>
      <!-- right-section ends here -->

    </div> <!-- content-section ends here -->
    <!-- footer starts here -->
    <div
      style='width:90hw;height:auto;margin:5px;margin-top:20px;border:1px solid white;font-size:20px;text-align:center'>
      &copyThinking Machines
    </div> <!-- footer ends here -->
  </div> <!-- main ends here -->
</body>

<script src="/stylethree/js/ConfirmDeleteEmployee.js"></script>
<script src="/stylethree/js/utils.js"></script>

</html>