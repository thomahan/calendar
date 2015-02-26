package db;

import db.UserDBC;
import main.PasswordHash;
import model.User;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDBCTest {
	@Test
	public void userShouldBeRetrievable() {
		String username = "user1";
		User user = UserDBC.getUser(username);
		
		assertEquals(username, user.getName());
		assertNotNull(user.getSalt());
		assertNotNull(user.getPasswordHash());
	}

	@Test
	public void userShouldBeAddedToDatabase() {
		/*
		String username = "user1";
		String password = "password";
		String salt = PasswordHash.nextSalt();
		String passwordHash = PasswordHash.hashPassword(salt, password);

		UserDBC.addUser(username, salt, passwordHash);
		UserDBC.getUser("user1");
		*/
	}
}
