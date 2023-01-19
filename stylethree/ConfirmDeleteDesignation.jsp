<!DOCTYPE HTML>
<html lang='en'>

<head>
  <meta charset='utf-8'>
  <title> CRUD Application (Style Three) </title>
  <link rel='stylesheet' href='/stylethree/css/styles.css'>
</head>

<body>
  <!-- main starts here -->
  <div class='main-section'>
    <!-- header starts here -->
    <div class='header'>
      <a href='/stylethree/index.html'><img src='/stylethree/images/logo.png' class='logo'></a>
      <div class='brand-name'>Thinking Machines</div>
      <div class='logout'><a href='/stylethree/logout'>Logout</a></div>
      <div class='user-logo'>
        <img src='/stylethree/images/user.png'></img> <span>admin</span>
      </div>
    </div> <!-- header ends here -->
    <!-- content-section starts here -->
    <div class='content-section'>
      <!-- left-section starts here -->
      <div class='content-left-section'>

        <b> Designations </b> <br>
        <a href='/stylethree/Employees.html'> Employees </a> <br>
        <br>
        <a href='/stylethree/index.html'> Home </a>


      </div>
      <!-- left-section ends here -->
      <!-- right-section starts here -->
      <div class='content-right-section'>

        <h3>Delete Designation (Module)</h3>
        <br>
        Delete <b>${title}</b> desingation? <br></br>
        <br>
        <form>
          <input type='hidden' id='code' name='code' value='${code}'>
          <button type='button' onclick="deleteDesignation(${code})"> Delete </button>
          <a href='/stylethree/Designations.html'>
            <button type='button'>Cancel</button>
          </a>
        </form>
        <!-- right-section ends here -->
      </div> <!-- content-section ends here -->
      <!-- footer starts here -->
      <div class='footer'>
        &copyThinking Machines
      </div> <!-- footer ends here -->
    </div> <!-- main ends here -->
</body>
<script src="/stylethree/js/ConfirmDeleteDesignation.js"></script>
<script src="/stylethree/js/utils.js"></script>
</html>