package com.webservice.hammerprice.dl;
// Generated 27-Apr-2022, 5:29:00 pm by Hibernate Tools 5.4.27.Final

import java.util.Date;

/**
 * UserDetails generated by hbm2java
 */
public class UserDetails implements java.io.Serializable {

	private long userId;
	private String mobileNumber;
	private String email;
	private String name;
	private Character gender;
	private Date dob;
	private String imageUrl;
	private String aadharNumber;
	private String panNumber;
	private Date creationTimeStamp;
	private Address address;

	public UserDetails() {
	}

	public UserDetails(User user, String mobileNumber, String email, String name, Date creationTimeStamp) {
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.name = name;
		this.creationTimeStamp = creationTimeStamp;
	}

	public UserDetails(User user, String mobileNumber, String email, String name, Character gender, Date dob,
			String imageUrl, String aadharNumber, String panNumber, Date creationTimeStamp, Address address) {
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.imageUrl = imageUrl;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.creationTimeStamp = creationTimeStamp;
		this.address = address;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAadharNumber() {
		return this.aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return this.panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Date getCreationTimeStamp() {
		return this.creationTimeStamp;
	}

	public void setCreationTimeStamp(Date creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
