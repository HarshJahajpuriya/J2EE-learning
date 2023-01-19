package com.webservice.framework.plugin;
import com.webservice.framework.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
public class PluginConfigurationReader
{
public static HashMap<String,String> getPlugins(File file) throws ConfigurationException
{
HashMap<String,String> plugins=new HashMap<String,String>();
try
{
if(file.exists()==false)
{
throw new ConfigurationException(" Plugin configuration file : "+file.getName()+" is missing");
}
DocumentBuilderFactory documentBuilderFactory;
documentBuilderFactory=DocumentBuilderFactory.newInstance();
DocumentBuilder documentBuilder;
documentBuilder=documentBuilderFactory.newDocumentBuilder();
Document document;
document=documentBuilder.parse(file);
String rootNodeName=document.getDocumentElement().getNodeName();
if(rootNodeName.equals("plugins")==false)
{
throw new ConfigurationException("<plugins> if not the root element of the configuration file.");
}
NodeList rootNodeList;
rootNodeList=document.getElementsByTagName("plugins");
Node rootNode;
NodeList listOfPlugins;
Node childNode;
Element element;
String tagName;
String tagContent;
int e=0;
int pluginCount=0;
while(e<rootNodeList.getLength())
{
rootNode=rootNodeList.item(e);
listOfPlugins=rootNode.getChildNodes();
for(int j=0;j<listOfPlugins.getLength();j++)
{
childNode=listOfPlugins.item(j);
tagName=childNode.getNodeName();
if(tagName.equals("#text")) continue;
if(tagName.equals("plugin")==false) 
{
throw new ConfigurationException("<plugins> does not contain <plugin> at number : "+(j+1));
}
pluginCount++;
Node pluginRootNode=childNode;
NodeList pluginList=pluginRootNode.getChildNodes();
Node pluginNameNode=null;
Node pluginClassNode=null;
Node pluginNode=null;
String pluginName="";
String pluginClass="";
int count=pluginList.getLength();
for(int x=0;x<pluginList.getLength();x++)
{
pluginNode=pluginList.item(x);
tagName=pluginNode.getNodeName();
if(tagName.equals("#text"))
{
count--;
continue;
}
if(tagName.equals("plugin-name")) 
{
pluginNameNode=pluginNode;
}
if(tagName.equals("plugin-class")) 
{
pluginClassNode=pluginNode;
}
}
if(pluginNameNode==null) throw new ConfigurationException("<plugin> does not contain <plugin-name> at number : "+pluginCount);
if(pluginClassNode==null) throw new ConfigurationException("<plugin> does not contain <plugin-class> at number : "+pluginCount);
pluginName=pluginNameNode.getTextContent();
pluginClass=pluginClassNode.getTextContent();
if(count>2)
{
throw new ConfigurationException("<plugin> does not contain tags other then <plugin-name> and <plugin-class> at number : "+pluginCount);
}

plugins.put(pluginName.replaceAll("\r","").replaceAll("\n",""),pluginClass.replaceAll("\r","").replaceAll("\n",""));
}
e++;
}
}catch(Exception exception)
{
ConfigurationException configurationException=new ConfigurationException(exception.getMessage());
throw configurationException;
}
return plugins;
}
}