package main;

import main.PasswordHash;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PasswordHashTest {
	@Test
	public void getSaltShouldReturnDifferentSalts() {
		String saltA = PasswordHash.nextSalt();
		String saltB = PasswordHash.nextSalt();
		
		/*
		System.out.println(saltA);
		System.out.println(saltB);
		//*/
		
		assertFalse(saltA.equals(saltB));
	}
	
	@Test
	public void hashingShouldBeConsistent() {
		String salt = PasswordHash.nextSalt();
		String password = "abc123";
		String hashA = PasswordHash.hashPassword(salt, password);
		String hashB = PasswordHash.hashPassword(salt, password);

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
