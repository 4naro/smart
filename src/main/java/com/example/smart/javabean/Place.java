package com.example.smart.javabean;

public class Place {

	private int id;
	private String name;
	private String address;
	private String period;
	private String email;
	private String password;
	private int range;
	private int user;

	public Place() {

	}

	public Place(int id) {
		this.id = id;
	}

	public Place(int id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Place(int id, String name, String address, String period, int range, int user) {
		this.name = name;
		this.address = address;
		this.period = period;
		this.range = range;
		this.user = user;
	}


	public Place(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Place [name=" + name + ", address=" + address + ", period=" + period + ", email=" + email
				+ ", password=" + password + ", range=" + range + ", user=" + user + "]";
	}

}