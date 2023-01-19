package com.thinking.machines.hr.servlets;

import java.io.Console;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.*;

public class Login extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String userName = request.getParameter("userName");
      String password = request.getParameter("password");
      AdministratorDAO administratorDAO = new AdministratorDAO();
      AdministratorDTO administratorDTO = null;
      try {
        administratorDTO = administratorDAO.getByUserName(userName);
      } catch(DAOException daoException) {
        // User Not Found
        response.sendError(401, "User Not Found");
        return;
      }
      if(!password.equals(administratorDTO.getPassword())) {
        // Wrong Password 
        response.sendError(401, "Incorrect Password");
        return;
      }
      HttpSession session = request.getSession();
      session.setAttribute("userName", userName);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
      requestDispatcher.forward(request, response);
    } catch (Exception e) {
      System.out.println("Error page");
      System.out.println(e);
    }
  }
}
