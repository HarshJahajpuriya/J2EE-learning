package com.webservice.library.dl;

public class Author {
  private Integer id;
  private String name;
  private Character gender;
  private Integer noOfBooksWritten;

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGender(Character gender) {
    this.gender = gender;
  }

  public void setNoOfBooksWritten(Integer n) {
    this.noOfBooksWritten = n;
  }

  public Integer getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Character getGender() {
    return this.gender;
  }

  public Integer getNoOfBooksWritten() {
    return this.noOfBooksWritten;
  }

}
