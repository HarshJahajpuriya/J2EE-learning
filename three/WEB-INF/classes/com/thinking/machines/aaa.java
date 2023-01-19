package com.thinking.machines;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class aaa extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) 
{
try 
{
String name = request.getParameter("name");
System.out.println("Data Arrived");
System.out.println("Name : " + name);
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> Third Application </title>");
pw.println("<script>");
pw.println("function validateForm(form)");
pw.println("{");
pw.println("if(form.city.value.length == 0) ");
pw.println("{");
pw.println("alert('City Required');");
pw.println("form.city.focus();");
pw.println("return false;");
pw.println("}");
pw.println("return true;");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<center>");
pw.println("<form action='/three/bbb' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' id='name' name='name' value='" + name + "'>");
pw.println("City: ");
pw.println("<input type='text' id='city' name='city'>");
pw.println("<br>");
pw.println("<button type='submit'> Ok </button>");
pw.println("</form>");
pw.println("</center>");
pw.println("</body>");
pw.println("</html>");
} catch(Exception e) 
{
System.out.println(e); 	// remove after testing
}
}
}