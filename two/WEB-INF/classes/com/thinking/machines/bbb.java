package com.thinking.machines;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class bbb extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) 
{
try 
{
String name = request.getParameter("name");
String city = request.getParameter("city");
System.out.println("Data Arrived: ");
System.out.println("Name : " + name);
System.out.println("City : " + city);
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset = 'utf-8'>");
pw.println("<title> Second Application </title>");
pw.println("<script>");
pw.println("function validateForm()");
pw.println("{");
pw.println("var mRadio = document.getElementById('MRadio');");
pw.println("var fRadio = document.getElementById('FRadio');");
pw.println("if(mRadio.checked == false && fRadio.checked == false) ");
pw.println("{");
pw.println("alert('Please Select Gender');");
pw.println("return false;");
pw.println("}");
pw.println("return true;");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<form action='/two/ccc' onsubmit='return validateForm()'>");
pw.println("<input type='hidden' id='name' name='name' value='" + name + "'>");
pw.println("<input type='hidden' id='city' name='city' value='" + city + "'>");
pw.println("Gender : ");
pw.println("<input type='radio' id='MRadio' name='gender' value='M'> M");
pw.println("&nbsp&nbsp&nbsp");
pw.println("<input type='radio' id='FRadio' name='gender' value='F'> F");
pw.println("<br>");
pw.println("<button type='submit'> Save </button>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
} catch(Exception e) 
{
System.out.println(e);
}
}
}