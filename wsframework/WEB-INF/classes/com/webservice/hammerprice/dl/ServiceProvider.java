package com.webservice.hammerprice.dl;

import java.util.HashSet;
import java.util.Set;

public class ServiceProvider implements java.io.Serializable {

	private long id;
	private ServiceCategory serviceCategory;
	private UserDetails userDetails;
	private Set auctions = new HashSet(0);

	public ServiceProvider() {
	}

	public ServiceProvider(long id, ServiceCategory serviceCategory, UserDetails userDetails) {
		this.id = id;
		this.serviceCategory = serviceCategory;
		this.userDetails = userDetails;
	}

	public ServiceProvider(long id, ServiceCategory serviceCategory, UserDetails userDetails, Set auctions) {
		this.id = id;
		this.serviceCategory = serviceCategory;
		this.userDetails = userDetails;
		this.auctions = auctions;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
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
