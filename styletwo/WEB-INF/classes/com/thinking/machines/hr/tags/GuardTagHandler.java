package com.thinking.machines.hr.tags;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class GuardTagHandler extends TagSupport {

public int doStartTag() {
HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
HttpSession session = request.getSession();
if(session.getAttribute("userName") == null) {
return super.EVAL_BODY_INCLUDE;
} else {
return super.SKIP_BODY;
}
}

public int doEndTag() {
return EVAL_PAGE;
}
}
