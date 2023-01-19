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
HttpSession session = request.getSession(false);
String name = "";
String city = "";
if(session != null)
{
name = (String)session.getAttribute("name");
city = (String)session.getAttribute("city");
}
System.out.println("Saved Data ");
System.out.println("Name : " + name);
System.out.println("City : " + city);
response.setContentType("text/html");
PrintWriter pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> Fifth Application </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("Name : " + name);
pw.println("<br>");
pw.println("City : " + city);
pw.println("<br>");
pw.println("<form action='/five/index.html'> ");
pw.println("<button type='submit'> Ok </button>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e); 	// remove after testing
}
}
}