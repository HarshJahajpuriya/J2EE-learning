package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;

public class UpdateDesignation extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) {

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
DesignationBean designationBean = (DesignationBean) request.getAttribute("designationBean");
try {
DesignationDTO designation = new DesignationDTO();
designation.setCode(designationBean.getCode());
designation.setTitle(designationBean.getTitle());
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.update(designation);
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Edit Module)");
messageBean.setMessage("Designation Updated: ");
messageBean.setGenerateButtons(true);
messageBean.setButtonOneText("Ok");
messageBean.setButtonOneAction("Designations.jsp");
request.setAttribute("messageBean", messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request, response);
} catch(DAOException daoException) {
ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean", errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/DesignationEditForm.jsp");
requestDispatcher.forward(request, response);
}
} catch(Exception exception) {
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
 