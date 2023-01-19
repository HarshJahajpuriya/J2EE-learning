package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;

public class GetEmployee extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) {

    try {
      HttpSession session = request.getSession();
      System.out.println(session.getAttribute("userName"));
      if(session.getAttribute("userName") == null) {
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("Login.html");
        requestDispatcher.forward(request, response);
        return;
      }
    } catch (Exception e) {
      System.out.println("Error page");
      //do nothing
    }

    String employeeId = null;

    try {
      employeeId = request.getParameter("employeeId");
    } catch(Exception e) {
      System.out.println("Error page");
    }

    EmployeeDAO employeeDAO = null;
    EmployeeDTO employee = null;
    try {
      employeeDAO = new EmployeeDAO();
      employee = employeeDAO.getByEmployeeId(employeeId);
    } catch (DAOException daoException) {
      try {
        response.sendError(500, "Cannot access database");
      } catch (Exception e) {
        // do nothing
      }
      System.out.println(daoException);
    }
    try {
      PrintWriter pw = response.getWriter();
      pw.print(employee.getEmployeeId() + ",");
      pw.print(employee.getName() + ",");
      pw.print(employee.getDesignationCode() + ",");
      pw.print(employee.getDesignation() + ",");
      pw.print(employee.getDateOfBirth() + ",");
      pw.print(employee.getGender() + ",");
      pw.print(employee.getIsIndian() + ",");
      pw.print(employee.getBasicSalary() + ",");
      pw.print(employee.getPANNumber() + ",");
      pw.print(employee.getAadharCardNumber() + ":");
      
    } catch (IOException ioe) {
      // Do nothing
    }
  }
}
