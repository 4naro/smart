package com.example.smart.javabean;


import java.util.Objects;

//JAVA BEAN
public class User extends Everybody {

	// Data members: inserire colonne tab User
	private int id;
	private String firstName;
	private String lastName;

	// costruttore no args;
	public User() {
	}

	// costruttore canonico


	// Getter and Setter
	public int getId() {
		return id;
	}

	public User(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setId(int id) {
		this.id = id;
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
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
