package com.webservice.library.services;

import java.util.*;
import com.webservice.framework.*;
import com.webservice.framework.annotations.*;
import com.webservice.library.dl.*;

@Path("/author")
public class AuthorService {
    @Path("getAll")
    public Object getAll() {
        LinkedList<Author> authors;
        AuthorDAO authorDao;
        try {
            authorDao = new AuthorDAO();
            authors = authorDao.getAll();
            return authors;
        } catch (DAOException daoException) {
            return daoException;
        } catch (Exception exception) {
            return exception;
        }
    }
    @Post
    @Path("add")
    public Object add(Author author) {
        try {
            AuthorDAO authorDao = new AuthorDAO();
            authorDao.add(author);
            return author;
        } catch (DAOException daoException) {
            return daoException;
        } catch (Exception exception) {
            return exception;
        }
    }
    @Post
    @Path("delete")
    public Object delete(int code) {
        try {
            AuthorDAO authorDao = new AuthorDAO();
            authorDao.remove(code);
            return "Author Deleted";
        } catch (DAOException daoException) {
            return daoException;
        } catch (Exception exception) {
            return exception;
        }
    }
}
