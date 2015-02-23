package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHash {
	private static final int SALT_BYTE_SIZE = 32;
	private static final int DEFAULT_ITERATIONS = 1000;

	/**
	 * Creates a salt of random bytes to be hashed together with the password
	 * @return A string of a random bytes in hexadecimal
	 */
	public static String getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];

		random.nextBytes(salt);
		
		return getHexString(salt); 
	}
		
	/**
	 * Creates a hash from a salt and a password by hashing them iteratively
	 * @param salt
	 * @param password
	 * @return An iterated SHA-256 hash
	 */
	public static String getPasswordHash(String salt, String password) {
		String hash = "";
		for (int i = 0; i < DEFAULT_ITERATIONS; i++) {
			hash = getHash(hash.concat(salt).concat(password));
		}
		return hash;
	}

	/**
	 * Converts an array of bytes into a string of hexadecimals
	 * @param bytes
	 * @return A string of hexadecimals
	 */
	private static String getHexString(byte[] bytes) {
		BigInteger n = new BigInteger(1, bytes);
		return n.toString(16);
	}

	/**
	 * Creates a hash from a string
	 * @param s
	 * @return A SHA-256 hash
	 */
	private static String getHash(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.reset();

			md.update(s.getBytes());	
			byte[] digest = md.digest();
			String hash = getHexString(digest);
			
			return hash;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
