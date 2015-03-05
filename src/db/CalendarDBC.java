package db;

import java.sql.Timestamp;
import java.util.Date;

import model.CalendarEvent;

public class CalendarDBC {
/*	
	public static  getCalendarEvent(int appointmentId, String username) {
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
	
	
	public ArrayList<CalendarEvent> getAllCalendarEvents(String username) {
		ArrayList<CalendarEvent> calendarEventList =  new ArrayList<CalendarEvent>();

		Query query = DBConnector.makeQuery(""
				+ "SELECT appointment_id, start_time, end_time, alarm_time, description, location, creator, room_id, name, seat_count, status, is_visible "
				+ "FROM appointment_invitation "
				+ "JOIN appointment_invitation ON appointment.id = appointment_invitation.appointment_id "
				+ "JOIN room ON appointment.roomid = room.id;");
		ResultSet result = query.getResult();

		try {
			if (result.next()) {
				int appointmentId = result.getInt("appointment_id");
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

				CalendarEvent calendarEvent = new CalendarEvent(appointmentId, startTime, endTime, description, location, canEdit, status, isVisible);

				calendarEventList.add(calendarEvent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			query.close();
		}

		return calendarEventList;
	}
*/
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
	public static void addEvent(Date startTimeDate, Date endTimeDate, Date alarmTimeDate, String description, String location, String username, int roomId){
		Timestamp startTime = new Timestamp(startTimeDate.getTime());
		Timestamp endTime = new Timestamp(endTimeDate.getTime());
		Timestamp alarmTime = new Timestamp(alarmTimeDate.getTime());

		DBConnector.makeStatement(""
			+ "INSERT INTO appointment (start_time, end_time, alarm_time, description, location, creator, room_id) "
			+ "VALUES ('"+startTime+"', "
					 +"'"+endTime+"', "
					 +"'"+alarmTime+"', "
					 +"'"+description+"', "
					 +"'"+location+"', "
					 +"'"+username+"', "
					 +"'"+roomId+"');");
	}
	
	public static void removeEvent(String username, CalendarEvent event){
		
		
	}
/*	
	public static Calendar getCalendar(String username){
		
	}
*/	
	
}
