package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.thinking.machines.hr.dl.*;

public class EditDesignation extends HttpServlet {
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

    try {
      int code = Integer.parseInt(request.getParameter("code"));
      
      DesignationDAO designationDAO = null;
      DesignationDTO designationDTO = null;
      try {
        designationDAO = new DesignationDAO();
        designationDTO = designationDAO.getByCode(code);
      } catch (DAOException daoException) {
        response.sendError(500, "Cannot access databse");
        return;
      }
      
      PrintWriter pw = null;
      try {
        // pw = response.getWriter();
        // pw.print("const code = " + designationDTO.getCode());
        // pw.print(designationDTO.getTitle());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/DesignationEditForm.jsp");
        request.setAttribute("code", designationDTO.getCode());
        request.setAttribute("title", designationDTO.getTitle());
        requestDispatcher.forward(request, response);

      } catch(Exception e) {
        // do nothing
      }

    } catch (Exception e) {
      System.out.println(e);
    }


  }  
}
