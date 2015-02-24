package model;

import java.util.ArrayList;
import java.util.Date;

public class CalendarEvent {

	private String eventName;
	private ArrayList<User> participants = new ArrayList<User>();
	private ArrayList<User> eventListeners = new ArrayList<User>();
	private Room room;
	private Date startDate; //Starttid for event
	private Date endDate; //Sluttid for event
	
	// Husk � g� gjennom klassen for � sjekke om verdier eller statements trengs � valideres.
	
	public CalendarEvent(String eventName, Date startDate, ArrayList<User> participants, Date endDate){
		this.eventName = eventName;
		this.startDate = startDate;
		this.participants = participants;
		this.endDate = endDate;
	}
	
	public String getEventName(){
		return eventName;
	}
	
	public void setEventName(String eventName){
		this.eventName = eventName;
	}
	
	public long getStartDate(){
		return startDate.getTime();
	}
	
	public long getEndDate(){
		return endDate.getTime();
	}
	
	public void setStartDate(Date date){
		this.startDate.setTime(date.getTime());
	}
	
	public void setEndDate(Date date){
		this.endDate.setTime(date.getTime());
	}
	
	public Room getRoom(){
		return room;
	}
	
	public void setRoom(Room room){
		this.room = room;
	}
	
	public void addParticipant(User user){
		if (participants.contains(user) == false){
			Invitation invitation = new Invitation(this);
			invitation.sendInvitationToUser(user);
			if (invitation.reply == true){
				System.out.println("Hei");
				participants.add(user);
				// + Legge inn i brukers kalender.
			}
		} else{
			throw new IllegalArgumentException("User is already added.");
		}
	}
	
	public void removeParticipant(User user){
		participants.remove(user);
	}
	
	public void addGroup(Group group){
		
		
		// Venter med denne til groups er ferdig. Her m� jeg sjekke at hvert gruppemedlem ikke allerede er med fra f�r.
	}
	
	public void removeGroup(Group group){
			participants.remove(group.getMembers()); 
	}
	
	public void fireCalendarEventHasChanged(){
		// M� kartlegge hva innholdet skal v�re her.
	}
	
}

