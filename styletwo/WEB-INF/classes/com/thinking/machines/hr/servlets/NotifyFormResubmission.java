package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import com.thinking.machines.hr.beans.*;

public class NotifyFormResubmission extends HttpServlet {
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

MessageBean messageBean = new MessageBean();
messageBean.setHeading("Notification");
messageBean.setMessage("Form resubmission is invalid.");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("Ok");
messageBean.setButtonOneAction("index.jsp");
request.setAttribute("messageBean", messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
try {
requestDispatcher.forward(request, response);
} catch(Exception e) {
// do nothing
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response) {
doGet(request, response);
}
}