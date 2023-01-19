package com.thinking.machines.hr.servlets;

import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.DAOException;
import com.thinking.machines.hr.dl.EmployeeDAO;
import com.thinking.machines.hr.dl.EmployeeDTO;

public class ConfirmDeleteEmployee extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("Confirming : " + request.getParameter("employeeId"));
    try {
      HttpSession session = request.getSession();
      System.out.println(session.getAttribute("userName"));
      if(session.getAttribute("userName") == null) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.html");
        requestDispatcher.forward(request, response);
        return;
      }
    } catch(Exception e) {
      // do nothing
    }

    String employeeId;
    try {
      employeeId = request.getParameter("employeeId");
    } catch(Exception e) {
      System.out.println("Error page");;
      return;
    }

    try {
      EmployeeDAO employeeDAO = new EmployeeDAO();
      EmployeeDTO employeeDTO = employeeDAO.getByEmployeeId(employeeId);
      request.setAttribute("employeeId", employeeDTO.getEmployeeId());
      request.setAttribute("name", employeeDTO.getName());
      request.setAttribute("designationCode", employeeDTO.getDesignationCode());
      request.setAttribute("designation", employeeDTO.getDesignation());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      String dateOfBirth = sdf.format(employeeDTO.getDateOfBirth());
      request.setAttribute("dateOfBirth", dateOfBirth);
      String gender;
      if(employeeDTO.getGender().equals("M")) gender = "Male";
      else gender = "Female";
      request.setAttribute("gender", gender);
      String isIndian;
      if(employeeDTO.getIsIndian()) isIndian = "Indian";
      else isIndian = "Not Indian";
      request.setAttribute("isIndian", isIndian);
      request.setAttribute("basicSalary", employeeDTO.getBasicSalary());
      request.setAttribute("panNumber", employeeDTO.getPANNumber());
      request.setAttribute("aadharCardNumber", employeeDTO.getAadharCardNumber());
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ConfirmDeleteEmployee.jsp");
      try {
        requestDispatcher.forward(request, response);
      } catch(Exception e) {
        // do nothing
      }
    } catch(DAOException daoException) {
      System.out.println(daoException);
    }
  }
}
