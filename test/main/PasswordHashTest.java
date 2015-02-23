package main;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import main.PasswordHash;

public class PasswordHashTest {
	public void getSaltShouldReturnDifferentSalts() {
		byte[] saltA = PasswordHash.getSalt();
		byte[] saltB = PasswordHash.getSalt();
		System.out.println(saltA);
		
		assertFalse(saltA.equals(saltB));
	}

}
