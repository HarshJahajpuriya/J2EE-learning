package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletThree extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) 
{
try 
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
} catch(Exception e) 
{
// do nothing
}
}

public void doPost(HttpServletRequest request, HttpServletResponse response) 
{
try 
{
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
int age = Integer.parseInt(request.getParameter("age"));
PrintWriter pw = response.getWriter();
pw.print(firstName+","+lastName+","+age);
} catch(Exception e) 
{
try 
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
} catch(Exception ee) 
{
// do nothing
}
}
}
}