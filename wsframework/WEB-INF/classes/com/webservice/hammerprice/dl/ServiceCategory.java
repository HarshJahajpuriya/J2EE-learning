package com.webservice.hammerprice.dl;

import java.util.HashSet;
import java.util.Set;

public class ServiceCategory implements java.io.Serializable {

	private int id;
	private String name;
	private String imageUrl;

	public ServiceCategory() {
	}

	public ServiceCategory(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public ServiceCategory(int id, String name, String imageUrl) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
