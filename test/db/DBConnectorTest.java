package db;

import db.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;

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

		result = DBConnector.makeQuery("SHOW TABLES;");

		assertNotNull(result);
	}
	
	@Test
	public void statementShouldMakeChangesToDatabase() {
		ResultSet result = null;

		DBConnector.makeStatement("CREATE TABLE test (data INT);");
		result = DBConnector.makeQuery("SELECT * FROM test;");
		DBConnector.makeStatement("DROP TABLE test;");

		assertNotNull(result);
	}
}
