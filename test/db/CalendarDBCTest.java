package db;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CalendarDBCTest {
	@SuppressWarnings("deprecation")
	
/*
	@Test
	public void appointmentShouldBeAddedToDatabase() {
		Date startTime = new Date(2015-1900, 4, 8, 16, 15, 00);
		Date endTime = new Date(2015-1900, 4, 8, 18, 00, 00);
		Date alarmTime = new Date(2015-1900, 4, 8, 16, 00, 00);
		String description = "Sage";
		String location = "Bua";
		String username = "user1";
		int roomId = 1;
		
		CalendarDBC.addEvent(startTime, endTime, alarmTime, description, location, username, roomId);
	}
*/
	
	@Test
	public void addInvitationShouldInviteUserToAppointment() {
		CalendarDBC.addInvitation(11, "user1", null);
	}
}
