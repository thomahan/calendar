package model;

import main.PasswordHash;
import model.User;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserTest {
	@Test
	public void passwordCheckShouldOnlyValidateCorrectPassword() {
		String username = "nils1";
		String password = "nilsrules";
		String wrongPassword = "nilsrulez";
		String salt = PasswordHash.nextSalt();
		String passwordHash = PasswordHash.hashPassword(salt, password);
		
		User user = new User(username, salt, passwordHash, "Nils", "Nilsen");
		
		assertTrue(user.isPasswordCorrect(password));
		assertFalse(user.isPasswordCorrect(wrongPassword));
	}
}
