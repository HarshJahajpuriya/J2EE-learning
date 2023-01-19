package com.webservice.framework.plugin;
public class PluginService
{
private String path;
private String className;
private String methodName;
private String[] parameters;
public PluginService(String path,String className,String methodName,String[] parameters)
{
this.path=path;
this.className=className;
this.methodName=methodName;
this.parameters=parameters;
}
public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}
public void setClassName(String className)
{
this.className=className;
}
public String getClassName()
{
return this.className;
}
public void setMethodName(String methodName)
{
this.methodName=methodName;
}
public String getMethodName()
{
return this.methodName;
}
public void setParameters(String[] parameters)
{
this.parameters=parameters;
}
public String[] getParameters()
{
return this.parameters;
}
}