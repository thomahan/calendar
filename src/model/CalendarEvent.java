package model;

import java.util.ArrayList;
import java.util.Date;

public class CalendarEvent {

	private String eventName, oldName;
	private ArrayList<User> participants = new ArrayList<User>();
	private ArrayList<User> eventListeners = new ArrayList<User>();
	private Room room;
	private Date startDate; //Starttid for event
	private Date endDate; //Sluttid for event
	
	
	public CalendarEvent(String eventName, Date startDate, ArrayList<User> participants, Date endDate){
		this.eventName = eventName;
		this.startDate = startDate;
		this.participants = participants;
		this.endDate = endDate;
		oldName = this.eventName;
		for (int i = 0; i < participants.size(); i++) {
			participants.get(i).getCalendar().addEvent(this);
			eventListeners.add(participants.get(i));
		}
		//MÅ HUSKE Å SETTE ROM AUTOMATISK
	}
	
	public String getEventName(){
		return eventName;
	}
	
	public String getOldName(){
		return oldName;
	}
	
	public void setEventName(String eventName){
		oldName = this.eventName;
		this.eventName = eventName;
		fireCalendarEventHasChanged();
	}
	
	public long getStartDate(){
		return startDate.getTime();
	}
	
	public long getEndDate(){
		return endDate.getTime();
	}
	
	public void setStartDate(Date date){
		this.startDate.setTime(date.getTime()); //M� endres i databasen
		fireCalendarEventHasChanged();
	}
	
	public void setEndDate(Date date){ // M� endres i databasen
		this.endDate.setTime(date.getTime());
		fireCalendarEventHasChanged();
	}
	
	public Room getRoom(){
		return room;
	}
	
	public void setRoom(Room room){ // M� endres i databasen
		this.room = room;
		fireCalendarEventHasChanged();
	}
	
	public void addParticipant(User user){ //M� ogs� legges til i database
		if (participants.contains(user) == false){
			Invitation invitation = new Invitation(this);
			invitation.sendInvitationToUser(user);
			if (invitation.reply == true){
				System.out.println("Hei");
				participants.add(user);
				user.getCalendar().addEvent(this);
				eventListeners.add(user);
			}
		} else{
			throw new IllegalArgumentException("User is already added.");
		}
	}
	
	public void removeParticipant(User user){ // Fjernes i database
		user.getCalendar().removeEvent(this);
		participants.remove(user);
		user.getCalendar().removeEvent(this);
	}
	
	public void addGroup(Group group){ // Leggese til i database
		int n = group.getMembers().size();
		for (int i = 0; i < n; i++) {
			addParticipant(group.getMembers().get(i));
		}
		// Venter med denne til groups er ferdig. Her m� jeg sjekke at hvert gruppemedlem ikke allerede er med fra f�r.
	}
	
	public void removeGroup(Group group){ // Fjernes i database
		int n = group.getMembers().size();
		for (int i = 0; i < n; i++) {
			removeParticipant(group.getMembers().get(i));
		}
	}
	
	public void fireCalendarEventHasChanged(){
		for(User listener : eventListeners){
			listener.eventHasChanged(this);
		}
	}
	
	public void addListener(User user){
		eventListeners.add(user);
	}
	
	public void removeListener(User user){
		eventListeners.remove(user);
	}
}


