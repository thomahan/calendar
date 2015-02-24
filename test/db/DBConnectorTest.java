package db;

import db.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;
import static org.junit.Assert.assertFalse;


public class DBConnectorTest {

	@Test
	public void connectionShouldBeEstablished() {
		DBConnector.loadDriver();
		Connection connection = DBConnector.connect();
		assertFalse(connection == null);
	}
	
	@Test
	public void queryShouldReturnResultSet() {
		ResultSet result = DBConnector.makeQuery("SHOW TABLES;");
		assertFalse(result == null);
	}
}
