package com.webservice.framework;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class FileUploadWebService extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
ServletContext servletContext=getServletContext();
HashMap<String,Service> services=(HashMap<String,Service>)servletContext.getAttribute("services");
RequestProcessor.process(getServletContext(),request,response,services); 
}
}
