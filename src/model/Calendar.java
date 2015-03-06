package model;

import java.util.ArrayList;
import java.util.Date;



public class Calendar {

	private String calendarName;
	private User owner;
	private ArrayList<Appointment> events = new ArrayList<Appointment>();
	
	public Calendar(User owner){
		this.owner = owner;
	}
	
	public void addEvent(Appointment event){ // M� ogs� legges til i databasen
		if (isValidEvent(event))events.add(event);
		else throw new IllegalArgumentException("Unable to add event. Either you have an existing event at this time, or the event is already added. Please check your events ");

	}
	
	public void removeEvent(Appointment event){
		events.remove(event); 
	}
	
	public boolean isValidEvent(Appointment event){
		if (events.contains(event)){ 
			return false;
		}  else if(eventsOverlap(event)){
			return false;
		} return true;
		
		
	}
	
	// Returns true if events overlap
	private boolean eventsOverlap(Appointment newEvent){
		Date start1 = newEvent.getStartDate();
		Date end1 = newEvent.getEndDate();
		Interval int1 = new Interval(start1, end1);
		
		for (Appointment event : events){
			if (int1.overlap(new Interval(event.getStartDate(), event.getEndDate()))){
				return true;
			}
		} return false;
	}

	
	public void showMyEvents(){
		for (Appointment event: events){
			System.out.println("Event name: " + event.getEventName() + ", Starting: " + event.getStartDate() + ", Ending: " + event.getEndDate() + ", Room: " + event.getRoom().getName());
		}
	}
	
	public void showEventsOnADay(Date date) {

		for (Appointment event : events) {
			if ((event.getStartDate().getYear() == date.getYear())
					&& (event.getStartDate().getMonth() == date.getMonth())
					&& (event.getStartDate().getDay() == date.getDay())) {
				System.out.println("Event name: " + event.getEventName()
						+ "\nStart: " + event.getStartDate().getHours() + ":"
						+ event.getStartDate().getMinutes() + "\nEnd: "
						+ event.getEndDate().getHours() + ":"
						+ event.getEndDate().getMinutes() + "\nRoom: "
						+ event.getRoom().getName() + "\nLocation: "
						+ event.getLocation() + "\nDescription: " + event.getDescription());
				for (User user : event.getParticipants()){
					System.out.print(user.getFirstName() + " " + user.getLastName() + ", ");
					
				} System.out.println("\n");
			} 
		}
	}
	
	
	
	
}
