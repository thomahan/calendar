package model;

import java.util.ArrayList;
import java.util.Date;

public class CalendarEvent {

	private final int id;
	private String eventName, oldName;
	private Date startDate; //Starttid for event
	private Date endDate; //Sluttid for event
	private Date alarmDate;
	private String description;
	private String location;
	private User creator;
	private Room room;
	private ArrayList<User> participants = new ArrayList<User>();
	private ArrayList<User> eventListeners = new ArrayList<User>();
	

	public CalendarEvent(String eventName, Date startDate, User user, Date endDate, String location, int id, String description){
		this.id = id;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.location = location;
		this.creator = user;
		oldName = this.eventName;
		participants.add(user);
		user.getCalendar().addEvent(this);
		eventListeners.add(user);
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
	
	public Date getStartDate(){
		return startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public Date getAlarmDate() {
		return alarmDate;
	}
		
	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getCreator() {
		return creator;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
//	public String getAvailableRoomNameIfAvailable(){
//		Query query = db.DBConnector.makeQuery("SELECT enddate, startdate FROM calendarevent;");
//		ResultSet result = query.getResult();
//		
//		Date start = this.startDate;
//		Date end = this.endDate;
//		Interval interval = new Interval(start, end);
//		
//		
//		try{
//			while (result.next()) {
//				Interval interval1 = new Interval(result.getDate("startdate"), result.getDate("enddate"));
//				if (interval.overlap(interval, interval1)){
//					room = result.getString("room");
//				}
//			} 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		query.close();
//		
//		return room;
//		
//	}
	
//	public Room getAvailableRoom(){
//		String roomname = getAvailableRoomNameIfAvailable();
//		
//		for(Room room : roomlist)
//		
//	}
}


