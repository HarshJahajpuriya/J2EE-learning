package com.webservice.hammerprice.dl;

public class User implements java.io.Serializable {

	private long id;
	private String userName;
	private String password;
	private byte enabled;
	private UserDetails userDetails;

	public User() {
	}

	public User(long id, String userName, String password, byte enabled) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public User(long id, String userName, String password, byte enabled, UserDetails userDetails) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.userDetails = userDetails;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public UserDetails getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
