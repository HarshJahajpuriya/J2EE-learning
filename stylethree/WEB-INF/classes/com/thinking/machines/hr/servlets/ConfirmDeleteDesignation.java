package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.thinking.machines.hr.dl.*;

public class ConfirmDeleteDesignation extends HttpServlet {
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
      //do nothing
    }

    int code;
    try {
      code = Integer.parseInt(request.getParameter("code"));
    } catch (Exception e) { 
      System.out.println("Error page");
      return;
    }

    try {
      DesignationDAO designationDAO = new DesignationDAO();
      DesignationDTO designationDTO = designationDAO.getByCode(code);
      request.setAttribute("code", designationDTO.getCode());
      request.setAttribute("title", designationDTO.getTitle());
    } catch(DAOException daoException) {
      try {
        response.sendError(500, daoException.getMessage());
        return;
      } catch(Exception e) {
        // do nothing
      }
      return;
    }

    try {
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ConfirmDeleteDesignation.jsp");
      requestDispatcher.forward(request, response);
    } catch(Exception e) {
      // do nothing
    }

  }
}
