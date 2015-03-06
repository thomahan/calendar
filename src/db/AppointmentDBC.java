package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import model.Appointment;

public class AppointmentDBC {
/*	
	public static  getAppointment(int appointmentId, String username) {
		CalendarEvent calendarEvent = null;

		Query query = DBConnector.makeQuery(""
				+ "SELECT appointment_id, start_time, end_time, alarm_time, description, location, creator, room_id, name, seat_count, status, is_visible "
				+ "FROM appointment "
				+ "JOIN appointment_invitation ON appointment.id = appointment_invitation.appointment_id "
				+ "JOIN room ON appointment.roomid = room.id;");
		ResultSet result = query.getResult();

		try {
			if (result.next()) {
				Timestamp startTime = result.getTimestamp("start_time");
				Timestamp endTime = result.getTimestamp("end_time");
				Timestamp alarmTime = result.getTimestamp("alarm_time");
				String description = result.getString("description");
				String location = result.getString("location");
				String creator = result.getString("creator");

				String status = result.getString("status");
				boolean isVisible = result.getBoolean("is_visible");

				int roomId = result.getInt("room_id");
				String roomName = result.getString("name");
				int seatCount = result.getInt("seat_count");
				

				boolean canEdit = (username.equals(creator));

				calendarEvent = new CalendarEvent(appointmentId, startTime, endTime, description, location, canEdit, status, isVisible);

				calendarEventList.add(calendarEvent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return calendarEvent;
	}
	
*/	

	@SuppressWarnings("deprecation")
	public static ArrayList<Appointment> getAppointmentList(String username, Date selectedDate) {
		ArrayList<Appointment> appointmentList =  new ArrayList<Appointment>();
		
		Timestamp lowerTimeStamp = new Timestamp(selectedDate.getTime());
		Timestamp upperTimeStamp = new Timestamp(selectedDate.getTime());
		upperTimeStamp.setHours(23);
		upperTimeStamp.setMinutes(59);

		Query query = DBConnector.makeQuery(""
				+ "SELECT appointment_id, start_time, end_time, alarm_time, title, creator, status, is_visible "
				+ "FROM appointment_invitation "
				+ "JOIN appointment_invitation ON appointment.id = appointment_invitation.appointment_id "
				+ "WHERE username = '"+username+"' "
						+ "AND start_time >= '"+lowerTimeStamp+"' AND start_time <='"+upperTimeStamp+"';");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				int appointmentId = result.getInt("appointment_id");
				Timestamp startTime = result.getTimestamp("start_time");
				Timestamp endTime = result.getTimestamp("end_time");
				Timestamp alarmTime = result.getTimestamp("alarm_time");
				String title = result.getString("title");
				String creator = result.getString("creator");

				String status = result.getString("status");
				boolean isVisible = result.getBoolean("is_visible");
				
				Date startTimeDate = new Date(startTime.getTime());
				Date endTimeDate = new Date(endTime.getTime());
				Date alarmTimeDate = new Date(alarmTime.getTime());
				boolean canEdit = (username.equals(creator));

				Appointment appointment = new Appointment(appointmentId, startTimeDate, endTimeDate, alarmTimeDate, title, canEdit, status, isVisible);

				appointmentList.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return appointmentList;
	}

	/**
	 * Adds an appointment to the database
	 * @param startTime
	 * @param endTime
	 * @param alarmTime
	 * @param description
	 * @param location
	 * @param username
	 * @param roomId
	 */
	public static int addAppointment(Date startTimeDate, Date endTimeDate, Date alarmTimeDate, String title, String description, String location, String username, int roomId){
		Timestamp startTime = new Timestamp(startTimeDate.getTime());
		Timestamp endTime = new Timestamp(endTimeDate.getTime());
	
		DBConnector.makeStatement(""
			+ "INSERT INTO appointment (start_time, end_time, title, creator) "
			+ "VALUES ('"+startTime+"', "
					 +"'"+endTime+"', "
					 +"'"+title+"', "
					 +"'"+username+"');");

		// The following way of referring back to the appointment is unreliable.
		// Code should be refactored with prepared statements to get the auto incremented value.
		Query query = null;
		int appointmentId = 0;
		try {
			query = DBConnector.makeQuery("SELECT id FROM appointment WHERE id = (SELECT MAX(id) FROM appointment WHERE creator ='"+username+"');");
			ResultSet res = query.getResult();
			if (res.next()) {
				appointmentId = res.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		if (alarmTimeDate != null) {
			Timestamp alarmTime = new Timestamp(alarmTimeDate.getTime());
			DBConnector.makeStatement(""
					+ "UPDATE appointment SET alarm_time = '"+alarmTime+"' "
							+ "WHERE id = '"+appointmentId+"';");
		}
		if (description != null) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment SET description = '"+description+"' "
							+ "WHERE id = '"+appointmentId+"';");
		}
		if (location != null) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment SET location = '"+location+"' "
							+ "WHERE id = '"+appointmentId+"';");
		}
		if (roomId != 0) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment SET room_id = '"+roomId+"' "
							+ "WHERE id = '"+appointmentId+"';");
		}
		
		return appointmentId;
	}
	
	/**
	 * Adds an invitation to the database between a user and an appointment
	 * @param appointmentId
	 * @param username
	 * @param status
	 */
	public static void addInvitation(int appointmentId, String username, String status) {
		if (status != null) {
			DBConnector.makeStatement(""
					+ "INSERT INTO appointment_invitation (appointment_id, username, status) "
					+ "VALUES ('"+appointmentId+"', "
					 		+"'"+username+"', "
					 		+"'"+status+"');");
		} else {
			DBConnector.makeStatement(""
					+ "INSERT INTO appointment_invitation (appointment_id, username) "
					+ "VALUES ('"+appointmentId+"', "
					 		+"'"+username+"');");
		}
	}

}
