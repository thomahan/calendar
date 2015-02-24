package model;

import java.util.ArrayList;
import java.util.Date;

public class CalendarEvent {

	private String eventName;
	private ArrayList<User> participants = new ArrayList<User>();
	private ArrayList<User> eventListeners = new ArrayList<User>();
	private Room room;
	private Date date;
	private int duration;
	
	// Husk � g� gjennom klassen for � sjekke om verdier eller statements trengs � valideres.
	
	public CalendarEvent(String eventName, Date date, ArrayList<User> participants){
		this.eventName = eventName;
		this.date = date;
		this.participants = participants;
	}
	
	public String getEventName(){
		return eventName;
	}
	
	public void setEventName(String eventName){
		this.eventName = eventName;
	}
	
	public long getDate(){
		return date.getTime();
	}
	
	public void setDate(Date date){
		this.date.setTime(date.getTime());
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
			if (invitation == true){
				participants.add(user);
			}
		} else{
			throw new IllegalArgumentException("User is already added.");
		}
	}
	
	public void removeParticipant(User user){
		participants.remove(user);
	}
	
	public void addGroup(Group group){
		// Venter med denne til groups er ferdig. Her m� jeg sjekke at hvert gruppemedlem ikke allerede er med far f�r.
	}
	
	public void removeGroup(Group group){
			participants.remove(group.members); // Tror det g�r an � gj�re det slik, istedenfor � g� gjennom en l�kke.
	}
	
	public void fireCalendarEventHasChanged(){
		// M� kartlegge hva innholdet skal v�re her.
	}
	
}

