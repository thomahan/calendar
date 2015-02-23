package main;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import main.PasswordHash;

public class PasswordHashTest {
	@Test
	public void getSaltShouldReturnDifferentSalts() {
		String saltA = PasswordHash.getSalt();
		String saltB = PasswordHash.getSalt();
		
		/*
		System.out.println(saltA);
		System.out.println(saltB);
		//*/
		
		assertFalse(saltA.equals(saltB));
	}
	
	@Test
	public void hashingShouldBeConsistent() {
		String salt = PasswordHash.getSalt();
		String password = "abc123";
		String hashA = PasswordHash.getPasswordHash(salt, password);
		String hashB = PasswordHash.getPasswordHash(salt, password);

		/*
		System.out.println(salt);
		System.out.println(password);
		System.out.println(hashA);
		System.out.println(hashB);
		System.out.println(hashB.length());
		//*/
		
		assertTrue(hashA.equals(hashB));
	}
}
