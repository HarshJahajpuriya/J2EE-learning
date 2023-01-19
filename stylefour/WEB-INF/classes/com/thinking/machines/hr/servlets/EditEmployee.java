package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.thinking.machines.hr.dl.*;

public class EditEmployee extends HttpServlet {
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

    String employeeId = "";
    try {
      employeeId = request.getParameter("employeeId");

    } catch(Exception e) {
      System.out.println("Error page");
      return;
    }

    EmployeeDAO employeeDAO = null;
    EmployeeDTO employeeDTO = null;
    try {
      employeeDAO = new EmployeeDAO();
      employeeDTO = employeeDAO.getByEmployeeId(employeeId);
    } catch(DAOException daoException) {
      try {
        response.sendError(500, "Cannot access database");
      } catch(IOException ioe) {
        // do nothing
      }
      return;
    }

    request.setAttribute("employeeId", employeeDTO.getEmployeeId());
    request.setAttribute("name", employeeDTO.getName());
    request.setAttribute("designationCode", employeeDTO.getDesignationCode());
    request.setAttribute("designation", employeeDTO.getDesignation());
    request.setAttribute("dateOfBirth", employeeDTO.getDateOfBirth());
    if(employeeDTO.getGender() == "F") {
      request.setAttribute("female", true);
      request.setAttribute("male", false);
    } else {
      request.setAttribute("male", true);
      request.setAttribute("female", false);
    }
    request.setAttribute("isIndian", employeeDTO.getIsIndian());
    System.out.println(employeeDTO.getIsIndian());
    request.setAttribute("basicSalary", employeeDTO.getBasicSalary());
    request.setAttribute("panNumber", employeeDTO.getPANNumber());
    request.setAttribute("aadharCardNumber", employeeDTO.getAadharCardNumber());

    RequestDispatcher requestDispatcher ;
    requestDispatcher = request.getRequestDispatcher("/EmployeeEditForm.jsp");
    try {
      System.out.println("Forwarded to EmployeeEditForm.jsp");
      requestDispatcher.forward(request, response);
    } catch(Exception e) {
      // do nothing
    }


  }
}
