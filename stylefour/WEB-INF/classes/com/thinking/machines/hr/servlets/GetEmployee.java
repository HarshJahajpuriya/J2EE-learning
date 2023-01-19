package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.tools.Diagnostic;

import java.io.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.utils.Res;
import com.thinking.machines.hr.utils.StatusCodes;
import com.google.gson.*;

public class GetEmployee extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) {

    try {
      Gson gson = new Gson();
      PrintWriter pw = response.getWriter();
      response.setContentType("application/json");
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
      }

      BufferedReader br = request.getReader();
      StringBuilder b = new StringBuilder();
      String s;
      while(true) {
        s = br.readLine();
        if(s==null) break;
        b.append(s);
      }
      String rawData = b.toString();
      EmployeeDTO employee = gson.fromJson(rawData, EmployeeDTO.class);

      EmployeeDAO employeeDAO = null;
      try {
        employeeDAO = new EmployeeDAO();
        employee = employeeDAO.getByEmployeeId(employee.getEmployeeId());
      } catch (DAOException daoException) {
        Res res = new Res();
        res.setStatus(StatusCodes.DATABASE_ERROR);
        res.setData(daoException.getMessage());
        pw.print(gson.toJson(res));
        pw.flush();
        System.out.println(daoException);
        return;
      }

      Res res = new Res();
      res.setStatus(StatusCodes.SUCCESS);
      res.setData(employee);
      pw.print(gson.toJson(employee));
      pw.flush();

      // pw.print(employee.getEmployeeId() + ",");
      // pw.print(employee.getName() + ",");
      // pw.print(employee.getDesignationCode() + ",");
      // pw.print(employee.getDesignation() + ",");
      // pw.print(employee.getDateOfBirth() + ",");
      // pw.print(employee.getGender() + ",");
      // pw.print(employee.getIsIndian() + ",");
      // pw.print(employee.getBasicSalary() + ",");
      // pw.print(employee.getPANNumber() + ",");
      // pw.print(employee.getAadharCardNumber() + ":");

    } catch (IOException ioe) {
      // Do nothing
    }
  }
}
