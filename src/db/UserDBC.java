package db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDBC {
	/**
	 * Returns a User object with data from the database
	 * @param username
	 * @return User
	 * @throws SQLException
	 */
	public static User getUser(String username) {
		User user = null;
		Query query = DBConnector.makeQuery(""
				+ "SELECT * "
				+ "FROM user "
				+ "WHERE username = '"+username+"';");
		ResultSet result = query.getResult();

		try {
			if (result.next()) {
				int id = result.getInt("id");
				String salt = result.getString("salt");
				String passwordHash = result.getString("password_hash");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				user = new User(id, firstName, lastName, username, salt, passwordHash);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return user;
	}

	/**
	 * Adds a user to the database
	 * @param user
	 */
	public static void addUser(User user) {
		DBConnector.makeStatement(""
				+ "INSERT INTO user (first_name, last_name, username, salt, password_hash) "
				+ "VALUES ("+user.getFirstName()+", "
							+user.getLastName()+", "
							+user.getUsername()+", "
							+user.getSalt()+", "
							+user.getPasswordHash()+");");
		
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
