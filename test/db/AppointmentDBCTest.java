package db;

import java.util.Date;

import org.junit.Test;

public class AppointmentDBCTest {
	@SuppressWarnings("deprecation")
	
	@Test
	public void appointmentShouldBeAddedToDatabase() {
		Date startTime = new Date(2015-1900, 4, 8, 16, 15, 00);
		Date endTime = new Date(2015-1900, 4, 8, 18, 00, 00);
		Date alarmTime = new Date(2015-1900, 4, 8, 16, 00, 00);
		String title = "Sage ved";
		String description = "Da var det vel på tide å sage ved igjen?";
		String location = "Bua";
		String username = "user";
		int roomId = 1;
		
		AppointmentDBC.addAppointment(startTime, endTime, alarmTime, title, description, location, username, roomId);
	}
/*
	@Test
	public void addInvitationShouldInviteUserToAppointment() {
		AppointmentDBC.addInvitation(11, "user1", null);
	}
	*/
}
