package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
	private final int id;
	private Date startDate;
	private Date endDate;
	private Date alarmDate;
	private String description;
	private String location;
	private Room room;
	private boolean editable;
	private String status;
	private boolean showDateByDefault;
		
	public Appointment(int id, Date startDate, Date endDate, String description, boolean editable, String status){
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.editable = editable;
		this.status = status;
		this.showDateByDefault = false;
	}
	
	public int getId() {
		return id;
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

	public String getDescription() {
		return description;
	}
	
	public String getLocation() {
		return location;
	}
		
	public void setLocation(String location) {
		this.location = location;
	}

	public Room getRoom(){
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean isEditable() {
		return editable;
	}
		
	public String getStatus() {
		return status;
	}

	public boolean isShowDateByDefault() {
		return showDateByDefault;
	}

	public void setShowDateByDefault(boolean showDateByDefault) {
		this.showDateByDefault = showDateByDefault;
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

}
