<jsp:useBean id='administratorBean' scope='request' class='com.thinking.machines.hr.beans.AdministratorBean' />
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<!DOCTYPE HTML>
<html lang='en'>
<head>
<meta charset='utf-8'>
<title> CRUD Application (Style Two) </title>
<link rel='stylesheet' href='/styletwo/css/styles.css'>
<link rel='stylesheet' href='/styletwo/css/LoginForm.css'>
<script src='js/LoginForm.js'></script>
</head>
<body>
<!-- main starts here -->
<div class='main-section'>
<!-- header starts here -->
<div class='header'>
<a href='/styletwo/index.html'><img src='/styletwo/images/logo.png' class='logo'></a>
<div class='brand-name'>Thinking Machines</div>
</div> <!-- header ends here -->
<!-- content-section starts here -->
<div class='content-section'>
<!-- right-section starts here -->
<div class='content-right-section'>
<form method='POST' class='loginForm' action='Login.jsp' onSubmit='return validateLoginForm(this)'>
<span class='error'>${errorBean.error}</span>
<table>
<tr>
<td>
<label> User name </label>
</td>
<td>
<input type='text' id='userName' name='userName' value='${administratorBean.userName}'><br>
<span id='userNameErrorSection' class='error'></span>
<br>
</td>
</tr>
<tr>
<td>
<label>Password </label>
</td>
<td>
<input type='password' id='password' name='password' value='${administratorBean.password}'><br>
<span id='passwordErrorSection' class='error'></span<br><br>
</td>
</tr>
</table>
<div style="display:flex">
<div style="flex:1"></div>
<button style="flex:1" type='submit'>Login</button>
<div style="flex:1"></div>
</div>
</form>
<!-- right-section ends here -->
</div> <!-- content-section ends here -->
<!-- footer starts here -->
<div class='footer'>
&copyThinking Machines
</div> <!-- footer ends here -->
</div> <!-- main ends here -->
</body>
</html>
