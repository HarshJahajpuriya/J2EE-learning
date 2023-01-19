package com.webservice.hammerprice.dl;

import java.util.Date;

public class Bid implements java.io.Serializable {

	private long id;
	private ServiceProvider serviceProvider;
	private double value;
	private Date timeStamp;

	public Bid() {
	}

	public Bid(long id, ServiceProvider serviceProvider, double value, Date timeStamp) {
		this.id = id;
		this.serviceProvider = serviceProvider;
		this.value = value;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public ServiceProvider getServiceProvider() {
		return this.serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
