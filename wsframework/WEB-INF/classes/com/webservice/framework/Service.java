package com.webservice.framework;
import java.lang.reflect.*;
class Service
{
private String path;        
private Class mappedClass;
private Method method;
private Class parameterTypes[];
private Object object=null;
private boolean isDirectoryAware;
private boolean isRequestAware;
private boolean isSessionAware;
private boolean isApplicationAware;
private boolean isFileAware;
private long maxFileSize; // in bytes
private boolean isReturningSomething;
private boolean allowGetTypeRequest;
private boolean allowPostTypeRequest;
private Method directoryInjectionMethod;
private Method requestInjectionMethod;
private Method sessionInjectionMethod;
private Method applicationInjectionMethod;
private Method filesInjectionMethod;
public Service(String path,Class mappedClass,Method method,boolean isReturningSomething,boolean isDirectoryAware,boolean isRequestAware,boolean isSessionAware,boolean isApplicationAware,boolean allowGetTypeRequest,boolean allowPostTypeRequest,boolean isFileAware,long maxFileSize,Method directoryInjectionMethod,Method requestInjectionMethod,Method sessionInjectionMethod,Method applicationInjectionMethod,Method filesInjectionMethod)
{
this.path=path;
this.method=method;
this.isReturningSomething=isReturningSomething;
this.mappedClass=mappedClass;
this.parameterTypes=method.getParameterTypes();
this.isDirectoryAware=isDirectoryAware;
this.isRequestAware=isRequestAware;
this.isSessionAware=isSessionAware;
this.isApplicationAware=isApplicationAware;
this.allowGetTypeRequest=allowGetTypeRequest;
this.allowPostTypeRequest=allowPostTypeRequest;
this.isFileAware=isFileAware;
this.maxFileSize=maxFileSize;
this.directoryInjectionMethod=directoryInjectionMethod;
this.requestInjectionMethod=requestInjectionMethod;
this.sessionInjectionMethod=sessionInjectionMethod;
this.applicationInjectionMethod=applicationInjectionMethod;
this.filesInjectionMethod=filesInjectionMethod;
if(this.isDirectoryAware && this.directoryInjectionMethod==null)
{
this.isDirectoryAware=false;
}
if(this.isRequestAware && this.requestInjectionMethod==null)
{
this.isRequestAware=false;
}
if(this.isSessionAware && this.sessionInjectionMethod==null)
{
this.isSessionAware=false;
}
if(this.isApplicationAware && this.applicationInjectionMethod==null)
{
this.isApplicationAware=false;
}
if(this.isFileAware && this.filesInjectionMethod==null)
{
this.isFileAware=false;
}
}
public String getPath()
{
return this.path;
}
public Class getMappedClass()
{
return this.mappedClass;
}
public Method getMethod()
{
return this.method;
}
public Class[] getParameterTypes()
{
return this.parameterTypes;
}
public Object getObject()
{
if(isRequestAware && isSessionAware && isDirectoryAware && isApplicationAware)
{
return newObject();
}
if(object==null)
{
try
{
object=mappedClass.newInstance();
}catch(Exception exception)
{
}
}
return object;
}
private Object newObject()
{
Object object=null;
try
{
object=mappedClass.newInstance();
}catch(Exception exception)
{
}
return object;
}
public boolean isDirectoryAware()
{
return isDirectoryAware;
}
public boolean isRequestAware()
{
return isRequestAware;
}
public boolean isSessionAware()
{
return isSessionAware;
}
public boolean isApplicationAware()
{
return isApplicationAware;
}
public boolean isReturningSomething()
{
return this.isReturningSomething;
}
public boolean allowGetTypeRequest()
{
return this.allowGetTypeRequest;
}
public boolean allowPostTypeRequest()
{
return this.allowPostTypeRequest;
}
public boolean isFileAware()
{
return isFileAware;
}
public long getMaxFileSize()
{
return this.maxFileSize;
}
public Method getDirectoryInjectionMethod()
{
return this.directoryInjectionMethod;
}
public Method getRequestInjectionMethod()
{
return this.requestInjectionMethod;
}
public Method getSessionInjectionMethod()
{
return this.sessionInjectionMethod;
}
public Method getApplicationInjectionMethod()
{
return this.applicationInjectionMethod;
}
public Method getFilesInjectionMethod()
{
return this.filesInjectionMethod;
}

}
