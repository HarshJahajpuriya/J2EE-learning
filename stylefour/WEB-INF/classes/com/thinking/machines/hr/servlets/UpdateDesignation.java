package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.google.gson.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.utils.Res;
import com.thinking.machines.hr.utils.StatusCodes;

public class UpdateDesignation extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      PrintWriter pw = response.getWriter();
      Gson gson = new Gson();

      try {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("userName"));
        if (session.getAttribute("userName") == null) {
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

      BufferedReader br = request.getReader();
      StringBuilder b = new StringBuilder();
      String s;
      while(true) {
        s=br.readLine();
        if(s==null) break;
        b.append(s);
      }

      String rawData = b.toString();

      DesignationDTO designationDTO = gson.fromJson(rawData, DesignationDTO.class);
      System.out.println(designationDTO.getCode());
      System.out.println(designationDTO.getTitle());

      
      
      DesignationDAO designationDAO = null;
      
      try {
        designationDAO = new DesignationDAO();
        designationDAO.update(designationDTO);;
        Res res = new Res();
        res.setStatus(StatusCodes.SUCCESS);
        res.setData("Designation Updated.");
        pw.print(gson.toJson(res));
        pw.flush();
      } catch (DAOException daoException) {
        Res res = new Res();
        res.setStatus(StatusCodes.DATABASE_ERROR);
        res.setData(daoException.getMessage());
        pw.print(gson.toJson(res));
        pw.flush();
      }

    } catch (Exception e) {
      System.out.println(e);
    }
    

  }
}
