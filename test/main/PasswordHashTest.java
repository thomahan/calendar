package main;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import main.PasswordHash;

public class PasswordHashTest {
	public void getSaltShouldReturnDifferentSalts() {
		String saltA = PasswordHash.getSalt();
		String saltB = PasswordHash.getSalt();
		
		assertFalse(saltA.equals(saltB));
	}

}
