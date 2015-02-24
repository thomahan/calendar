package model;

public class User {
	
	private String name;
	private final int employeeID;
	private Calendar calendar;
	
	private User(String name, int employeeID) {
		this.name = name;
		this.employeeID = employeeID;
	}
	
	private void changeUser(String newName) {
		name = newName;
	}
	
	private void createGroup(Group group) {
		
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
	
	
}
