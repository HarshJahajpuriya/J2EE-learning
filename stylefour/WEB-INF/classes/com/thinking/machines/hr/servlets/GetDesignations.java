package com.thinking.machines.hr.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.utils.Res;
import com.thinking.machines.hr.utils.StatusCodes;

public class GetDesignations extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) {

    try {
      Gson gson = new Gson();
      response.setContentType("application/json");
      PrintWriter pw = response.getWriter();

      try {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("userName"));
        if (session.getAttribute("userName") == null) {
          Res res = new Res();
          res.setStatus(StatusCodes.UNAUTHORIZED);
          res.setData("Login.html");
          pw.print(gson.toJson(res));
          return;
        }
      } catch (Exception e) {
        System.out.println("Error page");
        // do nothing
      }

      DesignationDAO designationDAO = null;
      List<DesignationDTO> designations = new ArrayList<>();
      try {
        designationDAO = new DesignationDAO();
        designations = designationDAO.getAll();
      } catch (DAOException daoException) {
        Res res = new Res();
        res.setStatus(StatusCodes.DATABASE_ERROR);
        res.setData(daoException.getMessage());
        pw.print(gson.toJson(res));
        pw.flush();
        return;
      }
      Res res = new Res();
      res.setStatus(StatusCodes.SUCCESS);
      res.setData((designations));
      pw.print(gson.toJson(res));
      pw.flush();

      // for(DesignationDTO designationDTO: designations) {
      // pw.print(designationDTO.getCode()+",");
      // pw.print(designationDTO.getTitle()+":");
      // }

    } catch (IOException ioe) {
      System.out.println("Do nothing. " + ioe);
    }

  }
}
