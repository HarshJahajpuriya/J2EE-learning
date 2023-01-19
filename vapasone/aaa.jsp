<!DOCTYPE HTML>
<html lang='en'>
<head>
<meta charset='utf-8'>
<title>First Application (Using JSP)</title>
</head>
<body>
<%
String nn = request.getParameter("name");
String cc = request.getParameter("city");
%>
<b>Name:</b> <%= nn %>
<br>
<%
out.println("<b>City: </b>"+cc);
System.out.println("Data Arrived");
System.out.println("Name : "+nn);
System.out.println("City : "+cc);
%>
</body>
</html>