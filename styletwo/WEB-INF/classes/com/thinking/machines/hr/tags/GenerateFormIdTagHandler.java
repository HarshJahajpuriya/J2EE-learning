package com.thinking.machines.hr.tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.*;

public class GenerateFormIdTagHandler extends TagSupport {
public int doStartTag() {
String formId = UUID.randomUUID().toString();
pageContext.setAttribute("formId", formId, PageContext.SESSION_SCOPE);
return SKIP_BODY;
}
public int doEndTag() {
return EVAL_PAGE;
}
}