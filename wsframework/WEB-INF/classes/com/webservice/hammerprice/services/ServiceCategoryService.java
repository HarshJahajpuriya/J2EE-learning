package com.webservice.hammerprice.services;

import com.webservice.framework.annotations.Get;
import com.webservice.framework.annotations.Path;
import com.webservice.framework.annotations.Post;
import com.webservice.hammerprice.dl.ServiceCategory;
import com.webservice.hammerprice.dl.ServiceCategoryDAO;

@Path("/serviceCategory")
public class ServiceCategoryService {

  @Get
  @Path("getAll")
  public Object getAll() {
    try {
      ServiceCategoryDAO serviceCategoryDAO = new ServiceCategoryDAO();
      return serviceCategoryDAO.getAll();
    } catch(Exception exception) {
      return exception;
    }
  }    

  @Post
  @Path("add") 
  public Object addServiceCategory(ServiceCategory serviceCategory) {
    try {
      ServiceCategoryDAO serviceCategoryDAO = new ServiceCategoryDAO();
      return serviceCategoryDAO.add(serviceCategory);
    } catch (Exception e) {
      return e;
    }
  }

}
