package com.webservice.hammerprice.dl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Auction implements java.io.Serializable {

	private long id;
	private ServiceConsumer serviceConsumer;
	private ServiceProvider serviceProvider;
	private String description;
	private double budget;
	private String requirements;
	private Date timeStamp;
	private Set bids = new HashSet(0);

	public Auction() {
	}

	public Auction(long id, ServiceConsumer serviceConsumer, ServiceProvider serviceProvider, String description,
			double budget, Date timeStamp) {
		this.id = id;
		this.serviceConsumer = serviceConsumer;
		this.serviceProvider = serviceProvider;
		this.description = description;
		this.budget = budget;
		this.timeStamp = timeStamp;
	}

	public Auction(long id, ServiceConsumer serviceConsumer, ServiceProvider serviceProvider, String description,
			double budget, String requirements, Date timeStamp, Set bids) {
		this.id = id;
		this.serviceConsumer = serviceConsumer;
		this.serviceProvider = serviceProvider;
		this.description = description;
		this.budget = budget;
		this.requirements = requirements;
		this.timeStamp = timeStamp;
		this.bids = bids;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ServiceConsumer getServiceConsumer() {
		return this.serviceConsumer;
	}

	public void setServiceConsumer(ServiceConsumer serviceConsumer) {
		this.serviceConsumer = serviceConsumer;
	}

	public ServiceProvider getServiceProvider() {
		return this.serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBudget() {
		return this.budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getRequirements() {
		return this.requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Set getBids() {
		return this.bids;
	}

	public void setBids(Set bids) {
		this.bids = bids;
	}

}
