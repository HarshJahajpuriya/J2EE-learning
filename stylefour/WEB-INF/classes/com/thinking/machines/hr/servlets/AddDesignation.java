package com.thinking.machines.hr.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.utils.Res;
import com.thinking.machines.hr.utils.StatusCodes;

public class AddDesignation extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("HEre");
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
        // do nothing
      }


      BufferedReader br = request.getReader();
      StringBuilder b = new StringBuilder();
      String s = "";
      while(true) {
        s = br.readLine();
        if(s == null) break;
        b.append(s);
      }
      


      String rawData = b.toString();
      System.out.println(rawData);
      DesignationDTO designationDTO = gson.fromJson(rawData, DesignationDTO.class);

      try {
        DesignationDAO designationDAO = new DesignationDAO();
        designationDAO.add(designationDTO);
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
      res.setData(designationDTO.getTitle() + " added");
      pw.print(gson.toJson(res));
      pw.flush();
    } catch (IOException ioe) {
      // do nothing
    }
  }
}