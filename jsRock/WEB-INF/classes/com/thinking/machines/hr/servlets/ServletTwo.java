package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;

public class ServletTwo extends HttpServlet 
{

public void doPost(HttpServletRequest request, HttpServletResponse response) 
{
try 
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
} catch(Exception e)
{
// do nothing
}
}

public void doGet(HttpServletRequest request, HttpServletResponse response) 
{
try 
{
int code = Integer.parseInt(request.getParameter("code"));
DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designation = designationDAO.getByCode(code);
PrintWriter pw = response.getWriter();
pw.print(designation.getCode()+","+designation.getTitle());
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