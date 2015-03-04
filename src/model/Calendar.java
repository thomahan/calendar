package model;

import java.util.ArrayList;
import java.util.Date;



public class Calendar {

	private String calendarName;
	private User owner;
	private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();
	
	public Calendar(User owner){
		this.owner = owner;
	}
	
	public void addEvent(CalendarEvent event){ // M� ogs� legges til i databasen
		if (isValidEvent(event))events.add(event);
		else throw new IllegalArgumentException("Unable to add event. Either you have an existing event at this time, or the event is already added. Please check your events ");

	}
	
	public void removeEvent(CalendarEvent event){
		events.remove(event); 
	}
	
	public boolean isValidEvent(CalendarEvent event){
		if (events.contains(event)){ 
			return false;
		}  else if(eventsOverlap(event)){
			return false;
		} return true;
		
		
	}
	
	// Returns true if events overlap
	private boolean eventsOverlap(CalendarEvent newEvent){
		Date start1 = newEvent.getStartDate();
		Date end1 = newEvent.getEndDate();
		Interval int1 = new Interval(start1, end1);
		
		for (CalendarEvent event : events){
			if (int1.overlap(int1, new Interval(event.getStartDate(), event.getEndDate()))){
				return true;
			}
		} return false;
	}

	
	public void showMyEvents(){
		for (CalendarEvent event: events){
			System.out.println("Event name: " + event.getEventName() + ", Starting: " + event.getStartDate() + ", Ending: " + event.getEndDate() + ", Room: " + event.getRoom().getName());
			
		}
	}
	
	
	
	
	
}
