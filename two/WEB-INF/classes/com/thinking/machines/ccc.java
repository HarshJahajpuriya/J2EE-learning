package com.thinking.machines;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ccc extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) 
{
try 
{
String name = request.getParameter("name");
String city = request.getParameter("city");
String gender = request.getParameter("gender");
System.out.println("Data Arrived :");
System.out.println("Name : " + name);
System.out.println("City : " + city);
System.out.println("Gender : " + gender);
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> Second Application </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("Data Arrived:");
pw.println("<br>");
pw.println("Name : " + name);
pw.println("<br>");
pw.println("City : " + city);
pw.println("<br>");
pw.println("Gender : " + gender);
pw.println("<br>");
pw.println("<form action='/two'>");
pw.println("<button type='submit'> Ok </button>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
} catch (Exception e)
{
System.out.println(e); 	// remove after testing
}
}
}