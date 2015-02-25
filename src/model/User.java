package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
	
	private String name;
	private final int employeeID;
	private Calendar calendar;
	private static final AtomicInteger count = new AtomicInteger(0);
	private ArrayList<Group> groups = new ArrayList<Group>();
	Scanner scanner = new Scanner(System.in);
	
	public User(String name) { //Legg til person i database
		this.name = name;
		this.employeeID = count.incrementAndGet();
	}
	
	private void changeUser(String newName) { //Legg til endring i db
		name = newName;
	}
	
	private void createGroup(String name) { //Legg til gruppe i db
		Group group = new Group(name);
		group.addUserToGroup(this);
		groups.add(group);
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public String getName() {
		return name;
	}

	public int getEmployeeID() {
		return employeeID;
	}
	
	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public boolean answerInvitation(Invitation invitation){
		String answer;
		answer = scanner.next();
		if(answer.equals("YES")){
			return true;
		} else 
			return false;
	} //HUSK � LAGE EN SISTE STATEMENT, hva vil den ikke svarer no eller yes?
	
	
	//Legger til en ny gruppe til gruppelisten
	public void addToGroups(Group group) { //Legg til oppdatert gruppeliste i db
		groups.add(group);
	}
	
	
}
