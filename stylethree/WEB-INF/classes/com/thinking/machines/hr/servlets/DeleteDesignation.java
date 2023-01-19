package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.thinking.machines.hr.dl.*;

public class DeleteDesignation extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      HttpSession session = request.getSession();
      if(session.getAttribute("userName") == null) {
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("Login.html");
        requestDispatcher.forward(request, response);
        return;
      }
    } catch (Exception e) {
      //do nothing
    }

    int code;
    try {
      code = Integer.parseInt(request.getParameter("code"));
    } catch(Exception e) {
      System.out.print("Error Page");
      return;
    }

    try {
      DesignationDAO designationDAO = new DesignationDAO();
      designationDAO.deleteByCode(code);
    } catch(DAOException daoException) {
      System.out.println(daoException);
      try {
        response.sendError(500, daoException.getMessage());
      } catch(Exception e) {
        // do nothing
      }
      return;
    }

    try {
      PrintWriter pw = response.getWriter();
      pw.print("Designation Deleted");
    } catch(Exception e) {
      System.out.println(e);
      // do nothing
    }

  }
}
