package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.*;

import java.io.*;
import java.util.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.utils.Res;
import com.thinking.machines.hr.utils.StatusCodes;

public class GetEmployees extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) {

    try {
      response.setContentType("applicaton/json");
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

      EmployeeDAO employeeDAO = null;
      List<EmployeeDTO> employees = new ArrayList<>();
      try {
        employeeDAO = new EmployeeDAO();
        employees = employeeDAO.getAll();
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
      res.setData(employees);
      pw.print(gson.toJson(res));
      pw.flush();

      // for(EmployeeDTO employeeDTO: employees) {
      // pw.print(employeeDTO.getEmployeeId()+",");
      // pw.print(employeeDTO.getName()+",");
      // pw.print(employeeDTO.getDesignationCode()+",");
      // pw.print(employeeDTO.getDesignation()+",");
      // pw.print(employeeDTO.getDateOfBirth()+",");
      // pw.print(employeeDTO.getGender()+",");
      // pw.print(employeeDTO.getIsIndian()+",");
      // pw.print(employeeDTO.getBasicSalary()+",");
      // pw.print(employeeDTO.getPANNumber()+",");
      // pw.print(employeeDTO.getAadharCardNumber()+":");
      // }

    } catch (IOException ioe) {
      // Do nothing
    }
  }
}
