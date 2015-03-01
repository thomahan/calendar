package model;

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
		
		User user = new User("Nils", "Nilsen", username, password);
		
		assertTrue(user.isPasswordCorrect(password));
		assertFalse(user.isPasswordCorrect(wrongPassword));
	}
}
