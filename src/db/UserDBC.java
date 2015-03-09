package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				String salt = result.getString("salt");
				String passwordHash = result.getString("password_hash");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				user = new User(username, salt, passwordHash, firstName, lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return user;
	}

	/**
	 * Returns a list of users from the database
	 * @return List of users
	 */
	public static ArrayList<User> getUserList() {
		ArrayList<User> userList = new ArrayList<User>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT username, first_name, last_name "
				+ "FROM user;");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				String username = result.getString("username");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				User user = new User(username, "", "", firstName, lastName);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return userList;
	}

	/**
	 * Adds a user to the database
	 * @param user
	 */
	public static void addUser(User user) {
		DBConnector.makeStatement(""
				+ "INSERT INTO user (username, salt, password_hash, first_name, last_name) "
				+ "VALUES ('"+user.getUsername()+"', "
						 +"'"+user.getSalt()+"', "
						 +"'"+user.getHashResult()+"', "
						 +"'"+user.getFirstName()+"', "
						 +"'"+user.getLastName()+"');");
		
	}

}
