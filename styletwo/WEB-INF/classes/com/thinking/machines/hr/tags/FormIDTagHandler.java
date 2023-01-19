package com.thinking.machines.hr.tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.io.*;

public class FormIDTagHandler extends TagSupport {
private void reset() {
}
public FormIDTagHandler() {
reset();
}
public int doStartTag() {
String formID = UUID.randomUUID().toString();
pageContext.setAttribute(formID, formID, PageContext.SESSION_SCOPE);
JspWriter jw = pageContext.getOut();
try {
jw.print("<input type='hidden' id='formID' name='formID' value='"+formID+"' />");
} catch(IOException ioe) {
// do nothing
}
return SKIP_BODY;
}
public int doEndTag() {
return EVAL_PAGE;
}
}