package com.webservice.framework;
import java.io.*;
public class FileUploadWrapper
{
private File file;
private String fileName;
private boolean isTemporary;
public FileUploadWrapper()
{
this.file=null;
this.fileName=null;
this.isTemporary=false;
}
public void setFile(File file)
{
this.file=file;
}
public File getFile()
{
return this.file;
}
public void setFileName(String fileName)
{
this.fileName=fileName;
}
public String getFileName()
{
return this.fileName;
}
public void isTemporary(boolean isTemporary)
{
this.isTemporary=isTemporary;
}
public boolean isTemporary()
{
return this.isTemporary;
}
}