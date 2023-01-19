package com.thinking.machines.hr.dl;

import java.util.*;
import java.math.*;
import java.io.*;

public class EmployeeDTO implements Serializable, Comparable<EmployeeDTO>
{
private String employeeId;
private String name;
private int designationCode;
private String designation;
private Date dateOfBirth;
private String gender;
private Boolean isIndian;
private BigDecimal basicSalary;
private String panNumber;
private String aadharCardNumber;
public EmployeeDTO()
{
this.employeeId = "";
this.name = "";
this.designationCode = 0;
this.designation = "";
this.dateOfBirth = null;
this.gender = "";
this.isIndian = true;
this.basicSalary = null;
this.panNumber = "";
this.aadharCardNumber = "";
}
public void setEmployeeId(String id)
{
this.employeeId = id;
}
public String getEmployeeId()
{
return this.employeeId;
}
public void setName(String name)
{
this.name = name;
}
public String getName()
{
return this.name;
}
public void setDesignationCode(int code) 
{
this.designationCode = code;
}
public int getDesignationCode()
{
return this.designationCode;
}
public void setDesignation(String designation)
{
this.designation = designation;
}
public String getDesignation()
{
return this.designation;
}
public void setDateOfBirth(Date dateOfBirth)
{
this.dateOfBirth = dateOfBirth;
}
public Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setGender(String gender)
{
this.gender = gender;
}
public String getGender()
{
return this.gender;
}
public void setIsIndian(Boolean isIndian)
{
this.isIndian = isIndian;
}
public Boolean getIsIndian()
{
return this.isIndian;
}
public void setBasicSalary(BigDecimal salary)
{
this.basicSalary = salary;
}
public BigDecimal getBasicSalary()
{
return this.basicSalary;
}
public void setPANNumber(String panNumber) 
{
this.panNumber = panNumber;
}
public String getPANNumber()
{
return this.panNumber;
}
public void setAadharCardNumber(String aadharCardNumber)
{
this.aadharCardNumber = aadharCardNumber;
}
public String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public boolean equals(Object object)
{
if(!(object instanceof EmployeeDTO)) return false;
return this.getEmployeeId().equals(((EmployeeDTO)object).getEmployeeId());
}
public int compareTo(EmployeeDTO another)
{
return this.getEmployeeId().compareToIgnoreCase(another.getEmployeeId());
}
public int hashCode()
{
return this.getEmployeeId().hashCode();
}
}