package com.example.smart.javabean;


import java.util.Objects;

//JAVA BEAN
public class User {

	// Data members: inserire colonne tab User
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	// costruttore no args;
	public User() {
	}

	// costruttore canonico


	// Getter and Setter
	public int getId() {
		return id;
	}

	public User(int id, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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
	

	public String getfirstName() {
		return firstName;
	}

	public void setFirst_name(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setLast_name(String lastName) {
		this.lastName = lastName;
	}



	// comodo se sappiamo di voler mettere i nostri Users in Hash Set e Hash Map

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + firstName + ", last_name=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

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
