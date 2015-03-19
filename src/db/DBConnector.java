package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	private static final String URL = "jdbc:mysql://mysql.stud.ntnu.no/thomahan_pu_g41_kalender";
	private static final String USERNAME = "thomahan_pu_g41";
	private static final String PASSWORD = "g41aahmt";

	/**
	 * Establishes a connection to the database
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
	public static Query makeQuery(String query) {
		Connection connection = null;
		Statement st = null;
		ResultSet result = null;
		Query queryObject = null;

		try {
			connection = getConnection();
			st = connection.createStatement();
			result = st.executeQuery(query);
			queryObject = new Query(connection, st, result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return queryObject;
	}

	/**
	 * Executes a statement on the database
	 * @param statement
	 * @throws SQLException
	 */
	public static void makeStatement(String statement) {
		Connection connection = null;
		Statement st = null;
		try {
			connection = getConnection();
			st = connection.createStatement();
			st.executeUpdate(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
