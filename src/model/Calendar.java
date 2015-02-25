package model;

import java.util.ArrayList;



public class Calendar {

	private String calendarName;
	private User owner;
	private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();
	
	public Calendar(User owner){
		this.owner = owner;
	}
	
	public void addEvent(CalendarEvent event){ // Må også legges til i databasen
		if (isValidEvent(event))events.add(event);
		else throw new IllegalArgumentException("Unable to add event. Either you have an existing event at this time, or the event is already added. Please check your events ");

	}
	
	public void removeEvent(CalendarEvent event){
		events.remove(event); // Må fjernes fra databasen
	}
	
	public boolean isValidEvent(CalendarEvent event){
		if (events.contains(event)){ // pluss sjekke databasen
			return false;
		}  else {
			return true;
		}
		
		// Ufullstendig metode. Må korrigere for tid (sjekke om avtale eksisterer på det gitte tidspunkt fra før).
		// For å se om brukeren har en event i det tidsrommet. 
	}
	

	
	public void showMyEvents(){
		for (CalendarEvent event: events){
			System.out.println("Event name: " + event.getEventName() + ", Starting: " + event.getStartDate() + ", Ending: " + event.getEndDate() + ", Room: " + event.getRoom().getName());
			
		}
	}
	
	
	
	
	
}
