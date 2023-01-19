package com.webservice.framework;
import javax.servlet.http.*;
public interface RequestAware
{
public void setHttpRequest(HttpServletRequest httpServletRequest);
}