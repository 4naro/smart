package com.example.smart.javabean;

public class Place extends Everybody {

	private int id;
	private String name;
	private String address;
	private String phone;
	private int people;

	public Place() {

	}

	public Place(int id, String name, String address, String phone, int people) {

		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.people = people;
	}

	public Place(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", people=" + people
				+ "]";
	}

}