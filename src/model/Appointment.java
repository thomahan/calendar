package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Appointment {
	private final int id;
	private Date startDate; // Starttid for appointment
	private Date endDate; // Sluttid for appointment
	private String description;
	private String location;
	private Room room;
	private boolean editable;
	private boolean showDateByDefault;

	private String status;
	private Date alarmDate;
	private String title, oldName;

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
		this.showDateByDefault = false;
	}
	
	public boolean isShowDateByDefault() {
		return showDateByDefault;
	}

	public void setShowDateByDefault(boolean showDateByDefault) {
		this.showDateByDefault = showDateByDefault;
	}
		
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setRoom(Room room) {
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

	@Override
	public String toString() {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat time = new SimpleDateFormat("HH:mm");
		DateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String summary = "<html>";

		summary += (date.format(startDate).equals(date.format(endDate)) && !showDateByDefault) ? time.format(startDate)+"-"+time.format(endDate) : full.format(startDate)+" - "+full.format(endDate);
		if (!isEditable()) {
			if (showDateByDefault) {
				summary += "<br>";
			}
			summary += " ("+status+")";
		}
		summary+="<br>";
		
		summary += (alarmDate != null) ? (date.format(startDate).equals(date.format(alarmDate))) ? "Alarm: "+time.format(alarmDate)+"<br>" : "Alarm:"+full.format(alarmDate)+"<br>" : "";

		summary += description+"<br>";
		summary += (location != null) ? "Location: "+location+"<br>" : "";
		if (room != null && room.getId() != 0) {
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
	
}
