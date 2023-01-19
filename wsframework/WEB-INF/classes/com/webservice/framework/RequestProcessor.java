package com.webservice.framework;

import javax.servlet.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import javax.servlet.http.*;

class RequestProcessor {
  private RequestProcessor() {
  }

  static void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response,
      HashMap<String, Service> services) {
    try {
      String uri = request.getRequestURI();
      String servletPath = request.getServletPath();
      String contextPath = request.getServletContext().getContextPath();
      String requestPath = uri.substring((contextPath + servletPath).length());

      if (requestPath.equals("/webservice-report")) {
        Boolean developmentMode = (Boolean) servletContext.getAttribute("developmentMode");
        if (developmentMode == false) {
          sendErrorResponse(HttpServletResponse.SC_NOT_FOUND, request, response);
          return;
        }
        processWebServiceReportRequest(request, response, servletContext.getRealPath("/"));
        return;
      }
      Service service = services.get(requestPath);
      if (service == null) {
        sendErrorResponse(HttpServletResponse.SC_NOT_FOUND, request, response);
        return;
      }
      boolean isFileAware = service.isFileAware();
      long maxFileSize = service.getMaxFileSize();
      String typeOfRequest = request.getMethod();
      if (isFileAware) {
        if (typeOfRequest.equals("POST") == false) {
          sendErrorResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED, request, response);
          return;
        }
      }
      if (typeOfRequest.equals("POST")) {
        if (service.allowPostTypeRequest() == false) {
          sendErrorResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED, request, response);
          return;
        }
      } else if (typeOfRequest.equals("GET")) {
        if (service.allowGetTypeRequest() == false) {
          sendErrorResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED, request, response);
          return;
        }
      } else {
        sendErrorResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED, request, response);
        return;
      }
      ObjectNode errorResponseObjectNode;
      ObjectNode responseObjectNode;
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      ObjectNode objectNode = null;
      objectNode = getRequestParameters(request);
      if (objectNode == null && isFileAware) {
        objectNode = objectMapper.createObjectNode();
      }
      LinkedList<FileUploadWrapper> fileUploadWrappers = new LinkedList<FileUploadWrapper>();
      if (isFileAware) {
        Collection<Part> parts = request.getParts();
        Iterator<Part> iterator = parts.iterator();
        String fileName;
        String pathToSaveUploadedFile = servletContext.getRealPath("/") + "WEB-INF" + File.separator + "dxt";
        String newFileName;
        FileUploadWrapper fileUploadWrapper;
        for (Part part : parts) {
          part = iterator.next();
          fileName = extractFileName(part);
          if (fileName == null)
            continue;
          newFileName = UUID.randomUUID().toString().replaceAll("-", "k");
          if (fileName.indexOf(".") > 0) {
            newFileName = newFileName + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
          }
          part.write(pathToSaveUploadedFile + File.separator + newFileName);
          fileUploadWrapper = new FileUploadWrapper();
          fileUploadWrapper.setFileName(fileName);
          fileUploadWrapper.setFile(new File(pathToSaveUploadedFile + File.separator + newFileName));
          fileUploadWrappers.add(fileUploadWrapper);
        }
      }
      if (objectNode == null) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = request.getReader();
        String line = null;
        while (true) {
          line = br.readLine();
          if (line == null)
            break;
          sb.append(line);
        }
        String rawData = sb.toString();
        if (rawData == null || rawData.length() == 0) {
          rawData = "{}";
        }
        try {
          objectNode = objectMapper.readValue(rawData, ObjectNode.class);
        } catch (Exception exception) {
          errorResponseObjectNode = objectMapper.createObjectNode();
          errorResponseObjectNode.put("success", false);
          errorResponseObjectNode.put("isException", false);
          errorResponseObjectNode.put("error", "Invalid JSON in request");
          PrintWriter printWriter = response.getWriter();
          response.setContentType("application/json");
          printWriter.print(errorResponseObjectNode.toString());
          printWriter.flush();
          printWriter.close();
          return;
        }
      } // if ends to extract raw data because parameters doesn't exist in QS
      Iterator<String> iterator = objectNode.fieldNames();
      ArrayList<String> parameters = new ArrayList<String>();
      while (iterator.hasNext())
        parameters.add(iterator.next());
      Class[] parameterTypes = service.getParameterTypes();
      Object object = service.getObject();
      if (object == null) {
        // unable to instantiate the object of the class mapped to the Service
        errorResponseObjectNode = objectMapper.createObjectNode();
        errorResponseObjectNode.put("success", false);
        errorResponseObjectNode.put("isException", false);
        errorResponseObjectNode.put("error", "Unable to create instance of : " + service.getMappedClass().getName());
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        printWriter.print(errorResponseObjectNode.toString());
        printWriter.flush();
        printWriter.close();
        return;
      }
      // dependency injection
      if (service.isDirectoryAware()) {
        // ((DirectoryAware)object).setDirectory(new
        // File(servletContext.getRealPath("/")));
        try {
          service.getDirectoryInjectionMethod().invoke(object, new File(servletContext.getRealPath("/")));
        } catch (Exception exception) {
          System.out.println(exception); // remove after testing
        }
      }
      if (service.isRequestAware()) {
        // ((RequestAware)object).setHttpRequest(request);
        try {
          service.getRequestInjectionMethod().invoke(object, request);
        } catch (Exception exception) {
          System.out.println(exception); // remove after testing
        }
      }
      if (service.isSessionAware()) {
        // ((SessionAware)object).setHttpSession(request.getSession());
        try {
          service.getSessionInjectionMethod().invoke(object, request.getSession());
        } catch (Exception exception) {
          System.out.println(exception); // remove after testing
        }
      }
      if (service.isApplicationAware()) {
        // ((ApplicationAware)object).setServletContext(servletContext);
        try {
          service.getApplicationInjectionMethod().invoke(object, servletContext);
        } catch (Exception exception) {
          System.out.println(exception); // remove after testing
        }
      }
      if (isFileAware) {
        try {
          service.getFilesInjectionMethod().invoke(object, fileUploadWrappers);
        } catch (Exception exception) {
          System.out.println(exception); // remove after testing
        }
      }
      Method method = service.getMethod();
      Object[] arguments;
      Object result;
      if (parameterTypes.length == parameters.size()) {
        arguments = new Object[parameterTypes.length];
        JsonNode argument;
        for (int i = 0; i < parameterTypes.length; i++) {
          argument = objectNode.get("argument-" + (i + 1));
          if (argument == null) {
            errorResponseObjectNode = objectMapper.createObjectNode();
            errorResponseObjectNode.put("success", false);
            errorResponseObjectNode.put("isException", false);
            errorResponseObjectNode.put("error", "argument-" + (i + 1) + " is missing");
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");
            printWriter.print(errorResponseObjectNode.toString());
            printWriter.flush();
            printWriter.close();
            return;
          } else {
            try {
              arguments[i] = objectMapper.readValue(argument.toString(), parameterTypes[i]);
            } catch (Throwable ee) {
              ee.printStackTrace();
            }
          }
        }
        try {
          result = method.invoke(object, arguments);
          if (isFileAware)
            removeTemporaryFiles(fileUploadWrappers);
          if (service.isReturningSomething()) {
            if (result != null && result instanceof Exception) {
              Method getExceptionsMethod = null;
              try {
                getExceptionsMethod = result.getClass().getMethod("getExceptions");
                result = getExceptionsMethod.invoke(result);
              } catch (Exception exception) {
              }
              errorResponseObjectNode = objectMapper.createObjectNode();
              errorResponseObjectNode.put("success", false);
              errorResponseObjectNode.put("isException", true);
              errorResponseObjectNode.put("isObject", true);
              String jsonString = objectMapper.writeValueAsString(result);
              JsonNode jsonNode = objectMapper.readTree(jsonString);
              errorResponseObjectNode.putPOJO("exception", jsonNode);
              PrintWriter printWriter = response.getWriter();
              response.setContentType("application/json");
              printWriter.print(errorResponseObjectNode.toString());
              printWriter.flush();
              printWriter.close();
              return;
            } else {
              if (result instanceof FileDownloadWrapper) {
                FileDownloadWrapper fileDownloadWrapper = (FileDownloadWrapper) result;
                File file = fileDownloadWrapper.getFile();
                try {
                  response.setContentType(fileDownloadWrapper.getContentType());
                  if (fileDownloadWrapper.isAttachment()) {
                    response.setHeader("Content-Disposition",
                        "attachment;filename=" + fileDownloadWrapper.getFileName());
                  } else {
                    response.setHeader("Content-Disposition", "filename=" + fileDownloadWrapper.getFileName());
                  }
                  response.setContentLength((int) file.length());
                  FileInputStream fileInputStream = new FileInputStream(file);
                  OutputStream outputStream = response.getOutputStream();
                  byte[] buffer = new byte[1024];
                  int count = 0;
                  while (true) {
                    count = fileInputStream.read(buffer);
                    if (count <= 0) {
                      break;
                    }
                    outputStream.write(buffer, 0, count);
                  }
                  fileInputStream.close();
                  outputStream.close();
                } catch (IOException ioException) {
                  System.out.println(ioException); // remove after testing
                  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                  return;
                } catch (Exception exception) {
                  System.out.println(exception); // remove after testing
                  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                  return;
                }
              } else {
                responseObjectNode = objectMapper.createObjectNode();
                responseObjectNode.put("success", true);
                responseObjectNode.put("isReturningSomething", true);
                if (result != null) {
                  responseObjectNode.put("isResultNull", false);
                  String jsonString = objectMapper.writeValueAsString(result);
                  JsonNode jsonNode = objectMapper.readTree(jsonString);
                  responseObjectNode.putPOJO("result", jsonNode);
                } else {
                  responseObjectNode.put("isResultNull", true);
                }
                PrintWriter printWriter = response.getWriter();
                response.setContentType("application/json");
                printWriter.print(responseObjectNode.toString());
                printWriter.flush();
                printWriter.close();
                return;
              }
            }
          } else {
            responseObjectNode = objectMapper.createObjectNode();
            responseObjectNode.put("success", true);
            responseObjectNode.put("isReturningSomething", false);
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");
            printWriter.print(responseObjectNode.toString());
            printWriter.flush();
            printWriter.close();
            return;
          }
        } catch (IllegalAccessException illegalAccessException) {
          if (isFileAware)
            removeTemporaryFiles(fileUploadWrappers);
          errorResponseObjectNode = objectMapper.createObjectNode();
          errorResponseObjectNode.put("success", false);
          errorResponseObjectNode.put("isException", false);
          errorResponseObjectNode.put("isObject", false);
          errorResponseObjectNode.put("error", "Cannot access : " + method.toString());
          PrintWriter printWriter = response.getWriter();
          response.setContentType("application/json");
          printWriter.print(errorResponseObjectNode.toString());
          printWriter.flush();
          printWriter.close();
          return;
        } catch (InvocationTargetException invocationTargetException) {
          if (isFileAware)
            removeTemporaryFiles(fileUploadWrappers);
          errorResponseObjectNode = objectMapper.createObjectNode();
          errorResponseObjectNode.put("success", false);
          errorResponseObjectNode.put("isException", true);
          errorResponseObjectNode.put("isObject", false);
          errorResponseObjectNode.put("exception", invocationTargetException.getCause().toString());
          PrintWriter printWriter = response.getWriter();
          response.setContentType("application/json");
          printWriter.print(errorResponseObjectNode.toString());
          printWriter.flush();
          printWriter.close();
          return;
        }
      } else if (parameterTypes.length == 1) {
        arguments = new Object[1];
        // replace rawData with objectNode converted to String as JSON
        // arguments[0]=objectMapper.readValue(rawData,parameterTypes[0]);
        arguments[0] = objectMapper.readValue(objectNode.toString(), parameterTypes[0]);
        try {
          result = method.invoke(object, arguments);
          if (isFileAware)
            removeTemporaryFiles(fileUploadWrappers);
          if (service.isReturningSomething()) {
            if (result != null && result instanceof Exception) {
              errorResponseObjectNode = objectMapper.createObjectNode();
              errorResponseObjectNode.put("success", false);
              errorResponseObjectNode.put("isException", true);
              errorResponseObjectNode.put("isObject", true);
              String jsonString = objectMapper.writeValueAsString(result);
              JsonNode jsonNode = objectMapper.readTree(jsonString);
              errorResponseObjectNode.putPOJO("exception", jsonNode);
              PrintWriter printWriter = response.getWriter();
              response.setContentType("application/json");
              printWriter.print(errorResponseObjectNode.toString());
              printWriter.flush();
              printWriter.close();
              return;
            } else {
              responseObjectNode = objectMapper.createObjectNode();
              responseObjectNode.put("success", true);
              responseObjectNode.put("isReturningSomething", true);
              if (result != null) {
                responseObjectNode.put("isResultNull", false);
                responseObjectNode.put("result", objectMapper.writeValueAsString(result));
              } else {
                responseObjectNode.put("isResultNull", true);
              }
              PrintWriter printWriter = response.getWriter();
              response.setContentType("application/json");
              printWriter.print(responseObjectNode.toString());
              printWriter.flush();
              printWriter.close();
              return;
            }
          } else {
            responseObjectNode = objectMapper.createObjectNode();
            responseObjectNode.put("success", true);
            responseObjectNode.put("isReturningSomething", false);
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");
            printWriter.print(responseObjectNode.toString());
            printWriter.flush();
            printWriter.close();
            return;
          }
        } catch (IllegalAccessException illegalAccessException) {
          if (isFileAware)
            removeTemporaryFiles(fileUploadWrappers);
          errorResponseObjectNode = objectMapper.createObjectNode();
          errorResponseObjectNode.put("success", false);
          errorResponseObjectNode.put("isException", false);
          errorResponseObjectNode.put("isObject", false);
          errorResponseObjectNode.put("error", "Cannot access : " + method.toString());
          PrintWriter printWriter = response.getWriter();
          response.setContentType("application/json");
          printWriter.print(errorResponseObjectNode.toString());
          printWriter.flush();
          printWriter.close();
          return;
        } catch (InvocationTargetException invocationTargetException) {
          if (isFileAware)
            removeTemporaryFiles(fileUploadWrappers);
          errorResponseObjectNode = objectMapper.createObjectNode();
          errorResponseObjectNode.put("success", false);
          errorResponseObjectNode.put("isException", true);
          errorResponseObjectNode.put("isObject", false);
          errorResponseObjectNode.put("exception", invocationTargetException.getCause().toString());
          PrintWriter printWriter = response.getWriter();
          response.setContentType("application/json");
          printWriter.print(errorResponseObjectNode.toString());
          printWriter.flush();
          printWriter.close();
          return;
        }
      } else {
        if (isFileAware)
          removeTemporaryFiles(fileUploadWrappers);
        errorResponseObjectNode = objectMapper.createObjectNode();
        errorResponseObjectNode.put("success", false);
        errorResponseObjectNode.put("isException", false);
        errorResponseObjectNode.put("isObject", false);
        errorResponseObjectNode.put("error",
            "Expected : " + parameterTypes.length + " arguments, found : " + parameters.size());
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        printWriter.print(errorResponseObjectNode.toString());
        printWriter.flush();
        printWriter.close();
        return;
      }
    } catch (Exception exception) {
      System.out.println(exception);
    }
  }

  static private void processWebServiceReportRequest(HttpServletRequest request, HttpServletResponse response,
      String basePath) {
    try {
      File file = new File(basePath + "WEB-INF/tmws_report/tm_ws_report.pdf");
      if (file.exists() == false) {
        sendErrorResponse(HttpServletResponse.SC_NOT_FOUND, request, response);
        return;
      }
      response.setContentType("application/pdf");
      FileInputStream fileInputStream = new FileInputStream(file);
      OutputStream outputStream = response.getOutputStream();
      byte buffer[] = new byte[1024];
      int count;
      while (true) {
        count = fileInputStream.read(buffer);
        if (count <= 0)
          break;
        outputStream.write(buffer, 0, count);
      }
      fileInputStream.close();
      outputStream.close();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  static private void sendErrorResponse(int responseFlag, HttpServletRequest request, HttpServletResponse response) {
    try {
      ObjectNode errorResponseObjectNode;
      ObjectMapper objectMapper = new ObjectMapper();
      if (responseFlag == HttpServletResponse.SC_NOT_FOUND) {
        errorResponseObjectNode = objectMapper.createObjectNode();
        errorResponseObjectNode.put("success", false);
        errorResponseObjectNode.put("isException", false);
        errorResponseObjectNode.put("error", "Resource not found");
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        printWriter.print(errorResponseObjectNode.toString());
        printWriter.flush();
        printWriter.close();
      }
      if (responseFlag == HttpServletResponse.SC_METHOD_NOT_ALLOWED) {
        errorResponseObjectNode = objectMapper.createObjectNode();
        errorResponseObjectNode.put("success", false);
        errorResponseObjectNode.put("isException", false);
        errorResponseObjectNode.put("error", request.getMethod() + " type request not allowed.");
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        printWriter.print(errorResponseObjectNode.toString());
        printWriter.flush();
        printWriter.close();
      }
    } catch (Exception excetion) {
    }
  }

  static public ObjectNode getRequestParameters(HttpServletRequest request) {
    Enumeration<String> enumerator = request.getParameterNames();
    String parameterName;
    LinkedList<String> names = new LinkedList<String>();
    while (enumerator.hasMoreElements()) {
      parameterName = enumerator.nextElement();
      names.add(parameterName);
    }
    if (names.size() == 0)
      return null;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode objectNode = objectMapper.createObjectNode();
    String data[];
    for (String name : names) {
      data = request.getParameterValues(name);
      if (data.length == 0)
        continue;
      if (data.length == 1) {
        objectNode.put(name, data[0]);
      } else {
        // assignment
      }
    }
    return objectNode;
  }

  private static String extractFileName(Part part) {
    String contentDisposition = part.getHeader("content-disposition");
    String[] items = contentDisposition.split(";");
    for (String s : items) {
      if (s.trim().startsWith("filename")) {
        return s.substring(s.indexOf("=") + 2, s.length() - 1);
      }
    }
    return null;
  }

  static public void removeTemporaryFiles(LinkedList<FileUploadWrapper> fileUploadWrappers) {
    for (FileUploadWrapper fileUploadWrapper : fileUploadWrappers) {
      if (fileUploadWrapper.isTemporary()) {
        try {
          fileUploadWrapper.getFile().delete();
        } catch (Exception exception) {
          System.out.println(exception); // remove after testing
        }
      }
    }
  }
}
