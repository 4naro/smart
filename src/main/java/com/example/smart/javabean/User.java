package com.example.smart.javabean;


import java.util.Objects;

//JAVA BEAN
public class User {

	// Data members: inserire colonne tab User
	private int id;
	private String email;
	private String password;

	// costruttore no args;
	public User() {
	}

	// costruttore canonico
	public User(int id,String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	// Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", id=" + id + "]";
	}

	// comodo se sappiamo di voler mettere i nostri Users in Hash Set e Hash Map

	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id;
	}
}
