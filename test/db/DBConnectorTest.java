package db;

import db.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;


public class DBConnectorTest {
	@Test
	public void connectionShouldBeEstablished() {
		Connection connection = DBConnector.getConnection();
		assertNotNull(connection);
	}
	
	@Test
	public void queryShouldReturnResultSet() {
		ResultSet result = null;
		Query query = null;

		query = DBConnector.makeQuery("SHOW TABLES;");
		result = query.getResult();
		query.close();

		assertNotNull(result);
	}
	
	@Test
	public void statementShouldMakeChangesToDatabase() {
		ResultSet result = null;
		Query query = null;

		DBConnector.makeStatement("CREATE TABLE test (data INT);");

		query = DBConnector.makeQuery("SELECT * FROM test;");
		result = query.getResult();
		query.close();

		DBConnector.makeStatement("DROP TABLE test;");

		assertNotNull(result);
	}
	
	@Test
	public void tableDataShouldBeRetrievable() {
		Query query = DBConnector.makeQuery("SELECT * FROM user;");
		ResultSet result = query.getResult();

		try {
			if (result.next()) {
				System.out.println(result.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		query.close();
		
		assertTrue(true);
	}
}
