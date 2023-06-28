package com.example.smart.javabean;

public class Everybody {

	private int id;
	private String email;
	private String password;
	private EverybodyType type; // FK è stata convertita in ENUM

	public Everybody() {
	}

	public Everybody(int id, String email, String password, EverybodyType type) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EverybodyType getType() {
		return type;
	}

	public void setType(EverybodyType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Everybody [id=" + id + ", email=" + email + ", password=" + password + ", type=" + type + "]";
	}

}
