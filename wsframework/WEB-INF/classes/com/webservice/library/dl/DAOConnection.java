package com.webservice.library.dl;

import java.sql.*;

public class DAOConnection {

  private DAOConnection() {
  }

  public static Connection getConnection() throws DAOException {
    Connection connection = null;
    try {
      // Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/wsframeworkdb?characterEncoding=utf8",
          "root",
          "jahajpuriya");
      return connection;
    } catch (Exception exception) {
      System.out.println(exception);
      throw new DAOException(exception.getMessage());
    }
  }
}