package com.thinking.machines.hr.dl;

public class AdministratorDTO implements java.io.Serializable, java.lang.Comparable<AdministratorDTO> {
  private String userName;
  private String password;

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public boolean equals(Object object) {
    if (!(object instanceof AdministratorDTO))
      return false;
    AdministratorDTO other = (AdministratorDTO) object;
    return this.getUserName().equals(other.getUserName());
  }

  public int compareTo(AdministratorDTO other) {
    return this.getUserName().compareToIgnoreCase(other.getUserName());
  }

  public int hashCode() {
    return this.getUserName().hashCode();
  }
}