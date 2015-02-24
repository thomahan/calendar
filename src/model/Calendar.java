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
		else throw new IllegalArgumentException("Unable to add event. Either you have an existing event at this time, or the event is already added. Please check your events ");

	}
	
	public void removeEvent(CalendarEvent event){
		events.remove(event);
	}
	
	public boolean isValidEvent(CalendarEvent event){
		if (events.contains(event)){
			return false;
		} else{
			return true;
		}
		
		// Ufullstendig metode. Må korrigere for tid (sjekke om avtale eksisterer på det gitte tidspunkt fra før)
	}
	
	public void showMyEvents(){
		for (CalendarEvent event: events){
			System.out.println("Event name: " + event.eventName + ", Date: " + event.date + ", Room: " + event.room);
		}
	}
	
	
	
	
	
}
