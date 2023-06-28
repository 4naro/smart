//package com.example.smart.login;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.example.smart.javabean.User;
//
//
//public class UserDao implements AutoCloseable {
//	private static final String GET_USER_BY_EMAIL_AND_PASSWORD = """
//			SELECT USER_ID, EMAIL
//			FROM EVERYBODY
//			WHERE EMAIL = ? AND PASSWORD = ?""";
//	private static final String INSERT_USER = """
//			INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)
//			""";
//
//	private static final Logger log = LogManager.getLogger(UserDao.class);
//
//	
//	private Connection connection;
//
//	public UserDao(DataSource ds) {
//		log.traceEntry();
//
//		try {
//			this.connection = ds.getConnection();
//		} catch (SQLException ex) {
//			throw new IllegalStateException(ex);
//		}
//	}
//
//	public User getUser(String email, String password) { // get per ritornare uno User
//
//		try (PreparedStatement stmt = connection.prepareStatement(GET_USER_BY_EMAIL_AND_PASSWORD)) { //
//			stmt.setString(1, email);
//			stmt.setString(2, password);
//			try (ResultSet rs = stmt.executeQuery()) {
//				if (rs.next()) {// se c'è una riga 'email' + password allora avrò la riga corrispondente
//
//					return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)); // creami al volo uno User e
//																						// ricordalo; qui
//					// abbiamo
//					// le colonne in ordine
//				} else {
//					return null;
//				}
//			}
//		} catch (SQLException ex) {
//			throw new IllegalStateException(ex);
//		}
//	}
//
//	public void saveUser(User user) {
//
//		try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
//			ps.setString(1, user.getEmail());
//			ps.setString(2, user.getPassword());
//			ps.executeUpdate();
//		} catch (SQLException se) {
//			log.error("Can't save user " + user.getId(), se);
//		}
//
//	}
//
//	@Override
//	public void close() {
//		try {
//			connection.close();
//		} catch (SQLException ex) {
//			throw new IllegalStateException(ex);
//		}
//
//	}
//
//}