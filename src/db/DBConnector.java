package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/thomahan_pu_g41_kalender";
	private static String username = "thomahan_pu_g41";
	private static String password = "g41aahmt";
	
	/**
	 * Loads JDBC driver
	 */
	public static void loadDriver() {
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Establishes a connection to the database
	 * @return Connection
	 */
	public static Connection connect() {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Executes a query and returns the result
	 * @param query
	 * @return Result of query
	 */
	public static ResultSet makeQuery(String query) {
		Connection connection = null;
		ResultSet result = null;
		try {
			connection = connect();
			Statement statement = connection.createStatement();
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
}
