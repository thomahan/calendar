package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import model.Appointment;
import model.Room;

public class AppointmentDBC {
	/**
	 * Returns an appointment from the database
	 * @param appointmentId
	 * @param username
	 * @return Appointment
	 */
	public static Appointment getAppointment(int appointmentId, String username) {
		Appointment appointment =  null;

		Query query = DBConnector.makeQuery(""
				+ "SELECT appointment_id, start_time, end_time, alarm_time, title, description, location, creator, status, is_visible, room_id, name, seat_count "
				+ "FROM appointment "
				+ "JOIN appointment_invitation ON appointment.id = appointment_invitation.appointment_id "
				+ "JOIN room ON appointment.room_id = room.id "
				+ "WHERE appointment_id = '"+appointmentId+"';");
		ResultSet result = query.getResult();

		try {
			if (result.next()) {
				Timestamp startTime = result.getTimestamp("start_time");
				Timestamp endTime = result.getTimestamp("end_time");
				Timestamp alarmTime = result.getTimestamp("alarm_time");
				String title = result.getString("title");
				String description = result.getString("description");
				String location = result.getString("location");
				String creator = result.getString("creator");

				String status = result.getString("status");
				boolean isVisible = result.getBoolean("is_visible");

				int roomId = result.getInt("room_id");
				String roomName = result.getString("name");
				int seatCount = result.getInt("seat_count");

				Date startDate = new Date(startTime.getTime());
				Date endDate = new Date(endTime.getTime());
				Date alarmDate = null;
				if (alarmTime != null) {
					alarmDate = new Date(alarmTime.getTime());
				}

				boolean canEdit = (username.equals(creator));

				appointment = new Appointment(appointmentId, startDate, endDate, alarmDate, title, canEdit, status, isVisible);
				appointment.setDescription(description);
				appointment.setLocation(location);
				
				if (roomId != 0) {
					Room room = new Room(roomId, roomName, seatCount);
					appointment.setRoom(room);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return appointment;
	}

	/**
	 * Returns alist of appointments that starts on a specified date
	 * @param username
	 * @param selectedDate
	 * @return List of appointments for specified date
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<Appointment> getAppointmentList(String username, Date selectedDate) {
		ArrayList<Appointment> appointmentList =  new ArrayList<Appointment>();
		
		Timestamp lowerTime = new Timestamp(selectedDate.getTime());
		Timestamp upperTime = new Timestamp(selectedDate.getTime());
		upperTime.setHours(23);
		upperTime.setMinutes(59);

		Query query = DBConnector.makeQuery(""
				+ "SELECT id, start_time, end_time, alarm_time, title, creator, status, is_visible "
				+ "FROM appointment JOIN appointment_invitation ON appointment.id = appointment_invitation.appointment_id "
				+ "WHERE username = '"+username+"' AND start_time >= '"+lowerTime+"' AND start_time <='"+upperTime+"';");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				int appointmentId = result.getInt("id");
				Timestamp startTime = result.getTimestamp("start_time");
				Timestamp endTime = result.getTimestamp("end_time");
				Timestamp alarmTime = result.getTimestamp("alarm_time");
				String title = result.getString("title");
				String creator = result.getString("creator");

				String status = result.getString("status");
				boolean isVisible = result.getBoolean("is_visible");

				Date startDate = new Date(startTime.getTime());
				Date endDate = new Date(endTime.getTime());
				Date alarmDate = null;
				if (alarmTime != null) {
					alarmDate = new Date(alarmTime.getTime());
				}

				boolean canEdit = (username.equals(creator));

				Appointment appointment = new Appointment(appointmentId, startDate, endDate, alarmDate, title, canEdit, status, isVisible);
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
	public static int addAppointment(Date startDate, Date endDate, Date alarmDate, String title, String description, String location, String username, int roomId){
		Timestamp startTime = new Timestamp(startDate.getTime());
		Timestamp endTime = new Timestamp(endDate.getTime());
	
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

		if (alarmDate != null) {
			Timestamp alarmTime = new Timestamp(alarmDate.getTime());
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
	 * Deletes an appointment from the database
	 * @param appointmentId
	 */
	public static void removeAppointment(int appointmentId) {
		DBConnector.makeStatement(""
				+ "DELETE appointment "
				+ "WHERE id = '"+appointmentId+"';");
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
	
	/**
	 * Removes an invitation from the database
	 * @param appointmentId
	 * @param username
	 */
	public static void removeInvitation(int appointmentId, String username) {
		DBConnector.makeStatement(""
				+ "DELETE appointment_invitation "
				+ "WHERE appointment_id = '"+appointmentId+"' AND username = '"+username+"';");
	}

}
