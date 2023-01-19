package com.webservice.hammerprice.dl;
public class Authority implements java.io.Serializable {

	private long id;
	private User user;
	private String role;

	public Authority() {
	}

	public Authority(long id, User user, String role) {
		this.id = id;
		this.user = user;
		this.role = role;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
