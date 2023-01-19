package com.thinking.machines.hr.servlets;

import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DeleteDesignation extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
PrintWriter pw = null;
int code = -1;
try 
{
code = Integer.parseInt(request.getParameter("code"));
} catch(NumberFormatException nfe)
{
sendBackView(response);
return;
}
try 
{
pw = response.getWriter();
response.setContentType("text/html");
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.deleteByCode(code);
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> CRUD Application (Style One) </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- main starts here -->");
pw.println("<div style='width:90hw;height:94vh;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a>");
pw.println("<div style='height:60px;font-size:40px;margin:5px;margin-bottom:0'>Thinking Machines</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:74vh;margin:5px;border:1px solid white'>");
pw.println("<div style='width:auto;height:70.8vh;margin:5px;border:1px solid black;float:left;padding:4px 8px'>");
pw.println("<a href='/styleone/designationsView'> Designations </a> <br>");
pw.println("<a href='/styleone/employeesView'> Employees </a>");
pw.println("</div>");
pw.println("<div style='width:auto;height:72vh;margin:5px;margin-left:110px;border:1px solid black;'>");
pw.println("<h3> Designation Deleted </h3>");
pw.println("<form action='/styleone/designationsView'>");
pw.println("<button type='submit'> Ok </button>");
pw.println("</form>");
pw.println("</div>");
pw.println("</div> <!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;margin-top:20px;border:1px solid white;font-size:20px;text-align:center'>");
pw.println("&copyThinking Machines");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- main ends here -->");
pw.println("</body>");
pw.println("</html>");
} catch(DAOException daoException) 
{
// Designation alloted to an employee
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> CRUD Application (Style One) </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- main starts here -->");
pw.println("<div style='width:90hw;height:94vh;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a>");
pw.println("<div style='height:60px;font-size:40px;margin:5px;margin-bottom:0'>Thinking Machines</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:74vh;margin:5px;border:1px solid white'>");
pw.println("<div style='width:auto;height:70.8vh;margin:5px;border:1px solid black;float:left;padding:4px 8px'>");
pw.println("<a href='/styleone/designationsView'> Designations </a> <br>");
pw.println("<a href='/styleone/employeesView'> Employees </a>");
pw.println("</div>");
pw.println("<div style='width:auto;height:72vh;margin:5px;margin-left:110px;border:1px solid black;'>");
pw.println("<h3> "+daoException.getMessage()+" </h3>");
pw.println("<form action='/styleone/designationsView'>");
pw.println("<button type='submit'> Ok </button>");
pw.println("</form>");
pw.println("</div>");
pw.println("</div> <!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;margin-top:20px;border:1px solid white;font-size:20px;text-align:center'>");
pw.println("&copyThinking Machines");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- main ends here -->");
pw.println("</body>");
pw.println("</html>");
sendBackView(response);
} catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
{
this.doGet(request, response);
}

private void sendBackView(HttpServletResponse response) 
{
try 
{
DesignationDAO designationDAO = new DesignationDAO();
List<DesignationDTO> designations = designationDAO.getAll();
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> CRUD Application (Style One) </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- main starts here -->");
pw.println("<div style='width:90hw;height:94vh;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a>");
pw.println("<div style='height:60px;font-size:40px;margin:5px;margin-bottom:0'>Thinking Machines</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:74vh;margin:5px;border:1px solid white'>");
pw.println("<div style='width:auto;height:70.8vh;margin:5px;border:1px solid black;float:left;padding:4px 8px'>");
pw.println("<b> Designations </b> <br>");
pw.println("<a href='/styleone/employeesView'> Employees </a>");
pw.println("</div>");
pw.println("<div style='width:auto;height:72vh;margin:5px;margin-left:110px;border:1px solid black;overflow:scroll;'>");
pw.println("<table border='1'>");
pw.println("<h2>Designations</h2>");
pw.println("<tr>");
pw.println("<th colspan='4' style='text-align:right;padding:4px;'>");
pw.println("<a href='/styleone/AddDesignation.html'>Add Designation</a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px'>S.No.</th>");
pw.println("<th style='width:200px'>Designation</th>");
pw.println("<th style='width:100px'>Edit</th>");
pw.println("<th style='width:100px'>Delete</th>");
pw.println("</tr>");
DesignationDTO designationDTO;
int code;
String title;
for(int i=0; i<designations.size(); i++)
{
designationDTO = designations.get(i);
code = designationDTO.getCode();
title = designationDTO.getTitle();
pw.println("<tr>");
pw.println("<td style='text-align:right'>"+(i+1)+"</td>");
pw.println("<td>"+title+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editDesignation?code=" + designationDTO.getCode() + "'>Edit</a></td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteDesignation?code=" + designationDTO.getCode() + "'>Delete</a></td>");
pw.println("</tr>");
}
pw.println("</table>");
pw.println("</div>");
pw.println("</div> <!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;margin-top:20px;border:1px solid white;font-size:20px;text-align:center'>");
pw.println("&copyThinking Machines");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- main ends here -->");
pw.println("</body>");
pw.println("</html>");
} catch(DAOException daoe)
{
System.out.println(daoe);	// remove after testing
} catch(Exception e) 
{
System.out.println(e);		// remove after testing
}
}
}