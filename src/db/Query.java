package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	Connection connection;
	Statement statement;
	ResultSet result;
	
	/**
	 * Returns a Query object
	 * @param connection
	 * @param statement
	 * @param result
	 */
	public Query(Connection connection, Statement statement, ResultSet result) {
		this.connection = connection;
		this.statement = statement;
		this.result = result;
	}
	
	/**
	 * Returns result set
	 * @return
	 */
	public ResultSet getResult() {
		return result;
	}
	
	/**
	 * Closes connection and statement so the database won't run out of connections
	 */
	public void close() {
		try {
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
