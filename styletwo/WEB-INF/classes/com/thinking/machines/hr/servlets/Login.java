package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;

public class Login extends HttpServlet 
{
public void doPost(HttpServletRequest request, HttpServletResponse response) 
{
System.out.println("Logging In");
AdministratorBean administratorBean = (AdministratorBean) request.getAttribute("administratorBean");
if(administratorBean == null) {
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/LoginForm.jsp");
System.out.println("Forwarding request to LoginForm.jsp");
try {
requestDispatcher.forward(request, response);
} catch(Exception e) {}
return;
}

AdministratorDTO administratorDTO = null;
try {
AdministratorDAO administratorDAO = new AdministratorDAO();
administratorDTO = administratorDAO.getByUserName(administratorBean.getUserName());
if(administratorDTO == null) {
throw new DAOException("Invalid user name: " + administratorBean.getUserName());
}
if(administratorBean.getPassword().equals(administratorDTO.getPassword())) {
HttpSession session = request.getSession();
session.setAttribute("userName", administratorBean.getUserName());
System.out.println("UserName: " + session.getAttribute("userName"));
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("index.jsp");
requestDispatcher.forward(request, response);
}
throw new DAOException("Invalid password");

} catch(DAOException daoException) {
ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean", errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("LoginForm.jsp");
try {
requestDispatcher.forward(request, response);
} catch(Exception e) {}
} catch(Exception e) {
System.out.println("In Login Servlet");
System.out.println(e);
}

}
}