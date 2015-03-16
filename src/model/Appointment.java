package model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Appointment {
	private final int id;
	private Date startDate; //Starttid for event
	private Date endDate; //Sluttid for event
	private String description;
	private String location;
	private Room room;
	private boolean editable;
	private String status;
	private Date alarmDate;
	private String title, oldName;
	private ArrayList<Room> roomList = new ArrayList<Room>();
	private ArrayList<Room> availableRoom = new ArrayList<Room>();
	Scanner scanner = new Scanner(System.in);

	private User creator;
	private ArrayList<User> participants = new ArrayList<User>();
	private ArrayList<User> eventListeners = new ArrayList<User>();
		
	public Appointment(int id, Date startDate, Date endDate, String description, boolean editable, String status){
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.editable = editable;
		this.status = status;
		//this.roomList = main.Controller.getRoomlist();
//		this.roomList = TestMain.getRoomList(); DENNE
//		this.room = selectRoom(); DENNE
//		room.getCalendar().addEvent(this); DENNE
	}
			
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setRoom(Room room){ // M� endres i databasen
		this.room = room;
		fireCalendarEventHasChanged();
	}
	
	public String getStatus() {
		return status;
	}

	public String getEventName(){
		return title;
	}
	
	public String getOldName(){
		return oldName;
	}
	
	public void setEventName(String eventName){
		oldName = this.title;
		this.title = eventName;
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


	public ArrayList<User> getParticipants(){
		return participants;
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

	public User getCreator() {
		return creator;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public ArrayList<Room> getAvailableRoom() {
		return availableRoom;
	}

	@Override
	public String toString() {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat time = new SimpleDateFormat("HH:mm");
		DateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String summary = "<html>";

		summary += (date.format(startDate).equals(date.format(endDate))) ? time.format(startDate)+"-"+time.format(endDate) : full.format(startDate)+" - "+full.format(endDate);
		summary += " ("+status+")<br>";
		summary += (alarmDate != null) ? (date.format(startDate).equals(date.format(alarmDate))) ? "Alarm: "+time.format(alarmDate)+"<br>" : "Alarm:"+full.format(alarmDate)+"<br>" : "";

		summary += description+"<br>";
		summary += (location != null) ? "Location: "+location+"<br>" : "";
		if (room.getId() != 0) {
			summary += "Room: "+room+"<br>";
		}

		summary += "<br> </html>";
		return summary;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Appointment appointment = (Appointment) obj;

		boolean idIsEqual = this.getId() == appointment.getId();
		boolean startDateIsEqual = this.getStartDate().equals(appointment.getStartDate());
		boolean endDateIsEqual = this.getEndDate().equals(appointment.getEndDate());
		boolean descriptionIsEqual = this.getDescription().equals(appointment.getDescription());

		boolean locationIsEqual;
		if (this.getLocation() == null && appointment.getLocation() == null) {
			locationIsEqual = true;
		} else if (this.getLocation() != null && appointment.getLocation() != null) {
			locationIsEqual = this.getLocation().equals(appointment.getLocation());
		} else {
			locationIsEqual = false;
		}

		boolean roomIdIsEqual;
		if (this.getRoom() == null && appointment.getRoom() == null) {
			roomIdIsEqual = true;
		} else if (this.getRoom() != null && appointment.getRoom() != null) {
			roomIdIsEqual = this.getRoom().equals(appointment.getRoom());
		} else {
			roomIdIsEqual = false;
		}

		return (idIsEqual && startDateIsEqual && endDateIsEqual && descriptionIsEqual && locationIsEqual && roomIdIsEqual);
	}


	public ArrayList<Room> getAvailableRoom(Date start, Date end){
		Interval int1 = new Interval(start, end);
		for(Room room : roomList) {
				if(room.getCalendar().getEvents().size() > 0){
					for (Appointment event : room.getCalendar().getEvents()){
						if (! int1.overlap((new Interval(event.getStartDate(), event.getEndDate())))) {
							availableRoom.add(room);
						}
					}
				} else {
					availableRoom.add(room);
				}
//		} if(availableRoom == null) {
//			throw new IllegalStateException("No available rooms for this event!");
		}
		return availableRoom;
	}
	
	public Room selectRoom() {
		getAvailableRoom(startDate, endDate);
		if(availableRoom.size() > 0) {
			for (int i = 0; i < availableRoom.size(); i++) {
			System.out.println(i+1 + ". " + availableRoom.get(i).getName());
			}
		} else{
			throw new IllegalStateException("No available rooms for this event!");
		}
		int answer;
		System.out.println("Hvilket rom?");
		answer = scanner.nextInt();
		return room = availableRoom.get(answer-1);
	}
	
}
