package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;

import com.thinking.machines.hr.dl.DAOException;
import com.thinking.machines.hr.dl.EmployeeDAO;
import com.thinking.machines.hr.dl.EmployeeDTO;

public class DeleteEmployee extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("Deleting");
    try {
      HttpSession session = request.getSession();
      System.out.println(session.getAttribute("userName"));
      if(session.getAttribute("userName") == null) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.html");
        requestDispatcher.forward(request, response);
        return;
      }
    } catch(Exception e) {
      // do nothing
    } 

    String employeeId;
    try {
      employeeId = request.getParameter("employeeId");
    } catch(Exception e) {
      System.out.println("Error page");
      return;
    }

    try {
      EmployeeDAO employeeDAO = new EmployeeDAO();
      employeeDAO.deleteByEmployeeId(employeeId);
    } catch(DAOException daoException) {  
      System.out.println(daoException);
      try {
        response.sendError(500, daoException.getMessage());
      } catch(Exception e) {
        // do noting
      }
      return;
    }

    try {
      PrintWriter pw = response.getWriter();
      pw.print("Employee Deleted");
    } catch(Exception e) {
      System.out.println(e);
    }

  }
}
