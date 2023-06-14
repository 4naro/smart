package com.example.smart.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.smart.javabean.User;

//Dao che ci permette di portare in giro il nostro java bean USER
public class UserDao implements AutoCloseable {
	private static final String GET_BY_USERNAME_AND_PASSWORD = "";
	private static final Logger log = LogManager.getLogger(UserDao.class);

	// SELECT USER_ID, EMAIL
	// FROM PERSON
	// WHERE EMAIL = ? AND PASSWORD = ?;

	// data member che rappesenta la connection
	private Connection connection;

	public UserDao(DataSource ds) {
		log.traceEntry();

		try {
			this.connection = ds.getConnection();
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
	}

	public User get(String email, String password) { // get per ritornare uno User

		try (PreparedStatement stmt = connection.prepareStatement(GET_BY_USERNAME_AND_PASSWORD)) { //
			stmt.setString(1, email);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {// se c'è una riga 'email' + password allora avrò la riga corrispondente
                    
					return new User(rs.getInt(1), rs.getString(2), rs.getString(3)); // creami al volo uno User e
																						// ricordalo; qui
					// abbiamo
					// le colonne in ordine
				} else {
					return null;
				}
			}
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}

	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}

	}
}