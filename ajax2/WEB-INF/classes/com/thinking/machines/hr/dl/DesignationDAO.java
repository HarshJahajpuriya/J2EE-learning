package com.thinking.machines.hr.dl;

import java.util.*;
import java.sql.*;

public class DesignationDAO
{
public void add(DesignationDTO designation) throws DAOException
{
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select * from designation where title = ?"
);
preparedStatement.setString(1, designation.getTitle());
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Designation: " + designation.getTitle() + " exists.");
}
preparedStatement = connection.prepareStatement(
"insert into designation (title) values (?)",
Statement.RETURN_GENERATED_KEYS
);
preparedStatement.setString(1, designation.getTitle());
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();
resultSet.next();
int code = resultSet.getInt(1);
designation.setCode(code);
resultSet.close();
preparedStatement.close();
connection.close();
} catch(SQLException sqlException) 
{
throw new DAOException(sqlException.getMessage());
}
}

public DesignationDTO getByCode(int code) throws DAOException 
{
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(
"Select * from designation where code = ?"
);
preparedStatement.setInt(1, code);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false) 
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation code: " + code);
}
DesignationDTO designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setCode(resultSet.getInt("code"));
designationDTO.setTitle(resultSet.getString("title").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return designationDTO;
} catch(SQLException sqlException) 
{
throw new DAOException(sqlException.getMessage());
}
}

public void update(DesignationDTO designation) throws DAOException 
{
int code = designation.getCode();
String title = designation.getTitle();
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(
"select * from designation where code = ?"
);
preparedStatement.setInt(1, code);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false)
{
// code does not exists.
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Designation");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"select * from designation where title = ? and code != ?"
);
preparedStatement.setString(1, title);
preparedStatement.setInt(2, code);
resultSet = preparedStatement.executeQuery();
if(resultSet.next() == true)
{
// if title already exists against another code
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(title + " exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"update designation set title = ? where code = ?"
);
preparedStatement.setString(1, title);
preparedStatement.setInt(2, code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException) 
{
throw new DAOException(sqlException.getMessage());
}
}

public void deleteByCode(int code) throws DAOException
{
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select * from designation where code = ?"
);
preparedStatement.setInt(1, code);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false) 
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Designation");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"select id from employee where designation_code = ?"
);
preparedStatement.setInt(1, code);
resultSet = preparedStatement.executeQuery();
if(resultSet.next() == true) 
{
int id = resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Cannot delete designation, designation is allotted to employee with Id " + id);
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(
"delete from designation where code = ?"
);
preparedStatement.setInt(1, code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
} catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public List<DesignationDTO> getAll() throws DAOException
{
List<DesignationDTO> designations;
designations = new LinkedList<>();
try
{
Connection connection;
connection = DAOConnection.getConnection();
Statement statement;
statement = connection.createStatement();
ResultSet resultSet;
resultSet = statement.executeQuery("select * from designation order by title");
DesignationDTO designationDTO;
int code;
String title;
while(resultSet.next()) 
{
code = resultSet.getInt("code");
title = resultSet.getString("title").trim();
designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designations.add(designationDTO);
}
resultSet.close();
statement.close();
connection.close();
return designations;
} catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public boolean designationCodeExists(int code) throws DAOException
{
boolean exists = false;
try 
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(
"select * from designation where code = ?"
);
preparedStatement.setInt(1, code);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next()) 
{
exists = true;
} 
resultSet.close();
preparedStatement.close();
connection.close();
} catch(Exception exception) 
{
throw new DAOException(exception.getMessage());
}
return exists;
}

}