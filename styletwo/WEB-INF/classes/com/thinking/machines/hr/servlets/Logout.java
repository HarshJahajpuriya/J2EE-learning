package com.thinking.machines.hr.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response) {
HttpSession session = request.getSession();
// session.removeAttribute("userName");
session.invalidate();
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/LoginForm.jsp");
try {
requestDispatcher.forward(request, response);
} catch(Exception e) {}
}
}