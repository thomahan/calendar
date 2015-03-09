package model;

import main.PasswordHash;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class User implements CalendarEventListener, GroupListener {
	
	private String firstName;
	private String lastName;
	private String username;
	private String salt;
	private String passwordHash;

	private Calendar calendar;
	
	private ArrayList<Group> groups = new ArrayList<Group>();
	Scanner scanner = new Scanner(System.in);
	
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
		this.calendar = new Calendar(this);
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
		this.calendar = new Calendar(this);
	}
	
	public boolean isPasswordCorrect(String password) {
		String passwordHashCandidate = PasswordHash.hashPassword(this.salt, password);
		return this.passwordHash.equals(passwordHashCandidate);
	}

	private void changeUser(String newFirstName, String newLastName) { //Legg til endring i db
		this.firstName = newFirstName;
		this.lastName = newLastName;
	}
	
	private void createGroup(String name) { //Legg til gruppe i db
		Group group = new Group(name);
		group.addUserToGroup(this);
		groups.add(group);
	}

	
	
	public String getFirstName() {
		return firstName;
	}
		
	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}
	
	public String getUsername(){
		return username;
	}

	public String getSalt(){
		return this.salt;
	}
	
	public String getHashResult(){
		return passwordHash;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public boolean answerInvitation(){
		String answer;
		answer = scanner.next();
		
		while (!(answer.equals("YES") || answer.equals("NO"))){
			System.out.println("Not a valid answer. Please type YES or NO");
			answer = scanner.next();
		}
		if(answer.equals("YES")){
			return true;
		} else 
			return false;
	} 

	//Legger til en ny gruppe til gruppelisten
	public void addToGroups(Group group) { //Legg til oppdatert gruppeliste i db
		groups.add(group);
	}

	@Override
	public void eventHasChanged(Appointment event) {
		System.out.println(event.getOldName() + " has been changed: " + "name: " + event.getEventName() + ", start date: " + event.getStartDate() + ", end date: " + event.getEndDate() + ", room: " + event.getRoom().getName());
		
	}

	@Override
	public void groupHasChanged(Group group) {
		System.out.println(group.getGroupName() + " has been changed.");
		
	}
	
	
	@Override
	public String toString() {
		return firstName+" "+lastName;
	}
}
