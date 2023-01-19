package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;

public class DeleteDesignation extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response) {
doPost(request, response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response) {

try {
HttpSession session = request.getSession();
if(session.getAttribute("userName") == null) {
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("LoginForm.jsp");
requestDispatcher.forward(request, response); 
return;
}
}catch(Exception e) {
// do nothing
}

try {
DesignationBean designationBean = (DesignationBean) request.getAttribute("designationBean");
int code = designationBean.getCode();
try {
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.deleteByCode(code);
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Delete Module)");
messageBean.setMessage("Designation Deleted.");
messageBean.setGenerateButtons(true);
messageBean.setButtonOneText("Ok");
messageBean.setButtonOneAction("Designations.jsp");
messageBean.setGenerateTwoButtons(false);
request.setAttribute("messageBean", messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request, response);
} catch(DAOException daoException) {
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request, response);
}
} catch(Exception exception) {
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/ErroPage.jsp");
try {
requestDispatcher.forward(request, response);
} catch(Exception e) {
// do nothing
}
}
}
}