package com.example.smart.login;

import javax.sql.DataSource;

import com.example.smart.javabean.Place;
import com.example.smart.javabean.User;

public class LoginSvc {

	public User getUser(DataSource ds, String email, String password) { // in base al type chiamo userDao/placeDao
		try (UserDao dao = new UserDao(ds)) {
			return dao.get(email, password);
		}
	}

	public Place getPlace(DataSource ds, String email, String password) {
		
		try (PlaceDao dao = new PlaceDao(ds)) { 
			return dao.get(email, password); //ritorno un place che dovrò girare alla Servlet
		}
	}
}
