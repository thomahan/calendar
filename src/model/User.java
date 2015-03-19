package model;

import main.PasswordHash;

public class User {
	private String username;
	private String salt;
	private String passwordHash;
	private String firstName;
	private String lastName;
	
	/**
	 * Returns a User object. To be used when creating a new user from UI.
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	public User(String firstName, String lastName, String username, String password) { //Legg til person i database, password hashes
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.salt = PasswordHash.nextSalt();
		this.passwordHash = PasswordHash.hashPassword(this.salt, password);
	}

	/**
	 * Return a User object. To be used when retrieving a user from the database.
	 * @param username
	 * @param salt
	 * @param passwordHash
	 * @param firstName
	 * @param lastName
	 */
	public User(String username, String salt, String passwordHash, String firstName, String lastName) {
		this.username = username;
		this.salt = salt;
		this.passwordHash = passwordHash;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUsername(){
		return username;
	}

	public String getSalt(){
		return this.salt;
	}
	
	public String getPasswordHash(){
		return passwordHash;
	}
	
	public String getFirstName() {
		return firstName;
	}
		
	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
		
	public boolean isPasswordCorrect(String password) {
		String passwordHashCandidate = PasswordHash.hashPassword(this.salt, password);
		return this.passwordHash.equals(passwordHashCandidate);
	}	

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		User user = (User) obj;
		return this.getUsername().equals(user.getUsername());
	}
	
	@Override
	public String toString() {
		return firstName+" "+lastName;
	}

}
