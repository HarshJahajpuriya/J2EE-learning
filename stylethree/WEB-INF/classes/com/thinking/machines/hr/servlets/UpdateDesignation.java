package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.thinking.machines.hr.dl.*;

public class UpdateDesignation extends HttpServlet {
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
      //do nothing
    }

    try {
      int code = Integer.parseInt(request.getParameter("code"));
      String title = request.getParameter("title");
      
      DesignationDAO designationDAO = null;
      DesignationDTO designationDTO = new DesignationDTO();
      try {
        designationDAO = new DesignationDAO();
        designationDTO.setCode(code);
        designationDTO.setTitle(title);
        designationDAO.update(designationDTO);;
      } catch (DAOException daoException) {
        PrintWriter pw = response.getWriter();
        pw.print(daoException.getMessage());
        response.sendError(500);
        System.out.println(daoException);
        return;
      }

    } catch (Exception e) {
      System.out.println(e);
    }
    

  }
}
