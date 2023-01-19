package com.webservice.hammerprice.dl;

import java.util.HashSet;
import java.util.Set;

public class ServiceConsumer implements java.io.Serializable {

	private long id;
	private UserDetails userDetails;
	private Set auctions = new HashSet(0);

	public ServiceConsumer() {
	}

	public ServiceConsumer(long id, UserDetails userDetails) {
		this.id = id;
		this.userDetails = userDetails;
	}

	public ServiceConsumer(long id, UserDetails userDetails, Set auctions) {
		this.id = id;
		this.userDetails = userDetails;
		this.auctions = auctions;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDetails getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Set getAuctions() {
		return this.auctions;
	}

	public void setAuctions(Set auctions) {
		this.auctions = auctions;
	}

}
