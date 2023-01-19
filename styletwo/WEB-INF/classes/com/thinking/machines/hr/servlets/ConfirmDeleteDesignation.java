package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;

public class ConfirmDeleteDesignation extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response) {

try {
HttpSession session = request.getSession();
if(session.getAttribute("userName") == null) {
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("LoginForm.jsp");
requestDispatcher.forward(request, response);
return;
}
} catch(Exception e) {
// do nothing
}

try {
int code = Integer.parseInt(request.getParameter("code"));
try {
DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designation = designationDAO.getByCode(code);
DesignationBean designationBean = new DesignationBean();
designationBean.setCode(designation.getCode());
designationBean.setTitle(designation.getTitle());
request.setAttribute("designationBean", designationBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/ConfirmDeleteDesignation.jsp");
requestDispatcher.forward(request, response);
} catch(DAOException daoException) {
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request, response);
}
} catch(Exception exception) {
System.out.println(exception);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
try {
requestDispatcher.forward(request, response);
} catch(Exception e) {
// do nothing
}
}
}
}