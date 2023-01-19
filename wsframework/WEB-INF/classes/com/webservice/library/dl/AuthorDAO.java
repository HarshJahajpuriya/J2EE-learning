package com.webservice.library.dl;

import java.sql.*;
import java.util.*;

public class AuthorDAO {

  public Author getAuthor(int id) throws DAOException {
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
          "select * from author where id = ?");
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next() == false) {
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Invalid Author Id: " + id);
      }

      Author author = new Author();
      id = resultSet.getInt("id");
      String name = resultSet.getString("name");
      Character gender = resultSet.getString("gender").charAt(0);
      Integer noOfBooksWritten = resultSet.getInt("noOfBooks");

      author.setId(id);
      author.setName(name);
      author.setGender(gender);
      author.setNoOfBooksWritten(noOfBooksWritten);

      resultSet.close();
      preparedStatement.close();
      connection.close();
      return author;

    } catch (SQLException sqlException) {
      System.out.println(new DAOException(sqlException.getMessage()));
    }
    return null;
  }

  public Author getByName(String name) throws DAOException {
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
          "select * from author where name = ?");
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next() == false) {
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Invalid Author Name: " + name);
      }

      Author author = new Author();
      int id = resultSet.getInt("id");
      name = resultSet.getString("name");
      Character gender = resultSet.getString("gender").charAt(0);
      Integer noOfBooksWritten = resultSet.getInt("noOfBooks");

      author.setId(id);
      author.setName(name);
      author.setGender(gender);
      author.setNoOfBooksWritten(noOfBooksWritten);

      resultSet.close();
      preparedStatement.close();
      connection.close();
      return author;

    } catch (SQLException sqlException) {
      System.out.println(new DAOException(sqlException.getMessage()));
    }
    return null;
  }

  public void add(Author author) throws DAOException {
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
          "select * from author where name = ?");
      preparedStatement.setString(1, author.getName());

      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()==true) {      
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Author: " + author.getName() + " exists");
      }

      resultSet.close();
      preparedStatement.close();
      preparedStatement = connection.prepareStatement(
      "insert into author (name, gender, noOfBooks) values (?,?,?)",
      Statement.RETURN_GENERATED_KEYS
      );

      preparedStatement.setString(1, author.getName());
      preparedStatement.setString(2, ""+author.getGender());
      preparedStatement.setInt(3, author.getNoOfBooksWritten());
      preparedStatement.executeUpdate();
      resultSet = preparedStatement.getGeneratedKeys();
      resultSet.next();

      int id = resultSet.getInt(1);

      author.setId(id);

      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (SQLException sqlException) {
      System.out.println(new DAOException(sqlException.getMessage()));
    }
  }

  public LinkedList<Author> getAll() throws DAOException {
    LinkedList<Author> authors = new LinkedList<>();
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
          "select * from author ");
      ResultSet resultSet = preparedStatement.executeQuery();
      
      int id, noOfBooksWritten;
      String name;
      Character gender;

      while(resultSet.next()) {
        id = resultSet.getInt("id");
        name = resultSet.getString("name");
        gender = resultSet.getString("gender").charAt(0);
        noOfBooksWritten = resultSet.getInt("noOfBooks");
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setGender(gender);
        author.setNoOfBooksWritten(noOfBooksWritten);
        authors.add((author));
      }
      
      
      resultSet.close();
      preparedStatement.close();
      connection.close();
      return authors;

    } catch (SQLException sqlException) {
      System.out.println(new DAOException(sqlException.getMessage()));
    }
    return null;
  }

  public void remove(int id) throws DAOException {
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
          "select * from author where id = ?");
      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()==false) {      
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Invalid code");
      }

      resultSet.close();
      preparedStatement.close();

      preparedStatement = connection.prepareStatement(
      "delete from author where id=?"
      );

      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();      

      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (SQLException sqlException) {
      System.out.println(new DAOException(sqlException.getMessage()));
    }
  }
  
}
