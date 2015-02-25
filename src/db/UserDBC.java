package db;

import model.User;

import java.sql.Connection;

public class UserDBC {
	public static User getUser(String username) {
		Connection connection = DBConnector.getConnection();
		// TODO: 
		connection.close();
		return null;
	}
	public static void addUser(String username, String salt, String passwordHash) {
		
	}
}
