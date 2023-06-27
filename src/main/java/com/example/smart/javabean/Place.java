package com.example.smart.javabean;

public class Place {

	private int id;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private int people;

	public Place() {

	}

	
	public Place(int id, String email, String password, String name, String address, String phone, int people) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.people = people;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", people=" + people + "]";
	}

}