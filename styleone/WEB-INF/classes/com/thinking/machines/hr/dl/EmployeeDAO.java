package com.thinking.machines.hr.dl;

import java.sql.*;
import java.util.*;
import java.math.*;

public class EmployeeDAO 
{

public void add(EmployeeDTO employee) throws DAOException
{
try 
{
String panNumber = employee.getPANNumber();
String aAdharCardNumber = employee.getAadharCardNumber();
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select id from employee where pan_number = ?"
);
preparedStatement.setString(1, panNumber);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number " + panNumber + " exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"select id from employee where aadhar_card_number = ?"
);
preparedStatement.setString(1, aAdharCardNumber);
resultSet = preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number " + aAdharCardNumber + " exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"insert into employee(name, designation_code, date_of_birth, gender, is_indian, basic_salary, pan_number, aadhar_card_number) values(?,?,?,?,?,?,?,?)",
Statement.RETURN_GENERATED_KEYS
);
preparedStatement.setString(1, employee.getName());
preparedStatement.setInt(2, employee.getDesignationCode());
java.util.Date date = employee.getDateOfBirth();
java.sql.Date sqlDate = new java.sql.Date(date.getTime());
preparedStatement.setDate(3, sqlDate);
preparedStatement.setString(4, employee.getGender().charAt(0)+"");
preparedStatement.setBoolean(5, employee.getIsIndian());
preparedStatement.setBigDecimal(6, employee.getBasicSalary());
preparedStatement.setString(7, employee.getPANNumber());
preparedStatement.setString(8, employee.getAadharCardNumber());
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();
resultSet.next();
String id = "A" + resultSet.getInt(1);
employee.setEmployeeId(id);
resultSet.close();
preparedStatement.close();
connection.close();
} catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public void update(EmployeeDTO employee) throws DAOException
{
String employeeId = employee.getEmployeeId();
int actualEmployeeId = -1;
try 
{
actualEmployeeId = Integer.parseInt(employeeId.substring(1));
} catch(NumberFormatException nfe)
{
throw new DAOException("Invalid Employee Id : " + employeeId);
}
try 
{
String panNumber = employee.getPANNumber();
String aAdharCardNumber = employee.getAadharCardNumber();
Connection connection;
PreparedStatement preparedStatement;
ResultSet resultSet;
connection = DAOConnection.getConnection();
preparedStatement = connection.prepareStatement(
"select gender from employee where id = ?"
);
preparedStatement.setInt(1, actualEmployeeId);
resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Employee Id : " + employeeId);
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"select id from employee where pan_number = ? and id <> ?"
);
preparedStatement.setString(1, panNumber);
preparedStatement.setInt(2, actualEmployeeId);
resultSet = preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number " + panNumber + " exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"select id from employee where aadhar_card_number = ? and id <> ?"
);
preparedStatement.setString(1, aAdharCardNumber);
preparedStatement.setInt(2, actualEmployeeId);
resultSet = preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number " + aAdharCardNumber + " exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"update employee set name=?, designation_code=?, date_of_birth=?, gender=?, is_indian=?, basic_salary=?, pan_number=?, aadhar_card_number=? where id=?"
);
preparedStatement.setString(1, employee.getName());
preparedStatement.setInt(2, employee.getDesignationCode());
java.util.Date date = employee.getDateOfBirth();
java.sql.Date sqlDate = new java.sql.Date(date.getTime());
preparedStatement.setDate(3, sqlDate);
preparedStatement.setString(4, employee.getGender().charAt(0)+"");
preparedStatement.setBoolean(5, employee.getIsIndian());
preparedStatement.setBigDecimal(6, employee.getBasicSalary());
preparedStatement.setString(7, employee.getPANNumber());
preparedStatement.setString(8, employee.getAadharCardNumber());
preparedStatement.setInt(9, actualEmployeeId);
preparedStatement.executeUpdate();
resultSet.close();
preparedStatement.close();
connection.close();
} catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public List<EmployeeDTO> getAll() throws DAOException
{
List<EmployeeDTO> employees = new LinkedList<>();
try 
{
Connection connection = DAOConnection.getConnection();
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("select employee.id, employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number, employee.aadhar_card_number from employee inner join designation on employee.designation_code = designation.code order by employee.name");
String employeeId;
String name;
int designationCode;
String designation;
java.util.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;
EmployeeDTO employee;
while(resultSet.next())
{
employeeId = "A" + resultSet.getString("id");
name = resultSet.getString("name").trim();
designationCode = resultSet.getInt("designation_code");
designation = resultSet.getString("title").trim();
dateOfBirth = resultSet.getDate("date_of_birth");
gender = resultSet.getString("gender");
isIndian = resultSet.getBoolean("is_indian");
basicSalary = resultSet.getBigDecimal("basic_salary");
panNumber = resultSet.getString("pan_number").trim();
aadharCardNumber = resultSet.getString("aadhar_card_number").trim();
employee = new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDesignation(designation);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
employees.add(employee);
}
return employees;
} catch(Exception exception) 
{
throw new DAOException(exception.getMessage());
}
}

