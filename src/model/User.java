package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
	
	private String name;
	private final int employeeID;
	private Calendar calendar;
	private static final AtomicInteger count = new AtomicInteger(0);
	private ArrayList<Group> groups = new ArrayList<Group>();
	
	public User(String name) {
		this.name = name;
		this.employeeID = count.incrementAndGet();
	}
	
	private void changeUser(String newName) {
		name = newName;
	}
	
	private void createGroup(String name) {
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
	
	
	//Legger til en ny gruppe til gruppelisten
	public void addToGroups(Group group) {
		groups.add(group);
	}
	
	
}
