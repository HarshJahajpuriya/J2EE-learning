package com.thinking.machines.hr.beans;

import java.util.*;
import java.math.*;
import java.text.*;

public class EmployeeBean {
private String employeeId;
private String name;
private int designationCode;
private String designation;
private String dateOfBirth;
private String gender;
private Boolean isIndian;
private String basicSalary;
private String panNumber;
private String aadharCardNumber;

public EmployeeBean() {
this.setEmployeeId("");
this.setName("");
this.setDesignationCode(-1);
this.setDesignation("");
this.setDateOfBirth("");
this.setGender("");
this.setIsIndian(true);
this.setBasicSalary("");
this.setPanNumber("");
this.setAadharCardNumber("");
}

public String getAadharCardNumber() {
  return aadharCardNumber;
}

public void setAadharCardNumber(String aadharCardNumber) {
  this.aadharCardNumber = aadharCardNumber;
}

public String getPanNumber() {
  return panNumber;
}

public void setPanNumber(String panNumber) {
  this.panNumber = panNumber;
}

public String getBasicSalary() {
  return this.basicSalary;
}

public void setBasicSalary(String basicSalary) {
  this.basicSalary = basicSalary;
}

public Boolean getIsIndian() {
  return isIndian;
}

public void setIsIndian(Boolean isIndian) {
  this.isIndian = isIndian;
}

public String getGender() {
  return gender;
}

public void setGender(String gender) {
  this.gender = gender;
}

public boolean isMale() {
return this.gender.equals("M");
}

public boolean isFemale() {
return this.gender.equals("F");
}

public String getEmployeeId() {
  return employeeId;
}

public void setEmployeeId(String employeeId) {
  this.employeeId = employeeId;
}

public String getName() {
  return name;
}

public void setName(String name) {
  this.name = name;
}

public int getDesignationCode() {
  return designationCode;
}

public void setDesignationCode(int designationCode) {
  this.designationCode = designationCode;
}

public String getDesignation() {
  return designation;
}

public void setDesignation(String designation) {
  this.designation = designation;
}

public String getDateOfBirth() {
	return this.dateOfBirth;
}

public void setDateOfBirth(String  dateOfBirth) {
this.dateOfBirth = dateOfBirth;
}
}



