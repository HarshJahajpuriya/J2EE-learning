package com.thinking.machines.hr.dl;

import java.sql.*;

public class AdministratorDAO {
  public AdministratorDTO getByUserName(String userName) throws DAOException {
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
          "select * from administrator where user_name = ?");
      preparedStatement.setString(1, userName);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next() == false) {
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Invalid user name: " + userName);
      }
      AdministratorDTO administratorDTO = new AdministratorDTO();
      userName = resultSet.getString(1);
      String password = resultSet.getString(2);
      administratorDTO.setUserName(userName);
      administratorDTO.setPassword(password);
      resultSet.close();
      preparedStatement.close();
      connection.close();
      return administratorDTO;
    } catch (SQLException sqlException) {
      System.out.println(new DAOException(sqlException.getMessage()));
    }
    return null;
  }
}