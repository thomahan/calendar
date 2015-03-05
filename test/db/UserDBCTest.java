package db;

import db.UserDBC;
import model.User;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDBCTest {
	@Test
	public void userShouldBeRetrievable() {
		String username = "user1";
		
		User user = UserDBC.getUser(username);
		System.out.println(user.getName()+user.getUsername()+user.getSalt()+user.getHashResult());
		
		assertEquals(username, user.getUsername());
		assertNotNull(user.getSalt());
		assertNotNull(user.getHashResult());
	}

	@Test
	public void userShouldBeAddedToDatabase() {
		String username = "user3";
		String password = "password";

		User user = new User("Bob", "Robertson", username, password);

		UserDBC.addUser(user);

		User dbUser = UserDBC.getUser("user3");
		
		assertEquals(user.getUsername(), dbUser.getUsername());
	}
}
