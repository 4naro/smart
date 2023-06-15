package com.example.smart.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.smart.javabean.Place;

public class PlaceDao implements AutoCloseable {
	private static final String GET_PLACE_BY_EMAIL_AND_PASSWORD = """
			SELECT PLACE_ID, EMAIL
			FROM PLACE
			WHERE EMAIL = ? AND PASSWORD = ?""";
	private static final String INSERT_PLACE = """
			INSERT INTO PLACE (EMAIL, PASSWORD, NAME, ADDRESS, PHONE)VALUES (?, ?, ?, ?, ?)
			""";
	private static final Logger log = LogManager.getLogger(PlaceDao.class);

	// insert query
	// data member che rappesenta la connection
	private Connection connection;

	public PlaceDao(DataSource ds) {
		log.traceEntry();

		try {
			this.connection = ds.getConnection();
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
	}

	public Place getPlace(String email, String password) { // get
																														// per
		// ritornare
		// uno User

		try (PreparedStatement stmt = connection.prepareStatement(GET_PLACE_BY_EMAIL_AND_PASSWORD)) { //
			stmt.setString(1, email);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Place(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6));

				} else {
					return null;

				}
			}
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}

	}

	public void savePlace(Place place) {

		try (PreparedStatement ps = connection.prepareStatement(INSERT_PLACE)) {
			ps.setString(1, place.getEmail());
			ps.setString(2, place.getPassword());
			ps.setString(3, place.getName());
			ps.setString(4, place.getAddress());
			ps.setString(5, place.getPhone());
			ps.executeUpdate();
		} catch (SQLException se) {
			log.error("Can't save place " + place.getId(), se);
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
