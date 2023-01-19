<%@taglib uri='WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Guard>
<jsp:forward page="LoginForm.jsp" />
</tm:Guard>
<!DOCTYPE HTML>
<html lang='en'>
<head>
<meta charset='utf-8'>
<title> CRUD Application (Style Two) </title>
<link rel='stylesheet' href='/styletwo/css/styles.css'>
</head>
<body>
<!-- main starts here -->
<div class='main-section'>
<!-- header starts here -->
<div class='header'>
<a href='/styletwo/index.jsp'><img src='/styletwo/images/logo.png' class='logo'></a>
<div class='brand-name'>Thinking Machines</div>
<div class='logout'><a href='/styletwo/logout'>Logout</a></div>
<div class='user-logo'>
<img src='/styletwo/images/user.png'></img> <span>${userName}</span>
</div>
</div> <!-- header ends here -->
<!-- content-section starts here -->
<div class='content-section'>
<!-- left-section starts here -->
<div class='content-left-section'>
<tm:If condition='${module != DESIGNATION}'>
<a href='/styletwo/Designations.jsp'> Designations </a> <br>
</tm:If>
<tm:If condition='${module == DESIGNATION}'>
<b> Designations </b> <br>
</tm:If>
<tm:If condition='${module != EMPLOYEE}'>
<a href='/styletwo/Employees.jsp'> Employees </a> <br>
</tm:If>
<tm:If condition='${module == EMPLOYEE}'>
<b> Employee </b> <br>
</tm:If>
<tm:If condition='${module != HOME}'>
<br>
<a href='/styletwo/index.jsp'> Home </a>
</tm:If>

</div>
<!-- left-section ends here -->
<!-- right-section starts here -->
<div class='content-right-section'>
