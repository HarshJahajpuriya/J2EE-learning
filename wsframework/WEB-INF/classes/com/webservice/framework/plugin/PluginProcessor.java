package com.webservice.framework.plugin;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PluginProcessor {
  public static void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response,
      ArrayList<PluginService> pluginServices, String pluginClass) {
    System.out.println(pluginServices.size());
    Class c = null;
    try {
      c = Class.forName(pluginClass);
      WebServicePlugin tmWebServicePlugin = (WebServicePlugin) c.newInstance();
      tmWebServicePlugin.process(response, pluginServices);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}