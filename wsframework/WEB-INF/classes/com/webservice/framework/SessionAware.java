package com.webservice.framework;
import javax.servlet.http.*;
public interface SessionAware
{
public void setHttpSession(HttpSession httpSession);
}