package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Group;
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
	public static List<User> getUserList() {
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

	public static List<Group> getGroupList() {
		List<Group> groupList = new ArrayList<Group>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT user_group_id, name "
				+ "FROM user_group;");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				int groupId = result.getInt("user_group_id");
				String name = result.getString("name");
				Group group = new Group(groupId, name);
				groupList.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return groupList;
	}
	
	public static List<Group> getSubGroupList(int groupId) {
		List<Group> subGroupList = new ArrayList<Group>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT sub_group_id "
				+ "FROM group_in_group "
				+ "WHERE user_group_id = '"+groupId+"';");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				int subGroupId = result.getInt("sub_group_id");
				Group group = new Group(subGroupId, "");
				subGroupList.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return subGroupList;
	}

	public static List<User> getMemberList(int groupId) {
		ArrayList<User> userList = new ArrayList<User>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT username "
				+ "FROM user_in_group "
				+ "WHERE user_group_id = '"+groupId+"';");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				String username = result.getString("username");
				User user = new User(username, "", "", "", "");
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return userList;
	}

}
