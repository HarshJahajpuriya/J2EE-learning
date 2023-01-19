package com.thinking.machines.hr.servlets;

import com.thinking.machines.hr.bl.*;
import com.thinking.machines.hr.beans.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeesJS extends HttpServlet
{

public void doGet(HttpServletRequest request, HttpServletResponse response) {
doPost(request, response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
{

try {
// The following is a very bad idea hence commented.
// File file = new File("c://tomcat9//webapps//styletwo//WEB-INF//js//EmployeesJS.js")
System.out.println("In Servlet EmployeesJS");
ServletContext servletContext = getServletContext();
File file = new File(servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"js"+File.separator+"Employees.js");
RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

PrintWriter pw = response.getWriter();

while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
pw.println(randomAccessFile.readLine());
}

EmployeeBL employeeBl = new EmployeeBL();
List<EmployeeBean> employees = employeeBl.getAll();
int i = 0;
for(EmployeeBean employeeBean: employees) {
pw.println("employee = new Employee();");
pw.println("employee.employeeId='"+employeeBean.getEmployeeId()+"'");
pw.println("employee.name='"+employeeBean.getName()+"'");
pw.println("employee.designationCode='"+employeeBean.getDesignationCode()+"'");
pw.println("employee.designation='"+employeeBean.getDesignation()+"'");
pw.println("employee.dateOfBirth='"+employeeBean.getDateOfBirth()+"'");
pw.println("employee.gender='"+employeeBean.getGender()+"'");
pw.println("employee.isIndian='"+employeeBean.getIsIndian()+"'");
pw.println("employee.basicSalary='"+employeeBean.getBasicSalary()+"'");
pw.println("employee.panNumber='"+employeeBean.getPanNumber()+"'");
pw.println("employee.aadharCardNumber='"+employeeBean.getAadharCardNumber()+"'");
pw.println("employees["+i+"] = employee;");
i++;
}

randomAccessFile.close();

} catch(Exception exception)
{
System.out.println(exception);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
try 
{
requestDispatcher.forward(request, response);
} catch(Exception e)
{
// Do Nothing
}
}
}
}