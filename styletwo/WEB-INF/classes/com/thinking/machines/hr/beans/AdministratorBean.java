package com.thinking.machines.hr.beans;

public class AdministratorBean 
{
private String userName;
private String password;
public AdministratorBean() 
{
this.userName = "";
this.password = "";
}
public void setUserName(String userName) 
{
this.userName = userName;
}
public String getUserName() 
{
return this.userName;
}
public void setPassword(String password) 
{
this.password = password;
}
public String getPassword() 
{
return this.password;
}
}