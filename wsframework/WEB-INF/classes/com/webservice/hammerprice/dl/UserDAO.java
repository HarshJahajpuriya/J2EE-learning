package com.webservice.hammerprice.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
  public User getUser(String userName, String password) throws DAOException {
    User userToSend = new User();
    try {
      Connection connection = DAOConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
      "select * from user where user_name = ?"
      );
      preparedStatement.setString(1, userName);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next() == false) {
        System.out.println("1");
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Invalid User : " + userName);
      } 
      userToSend.setId(resultSet.getLong("id"));
      userToSend.setUserName(resultSet.getString("user_name"));
      userToSend.setPassword(resultSet.getString("password"));
      userToSend.setEnabled(resultSet.getByte("enabled"));
      if(!password.equals(userToSend.getPassword())) {
        System.out.println("2");
        resultSet.close();
        preparedStatement.close();
        connection.close();
        throw new DAOException("Invalid Password");
      }
      userToSend.setId(resultSet.getLong("id"));
      userToSend.setUserName(resultSet.getString("user_name"));
      userToSend.setEnabled(resultSet.getByte("enabled"));;
      resultSet.close();
      preparedStatement.close();
      preparedStatement = connection.prepareStatement(
        "select * from user_details where user_id = ?"
      );
      preparedStatement.setLong(1, userToSend.getId());
      resultSet = preparedStatement.executeQuery();

      UserDetails userDetails = new UserDetails();
      if(resultSet.next() != false) {
        System.out.println("3");
        userDetails.setUserId(resultSet.getLong("user_id"));  
        userDetails.setMobileNumber(resultSet.getString("mobile_number"));  
        userDetails.setEmail(resultSet.getString("email"));  
        userDetails.setName(resultSet.getString("name"));  
        userDetails.setGender(resultSet.getString("gender").charAt(0));  
        userDetails.setDob(resultSet.getDate("dob")); 
        userDetails.setImageUrl(resultSet.getString("image_url"));
        userDetails.setAadharNumber(resultSet.getString("aadhar_number"));
        userDetails.setPanNumber(resultSet.getString("pan_number"));
        userDetails.setCreationTimeStamp(resultSet.getDate("creation_time_stamp"));
        resultSet.close();
        preparedStatement.close();
        
        preparedStatement = connection.prepareStatement(
          "select * from address where user_details_user_id = ?"
        );
        preparedStatement.setLong(1, userDetails.getUserId());

        resultSet = preparedStatement.executeQuery();
        Address address = new Address();
        if(resultSet.next()) {
          System.out.println("4");
          address.setUserDetailsUserId(resultSet.getLong("user_details_user_id"));
          address.setAddress(resultSet.getString("address"));
          address.setCity(resultSet.getString("city"));
          address.setState(resultSet.getString("state"));
          address.setPincode(resultSet.getString("pincode"));
        }
        userDetails.setAddress(address);

      } 
      userToSend.setUserDetails(userDetails);

      return userToSend;
      
    } catch(Exception e) {
      System.out.println(e);
    }
    return null;
  }
}
