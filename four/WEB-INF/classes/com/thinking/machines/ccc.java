package com.thinking.machines;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;

public class ccc extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try 
{
String name = "";
String city = "";
Cookie cookies[] = request.getCookies();
if(cookies!=null) 
{
for(int i=0; i<cookies.length; i++) 
{
Cookie c = cookies[i];
if(cookies[i].getName().equals("name")) 
{
name = URLDecoder.decode(cookies[i].getValue(), "utf-8");
}
if(cookies[i].getName().equals("city")) 
{
city = URLDecoder.decode(cookies[i].getValue(), "utf-8");
}
}
}
System.out.println("Saved Data : ");
System.out.println("Name : " + name);
System.out.println("City : " + city);
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> Fourth Application </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<center>");
pw.println("Name : " + name);
pw.println("<br>");
pw.println("City : " + city);
pw.println("<br>");
pw.println("<form action=/four/index.html>");
pw.println("<button type='submit'> Ok </button>");
pw.println("</form>");
pw.println("</center>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e) 
{
System.out.println(e);
}
}
}