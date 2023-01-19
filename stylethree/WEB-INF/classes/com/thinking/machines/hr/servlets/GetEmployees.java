package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import com.thinking.machines.hr.dl.*;

public class GetEmployees extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) {

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

    EmployeeDAO employeeDAO = null;
    List<EmployeeDTO> employees = new ArrayList<>();
    try {
      employeeDAO = new EmployeeDAO();
      employees = employeeDAO.getAll();
    } catch (DAOException daoException) {
      try {
        response.sendError(500, "Cannot access database");
      } catch(Exception e) {
        // do nothing
      }
      System.out.println(daoException);
    }
    try {
      PrintWriter pw = response.getWriter();
      for(EmployeeDTO employeeDTO: employees) {
        pw.print(employeeDTO.getEmployeeId()+",");
        pw.print(employeeDTO.getName()+",");
        pw.print(employeeDTO.getDesignationCode()+",");
        pw.print(employeeDTO.getDesignation()+",");
        pw.print(employeeDTO.getDateOfBirth()+",");
        pw.print(employeeDTO.getGender()+",");
        pw.print(employeeDTO.getIsIndian()+",");
        pw.print(employeeDTO.getBasicSalary()+",");
        pw.print(employeeDTO.getPANNumber()+",");
        pw.print(employeeDTO.getAadharCardNumber()+":");
      }
    } catch (IOException ioe) {
      // Do nothing
    }
  }
}
