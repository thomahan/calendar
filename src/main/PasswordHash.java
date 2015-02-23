package main;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordHash {
	private static final int SALT_BYTE_SIZE = 256;

	/**
	 * Creates a salt of random bytes to be hashed together with the password
	 * @return byte[]
	 */
	public static byte[] getSalt() {
		byte[] randomBytes = new byte[SALT_BYTE_SIZE];
		SecureRandom secrand = new SecureRandom();
		secrand.nextBytes(randomBytes);
		return randomBytes;
	}

}
