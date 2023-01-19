package com.thinking.machines.hr.tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class NavigationTagHandler extends TagSupport {
private Boolean designation;
private Boolean employee;
private Boolean home;
public void setDesignation(Boolean designation) {
this.designation = designation;
}
public Boolean getDesignation() {
return this.designation;
}
public void setEmployee(Boolean employee) {
this.employee = employee;
}
public Boolean getEmployee() {
return this.employee;
}
public void setHome(Boolean home) {
this.home = home;
}
public Boolean getHome() {
return this.home;
}
private void reset() {
designation = false;
employee = false;
home = false;
}
public NavigationTagHandler() {
reset();
}

public int doStartTag() {
pageContext.setAttribute("DESIGNATION", new String("DESIGNATION"), PageContext.REQUEST_SCOPE);
pageContext.setAttribute("EMPLOYEE", new String("EMPLOYEE"), PageContext.REQUEST_SCOPE);
pageContext.setAttribute("HOME", new String("HOME"), PageContext.REQUEST_SCOPE);
if(designation == true) {
pageContext.setAttribute("module", new String("DESIGNATION"), PageContext.REQUEST_SCOPE);
} else if(employee == true) {
pageContext.setAttribute("module", new String("EMPLOYEE"), PageContext.REQUEST_SCOPE);
} else if(home == true) {
pageContext.setAttribute("module", new String("HOME"), PageContext.REQUEST_SCOPE);
}
return EVAL_BODY_INCLUDE;
}

public int doEndTag() {
reset();
return EVAL_PAGE;
}
}
