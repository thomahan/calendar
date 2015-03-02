package model;

import java.util.ArrayList;

public class RoomList {

	public static ArrayList<Room> roomlist = new ArrayList<Room>();
	
	
	public static void addroom(Room room){
		roomlist.add(room);
	}
	
	public static void removeroom(Room room){
		roomlist.remove(room);
	}
	
	
	
	
}
