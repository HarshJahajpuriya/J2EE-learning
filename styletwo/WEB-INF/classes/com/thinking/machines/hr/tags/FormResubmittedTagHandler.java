package com.thinking.machines.hr.tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormResubmittedTagHandler extends TagSupport {
private void reset() {
}
public FormResubmittedTagHandler() {
reset();
}
public int doStartTag() {
HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
String formIDInForm = request.getParameter("formID");
if(formIDInForm == null) {
return EVAL_BODY_INCLUDE;
}
String formIDInSession = (String) pageContext.getAttribute(formIDInForm, PageContext.SESSION_SCOPE);
if(formIDInSession == null) {
return EVAL_BODY_INCLUDE;
}
if(formIDInForm.equalsIgnoreCase(formIDInSession)) {
pageContext.setAttribute(formIDInSession, "", PageContext.SESSION_SCOPE);
return SKIP_BODY;
} else {
return EVAL_BODY_INCLUDE;
}
}
public int doEndTag() {
reset();
return EVAL_PAGE;
}
}