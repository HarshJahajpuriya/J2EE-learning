package com.thinking.machines;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;

public class bbb extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try 
{
String name = request.getParameter("name");
String city = request.getParameter("city");
System.out.println("Data arrived");
System.out.println("Name : " + name);
System.out.println("City : " + city);
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> Third Application </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<center>");
pw.println("Name : " + name);
pw.println("<br>");
pw.println("City : " + city);
pw.println("<br>");
pw.println("<a href='/three/ccc?name=" + URLEncoder.encode(name) + "&city=" + URLEncoder.encode(city) + "'> Save </a>");
pw.println("</center>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e);	//remove after testing
}
}
}