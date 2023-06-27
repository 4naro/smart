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

public class EverybodyDao implements AutoCloseable {
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
			  INSERT INTO PLACE (EMAIL, PASSWORD, NAME, ADDRESS, PHONE, PEOPLE)VALUES (?, ?, ?, ?, ?, ?)
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

	public Everybody addUser(int id,String firstName, String lastName, String email, String password, int type_id) {

		try {
			connection.setAutoCommit(false); // potrebbe tirare ecc.
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}

		try (PreparedStatement stmt = connection.prepareStatement(INSERT_IN_EVERYBODY)) { //
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.executeUpdate();

		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("No rollback", e);
			}
			throw new IllegalStateException(ex);
		}
		Everybody everybody = get(email, password);

		try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) { //
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
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
		
		try {
			connection.commit();
		} catch (SQLException e) {

			log.error("No rollback", e);
			throw new IllegalStateException(e);
		}

		return everybody;
	}

	public Everybody addPlace(int id, String email, String password, int type_id) {

		try (PreparedStatement stmt = connection.prepareStatement(INSERT_PLACE)) { //
			stmt.setString(1, email);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					EverybodyType type = rs.getInt(3) == 1 ? EverybodyType.PLACE : EverybodyType.USER;
					return new Everybody(rs.getInt(1), rs.getString(1), rs.getString(2), type);
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
