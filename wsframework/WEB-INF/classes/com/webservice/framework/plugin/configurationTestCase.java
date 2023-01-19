package com.webservice.framework.plugin;
import java.io.*;
import com.webservice.framework.*;
import java.util.*;
class psp
{
public static void main(String gg[])
{
try
{
File file=new File("plugins.xml");
HashMap<String,String> plugins;
plugins=PluginConfigurationReader.getPlugins(file);
System.out.println(plugins.size());
Iterator<Map.Entry<String,String>> iterator;
Map.Entry<String,String> pair;
String pluginName;
String pluginClass;
iterator=plugins.entrySet().iterator();
while(iterator.hasNext())
{
pair=iterator.next();
pluginName=pair.getKey();
pluginClass=pair.getValue();
System.out.println(pluginName+"      "+pluginClass);
}
}catch(ConfigurationException e)
{
System.out.println(e);
}
}
}