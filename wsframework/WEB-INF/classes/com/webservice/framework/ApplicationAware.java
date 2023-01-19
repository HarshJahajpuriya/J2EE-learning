package com.webservice.framework;
import javax.servlet.*;
public interface ApplicationAware
{
public void setServletContext(ServletContext servletContext);
}