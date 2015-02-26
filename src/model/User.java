package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class User implements CalendarEventListener, GroupListener {
	
	private String givenName;
	private String lastName;
	private String username;
	private String hashResult;
	private String salt;
	private final int employeeID;
	private Calendar calendar;
	private static final AtomicInteger count = new AtomicInteger(0);
	private ArrayList<Group> groups = new ArrayList<Group>();
	Scanner scanner = new Scanner(System.in);
	
	public User(String givenName, String lastName, String username, String password) { //Legg til person i database, password hashes
		this.givenName = givenName;
		this.lastName = lastName;
		
		if (db.UserDBC.isUserNameUnique == true){
		this.username = username; // Her må det sjekkes opp mot database at det ikke eksisterer en bruker med samme navn. Kaste Illegal argument exception
		} else{
			throw new IllegalArgumentException("Username not valid.");
		}
		String salt1 = main.PasswordHash.nextSalt();
		this.salt = salt1;
		hashResult = main.PasswordHash.hashPassword(salt1, password);
		this.employeeID = count.incrementAndGet();
		calendar = new Calendar(this);
	}
	
	private void changeUser(String newGivenName, String newLastName) { //Legg til endring i db
		this.givenName = newGivenName;
		this.lastName = newLastName;
	}
	
	private void createGroup(String name) { //Legg til gruppe i db
		Group group = new Group(name);
		group.addUserToGroup(this);
		groups.add(group);
	}
	
	public String getSalt(){
		return this.salt;
	}
	
	public String getHashResult(){
		return hashResult;
	}
	
	public String getUsername(){
		return username;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public String getName() {
		return givenName + lastName;
	}

	public int getEmployeeID() {
		return employeeID;
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
	public void eventHasChanged(CalendarEvent event) {
		System.out.println(event.getEventName() + " " + event.getStartDate() + " has been changed.");
		
	}

	@Override
	public void groupHasChanged(Group group) {
		System.out.println(group.getGroupName() + " has been changed.");
		
	}
	
	
}
