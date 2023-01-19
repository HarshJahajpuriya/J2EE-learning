package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.google.gson.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.utils.Res;
import com.thinking.machines.hr.utils.StatusCodes;

public class ConfirmDeleteDesignation extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      PrintWriter pw = response.getWriter();
      Gson gson = new Gson();

      try {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("userName")+"::");
        if (session.getAttribute("userName") == null) {
          System.out.println("HEre");
          Res res = new Res();
          res.setStatus(StatusCodes.UNAUTHORIZED);
          res.setData("Login.html");
          pw.print(gson.toJson(res));
          pw.flush();
          return;
        }
      } catch (Exception e) {
        System.out.println("Error page");
        // do nothing
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
      } catch (DAOException daoException) {
        Res res = new Res();
        res.setStatus(StatusCodes.DATABASE_ERROR);
        res.setData(daoException.getMessage());
        pw.print(gson.toJson(res));
        pw.flush();
        return;
      }

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ConfirmDeleteDesignation.jsp");
      requestDispatcher.forward(request, response);
    } catch (Exception e) {
      // do nothing
    }

  }
}
