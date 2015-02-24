package model;

import java.util.ArrayList;



public class Calendar {

	private String calendarName;
	private User owner;
	private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();
	
	public Calendar(User owner){
		this.owner = owner;
	}
	
	public void addEvent(CalendarEvent event){
		if (isValidEvent(event))events.add(event);

	}
	
	public void removeEvent(CalendarEvent event){
		events.remove(event);
	}
	
	public boolean isValidEvent(CalendarEvent event){
		
	}
	
	
	
	
	
	
}
