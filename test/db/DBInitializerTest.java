package db;

import db.DBInitializer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DBInitializerTest {
	@Test
	public void tablesShouldBeInitializedInDatabase() {
		ResultSet result = DBConnector.makeQuery("SHOW TABLES;");
		try {
			System.out.println(result.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(result.toString().equals(""));
/*
		DBInitializer.initializeDB();

		result = DBConnector.makeQuery("SELECT * FROM user;");
		assertNotNull(result);*/
	}
}
