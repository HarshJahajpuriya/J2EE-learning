package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;

import com.google.gson.*;

public class ServletOne extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("Here In Two");
    try {
      response.setContentType("application/json");
      response.setCharacterEncoding("utf-8");
      DesignationDAO designationDAO = new DesignationDAO();
      List<DesignationDTO> designations = designationDAO.getAll();
      Gson gson = new Gson();
      String jsonString = gson.toJson(designations);
      PrintWriter pw = response.getWriter();
      pw.print(jsonString);
      pw.flush();
    } catch (Exception e) {
      try {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      } catch (Exception ee) {
        // do nothing
      }
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    } catch (Exception e) {
      // do nothing
    }
  }

}