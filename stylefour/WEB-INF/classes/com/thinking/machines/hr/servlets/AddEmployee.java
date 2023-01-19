package com.thinking.machines.hr.servlets;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.thinking.machines.hr.dl.DAOException;
import com.thinking.machines.hr.dl.EmployeeDAO;
import com.thinking.machines.hr.dl.EmployeeDTO;


public class AddEmployee extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      HttpSession session = request.getSession();
      System.out.println(session.getAttribute("userName"));
      if(session.getAttribute("userName")==null) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.html");
        requestDispatcher.forward(request, response);
        return;
      }
    } catch(Exception e) {
      // do nothing
    }
    
    String name = null;
    int designationCode = -1;
    SimpleDateFormat sdf = null;
    Date dateOfBirth = null;
    String gender = null;
    Boolean isIndian = null;
    BigDecimal basicSalary = null;
    String panNumber = null;
    String aadharCardNumber = null;

    try {
      name = (request.getParameter("name"));
      designationCode = Integer.parseInt(request.getParameter("designationCode"));
      sdf = new SimpleDateFormat("yyy-MM-dd");
      dateOfBirth = sdf.parse(request.getParameter("dateOfBirth"));
      gender = (request.getParameter("gender"));
      isIndian = request.getParameter("isIndian").equals('Y')?true:false;
      basicSalary = new BigDecimal(request.getParameter("basicSalary"));
      panNumber = (request.getParameter("panNumber"));
      aadharCardNumber = (request.getParameter("aadharCardNumber"));
    } catch(Exception e) {
      System.out.println("Error page");
      System.out.println(e);
    }
    
    try {
      EmployeeDTO employeeDTO = new EmployeeDTO();
      employeeDTO.setName(name);
      employeeDTO.setDesignationCode(designationCode);
      employeeDTO.setDateOfBirth(dateOfBirth);
      employeeDTO.setGender(gender);
      employeeDTO.setIsIndian(isIndian);
      employeeDTO.setBasicSalary(basicSalary);
      employeeDTO.setPANNumber(panNumber);
      employeeDTO.setAadharCardNumber(aadharCardNumber);
      EmployeeDAO employeeDAO = new EmployeeDAO();
      employeeDAO.add(employeeDTO);
    } catch(DAOException e) {
      System.out.println(e);
      try {
        response.sendError(500, e.getMessage());
      } catch(Exception ee) {
        // do nothing 
      }      
      return;
    }

    try {
      PrintWriter pw = response.getWriter();
      pw.print(name + " added as an Employee.");
    } catch(Exception e) {
      // do nothing
    }
    
  }
}
