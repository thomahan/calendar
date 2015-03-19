package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Room;

public class RoomDBC {

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

	public static void setAppointmentRoom(int appointmentId, int roomId) {
		DBConnector.makeStatement(""
			+ "UPDATE appointment "
			+ "SET room_id = '"+roomId+"' "
			+ "WHERE appointment_id = '"+appointmentId+"';");
	}

	public static void releaseRoom(int appointmentId) {
		DBConnector.makeStatement(""
			+ "UPDATE appointment "
			+ "SET room_id = DEFAULT "
			+ "WHERE appointment_id = "+appointmentId+";");
	}

}
