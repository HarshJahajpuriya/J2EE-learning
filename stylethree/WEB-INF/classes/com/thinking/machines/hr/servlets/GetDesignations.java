package com.thinking.machines.hr.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.*;

public class GetDesignations extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    DesignationDAO designationDAO = null;
    List<DesignationDTO> designations = new ArrayList<>();
    try {
      designationDAO = new DesignationDAO();
      designations =  designationDAO.getAll();
    } catch(DAOException daoException) {
      System.out.println(daoException);
      try {
        response.sendError(500, "Cannot access database");
      } catch(Exception e) {
        // do nothinh
        System.out.println("Here");
      }
    }
    try {
      PrintWriter pw = response.getWriter();
      for(DesignationDTO designationDTO: designations) {
        pw.print(designationDTO.getCode()+",");
        pw.print(designationDTO.getTitle()+":");
      }
    } catch(IOException ioe) {
      System.out.println("Do nothing. " + ioe);
    }

  }
}
