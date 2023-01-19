package com.thinking.machines.hr.servlets;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.DAOException;
import com.thinking.machines.hr.dl.DesignationDAO;
import com.thinking.machines.hr.dl.DesignationDTO;
import com.thinking.machines.hr.dl.EmployeeDAO;
import com.thinking.machines.hr.dl.EmployeeDTO;

public class UpdateEmployee extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("Updating EMP");
    try {
      HttpSession session = request.getSession();
      System.out.println(session.getAttribute("userName"));
      if(session.getAttribute("userName")==null) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.html");
        requestDispatcher.forward(request, response);
        return;
      }
    } catch(Exception e) {
      System.out.println("Error Page : " + e);
      return;
    }

    String employeeId = request.getParameter("employeeId");
    String name = request.getParameter("name");
    int designationCode = Integer.parseInt(request.getParameter("designationCode"));
    Date dateOfBirth = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      dateOfBirth = sdf.parse(request.getParameter("dateOfBirth"));
    } catch(ParseException pe) {
      System.out.println(pe);
    }
    String gender = request.getParameter("gender");
    Boolean isIndian = request.getParameter("isIndian") == "Y" ? true: false;
    BigDecimal basicSalary = new BigDecimal(request.getParameter("basicSalary"));
    String panNumber = request.getParameter("panNumber");
    String aadharCardNumber = request.getParameter("aadharCardNumber");

    EmployeeDAO employeeDAO = null;
    EmployeeDTO employeeDTO = null;
    DesignationDAO designationDAO = null;
    DesignationDTO designationDTO = null;
    try {
      employeeDAO = new EmployeeDAO();
      employeeDTO = new EmployeeDTO();
      employeeDTO.setEmployeeId(employeeId);
      employeeDTO.setName(name);
      employeeDTO.setDesignationCode(designationCode);
      designationDAO = new DesignationDAO();
      designationDTO = designationDAO.getByCode(designationCode);
      employeeDTO.setDesignation(designationDTO.getTitle());
      employeeDTO.setDateOfBirth(dateOfBirth);
      employeeDTO.setGender(gender);
      employeeDTO.setIsIndian(isIndian);
      employeeDTO.setBasicSalary(basicSalary);
      employeeDTO.setPANNumber(panNumber);
      employeeDTO.setAadharCardNumber(aadharCardNumber);
      employeeDAO.update(employeeDTO);

      try {
        PrintWriter pw = response.getWriter();
        pw.print("Employee Updated");
      } catch(Exception e) {
        System.out.println(e);
        // do nothing
      }
      
    } catch (DAOException e) {
      System.out.println(e);
      try {
        response.sendError(500, e.getMessage());
      } catch (Exception ex) {
        // do nothing
      }
      return;
    }

  }
}
