package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/thomahan_pu_g41_kalender";
	private static String username = "thomahan_pu_g41";
	private static String password = "g41aahmt";

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
		Statement st = null;
		ResultSet result = null;

		try {
			connection = connect();
			st = connection.createStatement();
			result = st.executeQuery(query);
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

		return result;
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
			connection = connect();
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
