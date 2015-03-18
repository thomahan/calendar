package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Appointment;
import model.CancelNotification;
import model.Room;
import model.User;

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
				+ "SELECT appointment.appointment_id, start_time, end_time, description, location, room_id, creator, status, alarm_time "
				+ "FROM appointment JOIN invitation ON appointment.appointment_id = invitation.appointment_id "
				+ "WHERE appointment.appointment_id = '"+appointmentId+"' AND username = '"+username+"';");
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
				
				if (alarmDate != null) {
					
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
	 * @param intervalStartDate
	 * @param intervalEndDate TODO
	 * @return List of appointments for specified date
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<Appointment> getAppointmentList(String username, Date intervalStartDate, Date intervalEndDate) {
		ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
		
		Timestamp lowerTime = new Timestamp(intervalStartDate.getTime());
		Timestamp upperTime = new Timestamp(intervalEndDate.getTime());
		
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
				
				Room room = (roomId != 0) ? getRoom(roomId) : new Room(0, "", 0);
				appointment.setRoom(room);

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
			+ "VALUES ('"+startTime+"', '"+endTime+"', '"+description+"', '"+username+"');");

		// The following way of referring back to the appointment is unreliable.
		// Code should be refactored with prepared statements to get the auto incremented value.
		Query query = null;
		int appointmentId = 0;
		try {
			query = DBConnector.makeQuery("SELECT appointment_id "
										+ "FROM appointment "
										+ "WHERE appointment_id = (SELECT MAX(appointment_id) "
																+ "FROM appointment "
																+ "WHERE creator ='"+username+"');");
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
					+ "UPDATE appointment "
					+ "SET location = '"+location+"' "
					+ "WHERE appointment_id = '"+appointmentId+"';");
		}
		if (roomId != 0) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment "
					+ "SET room_id = '"+roomId+"' "
					+ "WHERE appointment_id = '"+appointmentId+"';");
		}
		
		return appointmentId;
	}

	public static void updateAppointment(int appointmentId, Date startDate, Date endDate, String description, String location, int roomId){
		Timestamp startTime = new Timestamp(startDate.getTime());
		Timestamp endTime = new Timestamp(endDate.getTime());
	
		DBConnector.makeStatement(""
			+ "UPDATE appointment "
			+ "SET start_time = '"+startTime+"', end_time = '"+endTime+"', description = '"+description+"' "
			+ "WHERE appointment_id = '"+appointmentId+"'");

		if (location != null) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment "
					+ "SET location = '"+location+"' "
					+ "WHERE appointment_id = '"+appointmentId+"';");
		}

		if (roomId != 0) {
			DBConnector.makeStatement(""
					+ "UPDATE appointment "
					+ "SET room_id = '"+roomId+"' "
					+ "WHERE appointment_id = '"+appointmentId+"';");
		}
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
					+ "VALUES ('"+appointmentId+"', '"+username+"', '"+status+"');");
		} else {
			DBConnector.makeStatement(""
					+ "INSERT INTO invitation (appointment_id, username, unseen_change) "
					+ "VALUES ('"+appointmentId+"', '"+username+"', 1);");
		}
	}

	public static void removeInvitation(int appointmentId, String username) {
		DBConnector.makeStatement(""
				+ "DELETE FROM invitation "
				+ "WHERE appointment_id = '"+appointmentId+"' AND username = '" + username + "';");
	}

	public static void addGroupInvitation(int appointmentId, int groupId) {
		DBConnector.makeStatement(""
				+ "INSERT INTO group_invitation (appointment_id, user_group_id) "
				+ "VALUES ('"+appointmentId+"', '"+groupId+"');");
	}
	
	/**
	 * Sets the status of an invitation
	 * @param appointmentId
	 * @param username
	 */
	public static void setInvitationStatus(int appointmentId, String username, String status) {
		DBConnector.makeStatement(""
			+ "UPDATE invitation "
			+ "SET status = '"+status+"' "
			+ "WHERE appointment_id = '"+appointmentId+"' "
			  + "AND username = '"+username+"';");
	}
	
	public static void addChangeNotification(int appointmentId, String username) {
		List<User> invitedUserList = getInvitedUserList(appointmentId);
		for (User user : invitedUserList) {
			if (!user.getUsername().equals(username)) {
				DBConnector.makeStatement(""
						+ "UPDATE invitation "
						+ "SET unseen_change = '"+1+"' "
						+ "WHERE appointment_id = '"+appointmentId+"' "
			  			+ "AND username = '"+user.getUsername()+"';");	
			}
		}
	}
	
	public static void acceptChangeNotification(int appointmentId, String username) {
		DBConnector.makeStatement(""
			+ "UPDATE invitation "
			+ "SET unseen_change = '"+0+"' "
			+ "WHERE appointment_id = '"+appointmentId+"' "
			  + "AND username = '"+username+"';");	
	}

	public static List<User> getInvitedUserList(int appointmentId) {
		ArrayList<User> invitedUserList = new ArrayList<User>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT username "
				+ "FROM invitation "
				+ "WHERE appointment_id = "+appointmentId+";");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				String username = result.getString("username");
				User user = new User(username, "", "", "", "");
				invitedUserList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return invitedUserList;
	}
	
	public static List<User> getParticipantList(int appointmentId) {
		ArrayList<User> participantList = new ArrayList<User>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT user.username, user.first_name, user.last_name, status, creator "
				+ "FROM invitation "
					+ "JOIN user ON invitation.username = user.username "
					+ "JOIN appointment ON invitation.appointment_id = appointment.appointment_id "
				+ "WHERE invitation.appointment_id = "+appointmentId+" "
				+ "ORDER BY status ASC;");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				String username = result.getString("user.username");
				String firstName = result.getString("user.first_name");
				String lastName = result.getString("user.last_name");
				String status = result.getString("status");
				String creator = result.getString("creator");
				String statusString;
				if (username.equals(creator)) {
					statusString = "Appointment creator";
				} else {
					statusString = status;
				}
				participantList.add(new User(username, "", "", firstName, lastName + " (" + statusString + ")"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return participantList;
	}

	public static void addCancelNotification(int appointmentId, String username) {
		List<User> invitedUserList = getInvitedUserList(appointmentId);
		for (User user : invitedUserList) {
			if (!user.getUsername().equals(username)) {
				DBConnector.makeStatement(""
					+ "INSERT IGNORE INTO cancel_notification "
					+ "VALUES ('"+appointmentId+"', '"+user.getUsername()+"', '"+username+"');");
			}
		}
	}

	public static void removeCancelNotification(int appointmentId, String username) {
		DBConnector.makeStatement(""
			+ "DELETE FROM cancel_notification "
			+ "WHERE appointment_id = '"+appointmentId+"' AND username = '"+username+"';");
	}
		
	public static List<CancelNotification> getCancelNotificationList(String username) {
		ArrayList<CancelNotification> cancelNotificationList = new ArrayList<CancelNotification>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT appointment_id, canceller, first_name, last_name "
				+ "FROM cancel_notification JOIN user ON cancel_notification.canceller = user.username "
				+ "WHERE cancel_notification.username = '"+username+"';");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				int appointmentId = result.getInt("appointment_id");
				String cancellerUsername = result.getString("canceller");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");

				Appointment appointment = getAppointment(appointmentId, username);

				if (!(appointment.getStatus().equals("Hidden") || appointment.getStatus().equals("Cancelled"))) {
					User canceller = new User(cancellerUsername, "", "", firstName, lastName);
					CancelNotification cancelNotification = new CancelNotification(appointment, canceller);

					cancelNotificationList.add(cancelNotification);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return cancelNotificationList;
	}
			
	public static List<Appointment> getChangedAppointmentList(String username) {
		ArrayList<Appointment> changedAppointmentList = new ArrayList<Appointment>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT appointment_id "
				+ "FROM invitation "
				+ "WHERE username = '"+username+"' AND unseen_change = 1;");
		ResultSet result = query.getResult();

		try {
			while (result.next()) {
				int appointmentId = result.getInt("appointment_id");
				Appointment appointment = getAppointment(appointmentId, username);
				changedAppointmentList.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return changedAppointmentList;
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
									      + "OR (start_time < '"+endTime+"' AND end_time > '"+endTime+"')"
									      + "OR (start_time <= '"+startTime+"' AND end_time >= '"+endTime+"')"
									      + "OR (start_time >= '"+startTime+"' AND end_time <= '"+endTime+"')));");
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
	
	public static void releaseRoom(int appointmentId) {
		DBConnector.makeStatement(""
			+ "UPDATE appointment "
			+ "SET room_id = DEFAULT "
			+ "WHERE appointment_id = "+appointmentId+";");
	}
	
	public static void setAppointmentRoom(int appointmentId, int roomId) {
		DBConnector.makeStatement(""
			+ "UPDATE appointment "
			+ "SET room_id = '"+roomId+"' "
			+ "WHERE appointment_id = '"+appointmentId+"';");
	}
}