public boolean panNumberExists(String panNumber) throws DAOException
{
boolean exists = false;
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select id from employee where pan_number = ?"
);
preparedStatement.setString(1, panNumber);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next()) 
{
exists = true;
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return exists;
}

public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
boolean exists = false;
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select id from employee where aadhar_card_number = ?"
);
preparedStatement.setString(1, aadharCardNumber);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next())
{
exists = true;
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return exists;
}

public EmployeeDTO getByEmployeeId(String employeeId) throws DAOException
{
EmployeeDTO employee = null;
int id=-1;
try 
{
id = Integer.parseInt(employeeId.substring(1));
} catch(NumberFormatException nfe)
{
System.out.println("1");
throw new DAOException("Invalid Employee Id");
}
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number, employee.aadhar_card_number from employee inner join designation on employee.designation_code = designation.code where employee.id = ?"
);
preparedStatement.setInt(1,id);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
System.out.println("1");
throw new DAOException("Invalid Employee Id");
}
String name;
int designationCode;
String designation;
java.util.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;
name = resultSet.getString("name").trim();
designationCode = resultSet.getInt("designation_code");
designation = resultSet.getString("title").trim();
dateOfBirth = resultSet.getDate("date_of_birth");
gender = resultSet.getString("gender");
isIndian = resultSet.getBoolean("is_indian");
basicSalary = resultSet.getBigDecimal("basic_salary");
panNumber = resultSet.getString("pan_number").trim();
aadharCardNumber = resultSet.getString("aadhar_card_number").trim();
employee = new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDesignation(designation);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
return employee;
}catch(SQLException exception)
{
System.out.println(exception+"HERE");
throw new DAOException(exception.getMessage());	// remove after testing
}
}

public EmployeeDTO getByPANNumber(String panNumber) throws DAOException
{
EmployeeDTO employee = null;
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select employee.id, employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number, employee.aadhar_card_number from employee inner join designation on employee.designation_code = designation.code where employee.pan_number = ?"
);
preparedStatement.setString(1, panNumber);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
System.out.println("1");
throw new DAOException("Employee with " + panNumber +" Pan number does not exists.");
}
String employeeId;
String name;
int designationCode;
String designation;
java.util.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String aadharCardNumber;
employeeId = "A" + resultSet.getInt("id");
name = resultSet.getString("name").trim();
designationCode = resultSet.getInt("designation_code");
designation = resultSet.getString("title").trim();
dateOfBirth = resultSet.getDate("date_of_birth");
gender = resultSet.getString("gender");
isIndian = resultSet.getBoolean("is_indian");
basicSalary = resultSet.getBigDecimal("basic_salary");
panNumber = resultSet.getString("pan_number").trim();
aadharCardNumber = resultSet.getString("aadhar_card_number").trim();
employee = new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDesignation(designation);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
return employee;
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());	// remove after testing
}
}

public EmployeeDTO getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
EmployeeDTO employee = null;
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select employee.id, employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number, employee.aadhar_card_number from employee inner join designation on employee.designation_code = designation.code where employee.aadhar_card_number = ?"
);
preparedStatement.setString(1, aadharCardNumber);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Employee with " + aadharCardNumber +" Aadhar Card number does not exists.");
}
String employeeId;
String name;
int designationCode;
String designation;
java.util.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
employeeId = "A" + resultSet.getInt("id");
name = resultSet.getString("name").trim();
designationCode = resultSet.getInt("designation_code");
designation = resultSet.getString("title").trim();
dateOfBirth = resultSet.getDate("date_of_birth");
gender = resultSet.getString("gender");
isIndian = resultSet.getBoolean("is_indian");
basicSalary = resultSet.getBigDecimal("basic_salary");
panNumber = resultSet.getString("pan_number").trim();
aadharCardNumber = resultSet.getString("aadhar_card_number").trim();
employee = new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDesignation(designation);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
return employee;
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());	// remove after testing
}
}


public boolean employeeIdExists(String employeeId) throws DAOException
{
int id=-1;
try 
{
id = Integer.parseInt(employeeId.substring(1));
} catch(NumberFormatException nfe)
{
return false;
}
boolean exists = false;
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select gender from employee where id = ?"
);
preparedStatement.setInt(1,id);
ResultSet resultSet = preparedStatement.executeQuery();
exists = resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
return exists;
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());	// remove after testing
}
}

public void deleteByEmployeeId(String id) throws DAOException
{
int employeeId = -1;
try 
{
employeeId = Integer.parseInt(id.substring(1));
} catch(Exception exception)
{
throw new DAOException("Invalid Employee Id");
}
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select gender from employee where id = ?"
);
preparedStatement.setInt(1, employeeId);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false) 
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Employee Id");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"delete from employee where id = ?"
);
preparedStatement.setInt(1, employeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
} catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
}