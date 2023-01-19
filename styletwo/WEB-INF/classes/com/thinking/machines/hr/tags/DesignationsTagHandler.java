package com.thinking.machines.hr.tags;

import javax.servlet.jsp.tagext.*;
import java.util.*;
import javax.servlet.jsp.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;

public class DesignationsTagHandler extends BodyTagSupport {
private List<DesignationBean> designations;
private int index;
public DesignationsTagHandler() {
reset();
}
private void reset() {
index=0;
if(designations!=null) {
designations.clear();
designations = null;
}
designations = new ArrayList<DesignationBean>();
}
public int doStartTag() {
List<DesignationDTO> designationDTOs = null;
try {
designationDTOs = new DesignationDAO().getAll();
} catch(DAOException daoException) {
System.out.println(daoException);	// remove after testing
return SKIP_BODY;
}
if(designationDTOs.size() == 0) {
return super.SKIP_BODY;
}
DesignationBean designationBean = null;
for(DesignationDTO designationDTO: designationDTOs) {
designationBean = new DesignationBean();
designationBean.setCode(designationDTO.getCode());
designationBean.setTitle(designationDTO.getTitle());
designations.add(designationBean);
}
index=0;
designationBean = designations.get(index);
pageContext.setAttribute("designationBean", designationBean, PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber", new Integer(index+1));
return super.EVAL_BODY_INCLUDE;
}
public int doAfterBody() {
index++;
if(index == designations.size()) {
return super.SKIP_BODY;
}
DesignationBean designationBean = designations.get(index);
pageContext.setAttribute("designationBean", designationBean, PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber", new Integer(index+1));
return super.EVAL_BODY_AGAIN;
}
public int doEndTag() {
reset();
return EVAL_PAGE;
}
}