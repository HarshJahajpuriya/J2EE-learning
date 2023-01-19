package com.webservice.hammerprice.services;

import com.webservice.framework.annotations.Path;
import com.webservice.framework.annotations.Post;
import com.webservice.hammerprice.dl.DAOException;
import com.webservice.hammerprice.dl.User;
import com.webservice.hammerprice.dl.UserDAO;

@Path("/user")
public class UserService {

  @Path("getUser")
  @Post
  public Object getUser(String userName, String password) {
    try {
      UserDAO userDAO = new UserDAO();
      User userToSend = userDAO.getUser(userName, password);
      return userToSend;
    } catch(DAOException daoException) {
      return daoException;
    } catch (Exception e) {
      return e;
    }
  }

}
