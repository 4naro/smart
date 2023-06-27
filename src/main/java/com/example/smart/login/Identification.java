package com.example.smart.login;

import javax.sql.DataSource;

import com.example.smart.javabean.Everybody;
import com.example.smart.javabean.User;


public class Identification {

	private DataSource ds;

	public Identification(DataSource ds) {
		this.ds = ds;
	}

	public Everybody get(String email, String password) {// in base al type chiamo userDao/placeDao
		if (email == null || email.isEmpty()) {
			// log
			return null;
		}
		try ( EverybodyDao dao = new EverybodyDao(ds)) {
			return dao.get(email, password);
		}
	}
	
	public Everybody addUser(String email, String password) {
		if (email == null || email.isEmpty()) {
			// log
			return null;
		}
		try ( EverybodyDao dao = new EverybodyDao(ds)) {
			return dao.get(email, password);
		}
	}
	
	public Everybody addPlace(String email, String password) {
		if (email == null || email.isEmpty()) {
			// log
			return null;
		}
		try ( EverybodyDao dao = new EverybodyDao(ds)) {
			return dao.get(email, password);
		}
	}


	public User saveUser(String email, String password) {// in base al type chiamo userDao/placeDao
		if (email == null || email.isEmpty()) {
			return null;
		}

		if (password.length() < 8) {
			throw new IllegalStateException("Password must be at least 8 chars");
		}
		try (UserDao dao = new UserDao(ds)) {
			return dao.getUser(email, password);
		}
	}

//	public Place savePlace(String email, String password, String name, String address, String phone) {// in base al type
//																										// chiamo
//		if (email == null || email.isEmpty()) {
//			return null;
//		}
//
//		if (password.length() < 8) {
//			throw new IllegalStateException("Password must be at least 8 chars");
//		}																								// userDao/placeDao
//		if (name == null || address == null || phone == null) {
//			throw new IllegalStateException("you must complete the missing fields");
//		}
//		try (PlaceDao dao = new PlaceDao(ds)) {
//			return dao.getPlace(email, password);
//		}
//	}

}