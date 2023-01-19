package com.thinking.machines.hr.tags;

import com.thinking.machines.hr.bl.*;
import com.thinking.machines.hr.beans.*;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.lang.reflect.*;

public class EntityListTagHandler extends BodyTagSupport 
{
private String populatorClass;
private String populatorMethod;
private String name;
public int index;
public List list;
public void setPopulatorClass(String populatorClass) 
{
this.populatorClass = populatorClass;
}
public void setPopulatorMethod(String populatorMethod) 
{
this.populatorMethod = populatorMethod;
}
public void setName(String name) 
{
this.name = name;
}
public String getPopulatorClass() 
{
return this.populatorClass;
}
public String getPopulatorMethod() 
{
return this.populatorMethod;
}
public String getName() 
{
return this.name;
}
public EntityListTagHandler() 
{
reset();
}
public void reset() 
{
this.populatorClass = null;
this.populatorMethod = null;
this.name = null;
this.list = null;
this.index = 0;
}
public int doStartTag() 
{
try 
{
if(name == null || name.trim().length() == 0) return super.SKIP_BODY;
Class c = Class.forName(populatorClass);
Object obj = c.newInstance();
Class[] parameters = null;
Method m = c.getMethod(populatorMethod, parameters);
list = (List) m.invoke(obj);
if(list == null || list.size() == 0) return super.SKIP_BODY;
pageContext.setAttribute(name, list.get(index), PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber", index+1, PageContext.REQUEST_SCOPE);
index++;
return super.EVAL_BODY_INCLUDE;
} catch(Exception e) 
{
System.out.println(e); // Something needs to be done
return super.SKIP_BODY;
}
}
public int doAfterBody() 
{
if(index == list.size()) return super.SKIP_BODY;
pageContext.setAttribute(name, list.get(index), PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber", index+1, PageContext.REQUEST_SCOPE);
index++;
return super.EVAL_BODY_AGAIN;
}
public int doEndTag() 
{
reset();
return super.EVAL_PAGE;
}
}