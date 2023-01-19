package com.thinking.machines.hr.servlets;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddDesignation extends HttpServlet
{

public void doGet(HttpServletRequest request, HttpServletResponse response) {
doPost(request, response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
{

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

try
{
DesignationBean designationBean = (DesignationBean) request.getAttribute("designationBean");
String title = designationBean.getTitle();
DesignationDTO designation = new DesignationDTO();
designation.setTitle(title);
DesignationDAO designationDAO = new DesignationDAO();

//HttpSession session = request.getSession();
//String arrivedFormId = request.getParameter("formId");
//String sessionFormId = session.getAttribute("formId").toString();

//if(! sessionFormId.equals(arrivedFormId)) {
//System.out.println("Does not exists");
//MessageBean messageBean = new MessageBean();
//messageBean.setHeading("Designation (Add Module):");
//messageBean.setMessage("Do not refresh the page.");
//messageBean.setGenerateButtons(true);
//messageBean.setButtonOneText("Ok");
//messageBean.setButtonOneAction("Designations.jsp");
//request.setAttribute("messageBean", messageBean);
//RequestDispatcher requestDispatcher;
//requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
//requestDispatcher.forward(request, response);
//return;
//}

try 
{
designationDAO.add(designation);
designationBean.setCode(designation.getCode());
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Add Module)");
messageBean.setMessage("Designation added. Add more?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("DesignationAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Designations.jsp");
request.setAttribute("messageBean", messageBean);
//session.setAttribute("formId", "");
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request, response);
} catch(DAOException daoException) 
{
ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean", errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/DesignationAddForm.jsp");
requestDispatcher.forward(request, response);
}
} catch(Exception exception)
{
System.out.println(exception);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
try 
{
requestDispatcher.forward(request, response);
} catch(Exception e)
{
// Do Nothing
}
}
}
}