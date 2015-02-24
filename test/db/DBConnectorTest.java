package db;

import db.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;


public class DBConnectorTest {
	@Test
	public void connectionShouldBeEstablished() {
		Connection connection = DBConnector.connect();
		assertNotNull(connection);
	}
	
	@Test
	public void queryShouldReturnResultSet() {
		ResultSet result = null;

		try {
			result = DBConnector.makeQuery("SHOW TABLES;");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertNotNull(result);
	}
	
	@Test
	public void statementShouldMakeChangesToDatabase() {
		ResultSet result = null;

		try {
			DBConnector.makeStatement("CREATE TABLE test (data INT);");
			result = DBConnector.makeQuery("SELECT * FROM test;");
			DBConnector.makeStatement("DROP TABLE test;");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertNotNull(result);
	}
}
