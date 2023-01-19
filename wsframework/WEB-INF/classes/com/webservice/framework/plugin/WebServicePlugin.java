package com.webservice.framework.plugin;
import javax.servlet.http.*;
import java.util.*;
public interface WebServicePlugin
{
public void process(HttpServletResponse response,ArrayList<PluginService> pluginServices);
}