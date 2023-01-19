package com.webservice.hammerprice.dl;

public class Address implements java.io.Serializable {

	private long userDetailsUserId;
	private String address;
	private String city;
	private String state;
	private String pincode;

	public Address() {
	}

	public Address(String address, String city, String state, String pincode) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public long getUserDetailsUserId() {
		return this.userDetailsUserId;
	}

	public void setUserDetailsUserId(long userDetailsUserId) {
		this.userDetailsUserId = userDetailsUserId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
