package com.example.smart.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.smart.javabean.Everybody;
import com.example.smart.javabean.EverybodyType;
import com.example.smart.javabean.Place;
import com.example.smart.javabean.User;

public class EverybodyDao implements AutoCloseable {
	
	private static final String GET_PK= """
			SELECT EVERYBODY_ID
			FROM EVERYBODY
			WHERE EMAIL = ? """;
	private static final String GET_EMAIL_AND_PASSWORD = """
			SELECT EVERYBODY_ID, EMAIL, PASSWORD, TYPE_ID
			FROM EVERYBODY
			WHERE EMAIL = ? AND PASSWORD = ?""";

	private static final String INSERT_USER = """
			  INSER INTO USER (FIRST_NAME, LAST_NAME,EVERYBODY_ID) VALUES (?, ?, ?)
			""";
	private static final String INSERT_IN_EVERYBODY = """
			INSERT INTO EVERYBODY (EMAIL, PASSWORD) VALUES (?, ?)
			SELECT EVERYBODY_ID
			FROM EVERYBODY
			WHERE EMAIL = ? AND PASSWORD = ?
			""";
	private static final String INSERT_PLACE = """
			  INSERT INTO PLACE (EMAIL, PASSWORD, NAME, ADDRESS, PHONE, PEOPLE, EVERYBODY_ID)VALUES (?, ?, ?, ?, ?, ?)
			""";

	private static final Logger log = LogManager.getLogger(PlaceDao.class);

	private Connection connection;

	public EverybodyDao(DataSource ds) {
		log.traceEntry();

		try {
			this.connection = ds.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
	}

	public Everybody get(String email, String password) {

		try (PreparedStatement stmt = connection.prepareStatement(GET_EMAIL_AND_PASSWORD)) { //
			stmt.setString(1, email);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					EverybodyType type = rs.getInt(4) == 1 ? EverybodyType.USER : EverybodyType.PLACE;
					return new Everybody(rs.getInt(1), rs.getString(2), rs.getString(3), type);
				} else {
					return null;

				}
			}
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
	}

	public int getPK(String email) { //ritorna PK dell'everybody che ha quell'email 

		try (PreparedStatement stmt = connection.prepareStatement(GET_PK)) { //
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
				
					return rs.getInt(1);
				} else {
					return 0;
				}
			}
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private Everybody addEverybody(Everybody everybody) {

		try (PreparedStatement stmt = connection.prepareStatement(INSERT_IN_EVERYBODY)) { //
			stmt.setString(1, everybody.getEmail());
			stmt.setString(2, everybody.getPassword());
			stmt.setInt(3, everybody.getType().ordinal());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("No rollback", e);
			}
			throw new IllegalStateException(ex);
		}
		int id = getPK(everybody.getEmail());
		everybody.setId(id);
		return everybody;
	}
	

	public Everybody addUser(User user) {

		try {
			connection.setAutoCommit(false); // potrebbe tirare ecc.
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}

		Everybody everybody = new Everybody(user.getEmail(), user.getPassword(), EverybodyType.USER);
		addEverybody(everybody);
		
		try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) { //
			stmt.setString(1, user.getfirstName());
			stmt.setString(2, user.getlastName());
			stmt.setInt(3, everybody.getId());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("No rollback", e);
			}
			throw new IllegalStateException(ex);
		}
		return everybody;

	}

	public Everybody addPlace(Place place) {

		try {
			connection.setAutoCommit(false); // potrebbe tirare ecc.
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}

		Everybody everybody = new Everybody(place.getEmail(), place.getPassword(), EverybodyType.PLACE);
		addEverybody(everybody);

		try (PreparedStatement stmt = connection.prepareStatement(INSERT_PLACE)) { //
			stmt.setString(1, place.getName());
			stmt.setString(2, place.getAddress());
			stmt.setString(3, place.getPhone());
			stmt.setInt(4, place.getPeople());
			stmt.setInt(5, everybody.getId());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("No rollback", e);
			}
			throw new IllegalStateException(ex);
		}

		try {
			connection.commit();
		} catch (SQLException e) {

			log.error("No rollback", e);
			throw new IllegalStateException(e);
		}

		return everybody;
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
