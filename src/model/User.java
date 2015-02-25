package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class User implements CalendarEventListener, GroupListener {
	
	private String name;
	private final int employeeID;
	private Calendar calendar;
	private static final AtomicInteger count = new AtomicInteger(0);
	private ArrayList<Group> groups = new ArrayList<Group>();
	Scanner scanner = new Scanner(System.in);
	
	public User(String name) {
		this.name = name;
		this.employeeID = count.incrementAndGet();
		calendar = new Calendar(this);
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
	public void addToGroups(Group group) {
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
