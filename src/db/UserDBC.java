package db;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBC {
	public static User getUser(String username) throws SQLException {
		Connection connection = DBConnector.getConnection();
		// TODO: 
		connection.close();
		return null;
	}
	public static void addUser(String username, String salt, String passwordHash) {
		
	}
	
	
	public static boolean isUsernameUnique(String username){
		
		
		Query query = db.DBConnector.makeQuery("SELECT username FROM User;");
		ResultSet result = query.getResult();
		
		
		try{
			while (result.next()) {
				if (result.getString("username").equals(username)){
					return false;
				}
			} 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query.close();
		return true;
		
	}
}
