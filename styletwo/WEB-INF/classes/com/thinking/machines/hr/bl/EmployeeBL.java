package com.thinking.machines.hr.bl;

import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.math.*;
import java.text.*;
import java.util.*;

public class EmployeeBL 
{
public List<EmployeeBean> getAll() 
{
List<EmployeeBean> employees = new LinkedList<>();
try 
{
EmployeeDAO employeeDAO = new EmployeeDAO();
List<EmployeeDTO> employeeDTOs = employeeDAO.getAll();

String employeeId;
String name;
int designationCode;
String designation;
String dateOfBirth;
String gender;
Boolean isIndian;
String basicSalary;
String panNumber;
String aadharCardNumber;
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

for(EmployeeDTO employeeDTO: employeeDTOs) 
{

employeeId = employeeDTO.getEmployeeId();
name = employeeDTO.getName();
designationCode = employeeDTO.getDesignationCode();
designation = employeeDTO.getDesignation();
dateOfBirth = sdf.format(employeeDTO.getDateOfBirth());
gender = employeeDTO.getGender();
isIndian = employeeDTO.getIsIndian();
basicSalary = employeeDTO.getBasicSalary().toPlainString();
panNumber = employeeDTO.getPANNumber();
aadharCardNumber = employeeDTO.getAadharCardNumber();

EmployeeBean employee = new EmployeeBean();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDesignation(designation);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian);
employee.setBasicSalary(basicSalary);
employee.setPanNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);

employees.add(employee);
}
} catch(DAOException daoException) {
System.out.println(daoException); // to be changed later on
}
return employees;
}
}
