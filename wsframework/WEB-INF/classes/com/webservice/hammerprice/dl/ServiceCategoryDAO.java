package com.webservice.hammerprice.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ServiceCategoryDAO {
  public List<ServiceCategory> getAll() throws DAOException {
    
    List<ServiceCategory> list = new LinkedList<>();
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
        "select * from service_category"
      );
      ResultSet resultSet = preparedStatement.executeQuery();
      ServiceCategory serviceCategory;
      while(resultSet.next()) { 
        serviceCategory = new ServiceCategory();
        serviceCategory.setId(resultSet.getInt("id"));
        serviceCategory.setName(resultSet.getString("name"));
        serviceCategory.setImageUrl(resultSet.getString("image_url"));
        list.add(serviceCategory);
      }
      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      System.out.println(e);
    }
    return list;

  }

  public ServiceCategory add(ServiceCategory serviceCategory) throws DAOException {
    
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
        "select id from service_category where name = ?"
      );
      preparedStatement.setString(1, serviceCategory.getName());
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()) {
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Service category with name " + serviceCategory.getName() +" already exists.");
      }
      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      System.out.println(new DAOException(e.getMessage()));
    }

    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
        "insert into service_category (name, image_url) values (?,?);"
      );
      preparedStatement.setString(1, serviceCategory.getName());
      preparedStatement.setString(2, serviceCategory.getImageUrl());
      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      resultSet.next();
      serviceCategory.setId(resultSet.getInt(1));

      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return serviceCategory;
  }

}
