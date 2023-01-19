package com.webservice.framework;
import com.webservice.framework.plugin.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class GetPluginDetail implements WebServicePlugin 
{
private ArrayList<PluginService> pluginServices;
public void process(HttpServletResponse response,ArrayList<PluginService> pluginServices)
{
PrintWriter pw=null;
try
{
pw=response.getWriter();
response.setContentType("text/plain");
}catch(IOException io)
{
System.out.println(io);
}
System.out.println(pluginServices.size());
int i=0;
while(i<pluginServices.size())
{
pw.println("Path  "+pluginServices.get(i).getPath());
pw.println("Class  "+pluginServices.get(i).getClassName());
pw.println("Method  "+pluginServices.get(i).getMethodName());
String parameters[]=pluginServices.get(i).getParameters();
int e=0;
while(e<parameters.length)
{
pw.print("parmeter"+(e+1)+"  "+parameters[e]+"  ");
e++;
}
pw.println("");
i++;
}
} 
}