package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;

public class ServletOne extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response) 
{
System.out.println("Here");
try 
{
DesignationDAO designationDAO = new DesignationDAO();
List<DesignationDTO> designations = designationDAO.getAll();
PrintWriter pw = response.getWriter();
for(int i=0; i<designations.size(); i++) 
{
pw.print(designations.get(i).getCode()+","+designations.get(i).getTitle());
if(i<designations.size()-1) pw.print(",");
}
} catch(Exception e) {
try 
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
} catch(Exception ee) {
// do nothing
}
}
}

public void doPost(HttpServletRequest request, HttpServletResponse response) {
try {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
} catch(Exception e) {
// do nothing
}
}

}