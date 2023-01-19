package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.google.gson.*;

public class ServletThree extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    } catch (Exception e) {
      // do nothing
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      BufferedReader br = request.getReader();
      StringBuffer b = new StringBuffer();
      String d;
      while(true) {
        d = br.readLine();
        if(d == null) break;
        b.append(d);
      }
    
      String rawData = b.toString();
      Gson g = new Gson();
      Person person = g.fromJson(rawData, Person.class);
      
      response.setContentType("application/json");
      PrintWriter pw = response.getWriter();
      pw.print(g.toJson(person));
      pw.flush();
      
    } catch (Exception e) {
      try {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      } catch (Exception ee) {
        // do nothing
      }
    }
  }
}