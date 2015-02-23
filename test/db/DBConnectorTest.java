package db;

import db.DBConnector;
import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.assertFalse;


public class DBConnectorTest {
	public void connectionShouldBeEstablished() {
		ResultSet result = DBConnector.makeQuery("SHOW TABLES;");
		assertFalse(result.toString().equals(""));
	};
}
