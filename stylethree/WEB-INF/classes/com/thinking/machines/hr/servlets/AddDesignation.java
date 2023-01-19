package com.thinking.machines.hr.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.*;

public class AddDesignation extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request,response);
  }

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

    String designationTitle = request.getParameter("title");

    try {

    DesignationDTO designationDTO = new DesignationDTO();
    designationDTO.setTitle(designationTitle);    

    DesignationDAO designationDAO = new DesignationDAO();
    designationDAO.add(designationDTO);
    } catch(DAOException daoException) {
      try {
        response.sendError(500, daoException.getMessage());
        System.out.print(daoException);
      } catch(Exception e) {
        // do nothing
      }
    }
    PrintWriter pw = null;
    try {
      pw = response.getWriter();
      pw.print(designationTitle+" added");
    } catch(IOException ioe) {
      // do nothing
    }
  }
}