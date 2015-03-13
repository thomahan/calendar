package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
				+ "SELECT appointment_id, start_time, end_time, description, location, room_id, creator, status, alarm_time "
				+ "FROM appointment JOIN invitation ON appointment.appointment_id = invitation.appointment_id "
				+ "WHERE appointment_id = '"+appointmentId+"' AND username = '"+username+"';");
		ResultSet result = query.getResult();
		try {
			if (result.next()) {
				Timestamp startTime = result.getTimestamp("start_time");
				Timestamp endTime = result.getTimestamp("end_time");
				Timestamp alarmTime = result.getTimestamp("alarm_time");
				String description = result.getString("description");
				String location = result.getString("location");
				int roomId = result.getInt("room_id");
				String creator = result.getString("creator");
				String status = result.getString("status");

				Date startDate = new Date(startTime.getTime());
				Date endDate = new Date(endTime.getTime());
				Date alarmDate = null;
				if (alarmTime != null) {
					alarmDate = new Date(alarmTime.getTime());
				}

				boolean canEdit = (username.equals(creator));

				appointment = new Appointment(appointmentId, startDate, endDate, description, canEdit, status);
				appointment.setLocation(location);

				if (roomId != 0) {
					Room room = getRoom(roomId);
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
				+ "SELECT appointment.appointment_id, start_time, end_time, description, location, room_id, creator, status, alarm_time "
				+ "FROM appointment JOIN invitation ON appointment.appointment_id = invitation.appointment_id "
				+ "WHERE username = '"+username+"' AND start_time >= '"+lowerTime+"' AND start_time <= '"+upperTime+"' "
				+ "ORDER BY start_time ASC;");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				int appointmentId = result.getInt("appointment_id");
				Timestamp startTime = result.getTimestamp("start_time");
				Timestamp endTime = result.getTimestamp("end_time");
				Timestamp alarmTime = result.getTimestamp("alarm_time");
				String description = result.getString("description");
				String location = result.getString("location");
				int roomId = result.getInt("room_id");
				String creator = result.getString("creator");
				String status = result.getString("status");
				
				Date startDate = new Date(startTime.getTime());
				Date endDate = new Date(endTime.getTime());
				Date alarmDate = null;
				if (alarmTime != null) {
					alarmDate = new Date(alarmTime.getTime());
				}

				boolean editable = (username.equals(creator));

				Appointment appointment = new Appointment(appointmentId, startDate, endDate, description, editable, status);
				appointment.setAlarmDate(alarmDate);
				appointment.setLocation(location);
				
				if (roomId != 0) {
					Room room = getRoom(roomId);
					appointment.setRoom(room);
				}

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
	public static int addAppointment(Date startDate, Date endDate, String description, String location, String username, int roomId){
		Timestamp startTime = new Timestamp(startDate.getTime());
		Timestamp endTime = new Timestamp(endDate.getTime());
	
		DBConnector.makeStatement(""
			+ "INSERT INTO appointment (start_time, end_time, description, creator) "
			+ "VALUES ('"+startTime+"', "
					 +"'"+endTime+"', "
					 +"'"+description+"', "
					 +"'"+username+"');");

		// The following way of referring back to the appointment is unreliable.
		// Code should be refactored with prepared statements to get the auto incremented value.
		Query query = null;
		int appointmentId = 0;
		try {
			query = DBConnector.makeQuery("SELECT appointment_id FROM appointment WHERE appointment_id = (SELECT MAX(appointment_id) FROM appointment WHERE creator ='"+username+"');");
			ResultSet res = query.getResult();
			if (res.next()) {
				appointmentId = res.getInt("appointment_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		if (location != null) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment SET location = '"+location+"' "
							+ "WHERE appointment_id = '"+appointmentId+"';");
		}
		if (roomId != 0) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment SET room_id = '"+roomId+"' "
							+ "WHERE appointment_id = '"+appointmentId+"';");
		}
		
		return appointmentId;
	}
	
	/**
	 * Deletes an appointment from the database
	 * @param appointmentId
	 */
	public static void removeAppointment(int appointmentId) {
		DBConnector.makeStatement(""
				+ "DELETE FROM appointment "
				+ "WHERE appointment_id = '"+appointmentId+"';");
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
					+ "INSERT INTO invitation (appointment_id, username, status) "
					+ "VALUES ('"+appointmentId+"', "
					 		+"'"+username+"', "
					 		+"'"+status+"');");
		} else {
			DBConnector.makeStatement(""
					+ "INSERT INTO invitation (appointment_id, username) "
					+ "VALUES ('"+appointmentId+"', "
					 		+"'"+username+"');");
		}
	}
	
	/**
	 * Sets the status of an invitation
	 * @param appointmentId
	 * @param username
	 */
	public static void setInvitationStatus(int appointmentId, String username, String status) {
		DBConnector.makeStatement(""
			+ "UPDATE invitation SET status = '"+status+"' WHERE appointment_id = '"+appointmentId+"' AND username = '"+username+"';");
	}
	
	/**
	 * Returns a list of rooms available for an interval with the given seat count
	 * @param startDate
	 * @param endDate
	 * @param minSeatCount
	 * @return List of rooms
	 */
	public static List<Room> getAvailableRoomList(Date startDate, Date endDate, int minSeatCount) {
		List<Room> roomList = new ArrayList<Room>();
		Timestamp startTime = new Timestamp(startDate.getTime());
		Timestamp endTime = new Timestamp(endDate.getTime());
	
		Query query = DBConnector.makeQuery(""
				+ "SELECT room_id, name, seat_count "
				+ "FROM room "
				+ "WHERE seat_count >= '"+minSeatCount+"' "
						+ "AND room_id NOT IN (SELECT room_id "
									  + "FROM appointment "
									  + "WHERE room_id IS NOT NULL "
									  + "AND ((start_time < '"+startTime+"' AND end_time > '"+startTime+"') "
									  + "OR (start_time < '"+endTime+"' AND end_time > '"+endTime+"')));");
		ResultSet result = query.getResult();
		try {
			while (result.next()) {
				int roomId = result.getInt("room_id");
				String name = result.getString("name");
				int seatCount = result.getInt("seat_count");

				Room room = new Room(roomId, name, seatCount);
				roomList.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return roomList;	
	}

	public static Room getRoom(int roomId) {
		Room room = null;
	
		Query query = DBConnector.makeQuery(""
				+ "SELECT name, seat_count "
				+ "FROM room "
				+ "WHERE room_id = '"+roomId+"';");
		ResultSet result = query.getResult();
		try {
			if (result.next()) {
				String name = result.getString("name");
				int seatCount = result.getInt("seat_count");

				room = new Room(roomId, name, seatCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return room;	
	}

}
