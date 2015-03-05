package db;

import db.DBInitializer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DBInitializerTest {
	@Test
	public void tablesShouldBeInitializedInDatabase() {
		Query query = DBConnector.makeQuery("SHOW TABLES;");
		ResultSet result = query.getResult();
		boolean hasTable = true;
		try {
			hasTable = result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query.close();
		assertFalse(hasTable);

		DBInitializer.createTables();

		query = DBConnector.makeQuery("SHOW TABLES;");
		result = query.getResult();
		hasTable = false;
		try {
			hasTable = result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query.close();
		assertTrue(hasTable);
	}
}
