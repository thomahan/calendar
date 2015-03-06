package model;

import java.util.Date;

public class AppointmentMockUp {
	private int id;
	private Date startDate;
	private Date endDate;
	private Date alarmDate;
	private String title;
	private String description;
	private String location;
	private int roomId;
	private boolean canEdit;
	private String status;
	private boolean isVisible;

	public AppointmentMockUp(int id, Date startDate, Date endDate, String title, boolean isVisible) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		this.isVisible = isVisible;
	}
	
	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
		
	public void setLocation(String location) {
		this.location = location;
	}
		
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
		
	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
