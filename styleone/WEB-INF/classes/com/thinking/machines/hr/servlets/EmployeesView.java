package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.thinking.machines.hr.dl.*;
import java.text.*;
import java.io.*;

public class EmployeesView extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try 
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
EmployeeDAO employeeDAO;
employeeDAO = new EmployeeDAO();
List <EmployeeDTO> employees = employeeDAO.getAll();
PrintWriter pw;
response.setContentType("text/html");
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> CRUD Application (Style One) </title>");
pw.println("<script>");
pw.println("function Employee()");
pw.println("{");
pw.println("this.employeeId='';");
pw.println("this.name='';");
pw.println("this.designationCode=0;");
pw.println("this.desingation='';");
pw.println("this.dateOfBirth='';");
pw.println("this.gender='';");
pw.println("this.isIndian='';");
pw.println("this.basicSalary=0;");
pw.println("this.panNumber='';");
pw.println("this.aadharCardNumber='';");
pw.println("}");
pw.println("var selectedRow = null;");
pw.println("var employees = [];");
int i=0;
for(EmployeeDTO employee: employees)
{
pw.println("var employee = new Employee();");
pw.println("employee.employeeId='"+employee.getEmployeeId()+"';");
pw.println("employee.name='"+employee.getName()+"';");
pw.println("employee.designationCode="+employee.getDesignationCode()+";");
pw.println("employee.designation='"+employee.getDesignation()+"';");
pw.println("employee.dateOfBirth='"+simpleDateFormat.format(employee.getDateOfBirth())+"';");
pw.println("employee.gender='"+employee.getGender()+"';");
pw.println("employee.isIndian="+employee.getIsIndian()+";");
pw.println("employee.basicSalary="+employee.getBasicSalary().toPlainString()+";");
pw.println("employee.panNumber='"+employee.getPANNumber()+"';");
pw.println("employee.aadharCardNumber='"+employee.getAadharCardNumber()+"';");
pw.println("employees["+i+"] = employee;");
i++;
}
pw.println("function selectEmployee(row, employeeId)");
pw.println("{");
pw.println("if(selectedRow != null)");
pw.println("{");
pw.println("selectedRow.style.background='white';");
pw.println("selectedRow.style.color='black';");
pw.println("}");
pw.println("row.style.background='#7f8185';");
pw.println("row.style.color='white';");
pw.println("selectedRow = row;");
pw.println("for(var i=0; i<employees.length; i++)");
pw.println("{");
pw.println("if(employees[i].employeeId==employeeId)");
pw.println("{");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("var emp = employees[i];");
pw.println("document.getElementById('detailPanel_employeeId').innerHTML = emp.employeeId;");
pw.println("document.getElementById('detailPanel_name').innerHTML = emp.name;");
pw.println("document.getElementById('detailPanel_designation').innerHTML = emp.designation;");
pw.println("document.getElementById('detailPanel_dateOfBirth').innerHTML = emp.dateOfBirth;");
pw.println("document.getElementById('detailPanel_basicSalary').innerHTML = emp.basicSalary;");
pw.println("document.getElementById('detailPanel_isIndian').innerHTML = emp.isIndian===true?'Yes':'No';");
pw.println("document.getElementById('detailPanel_panNumber').innerHTML = emp.panNumber;");
pw.println("document.getElementById('detailPanel_aadharCardNumber').innerHTML = emp.aadharCardNumber;");
pw.println("document.getElementById('detailPanel_gender').innerHTML = emp.gender;");
pw.println("}");
pw.println("</script>");
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
pw.println("<b> Employees </b>");
pw.println("</div>");
pw.println("<!-- right section starts here -->");
pw.println("<div style='width:auto;height:71.46vh;margin:5px;margin-left:110px;border:1px solid black;'>");
pw.println("<!-- right section upper part starts here -->");
pw.println("<div style='width:auto;height:45vh;margin:5px;border:1px solid black;overflow:scroll;padding:5px'>");
pw.println("<table border='1'>");
pw.println("<h2>Designations</h2>");
pw.println("<tr>");
pw.println("<th colspan='6' style='text-align:right;padding:4px;'>");
pw.println("<a href='/styleone/getAddEmployeeForm'>Add Employee</a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px'>S.No.</th>");
pw.println("<th style='width:200px'>Id.</th>");
pw.println("<th style='width:200px'>Name</th>");
pw.println("<th style='width:200px'>Designation</th>");
pw.println("<th style='width:100px'>Edit</th>");
pw.println("<th style='width:100px'>Delete</th>");
pw.println("</tr>");
i=1;
for(EmployeeDTO employee: employees)
{
pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this, \""+employee.getEmployeeId()+"\")'>");
pw.println("<td style='text-align:right'>"+i+"</td>");
pw.println("<td>"+employee.getEmployeeId()+"</td>");
pw.println("<td>"+employee.getName()+"</td>");
pw.println("<td>"+employee.getDesignation()+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editEmployee?employeeId="+employee.getEmployeeId()+"'>Edit</a></td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteEmployee?employeeId="+employee.getEmployeeId()+"'>Delete</a></td>");
pw.println("</tr>");
i++;
}
pw.println("</table>");
pw.println("</div> <!-- right section upper part ends here -->");
pw.println("<!-- right section lower part starts here -->");
pw.println("<div style='width:auto;height:20vh;margin:5px;border:1px solid black;padding:5px;'>");
pw.println("<label style='background-color:gray;color:white;padding: 0 5px'>Details: -</label>");
pw.println("<table style='width:100%'>");
pw.println("<tr>");
pw.println("<td>Employee Id: <span id='detailPanel_employeeId'></span></td>");
pw.println("<td>Name: <span id='detailPanel_name'></span></td>");
pw.println("<td>Designation: <span id='detailPanel_designation'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date Of Birth: <span id='detailPanel_dateOfBirth'></span></td>");
pw.println("<td>Gender: <span id='detailPanel_gender'></span></td>");
pw.println("<td>Is Indian: <span id='detailPanel_isIndian'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary: <span id='detailPanel_basicSalary'></span></td>");
pw.println("<td>Pan Number: <span id='detailPanel_panNumber'></span></td>");
pw.println("<td>Aadhar Card Number: <span id='detailPanel_aadharCardNumber'></span></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div> <!-- right section lower part ends here -->");
pw.println("</div> <!-- right section ends here -->");
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
}catch(Exception exception)
{
System.out.println(exception);	// remove after testing
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request, response);
}
}
