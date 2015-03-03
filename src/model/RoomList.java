package model;

import java.util.ArrayList;

public class RoomList {

	public ArrayList<Room> roomlist = new ArrayList<Room>();
	
	
	public RoomList(){ // Må opprettes fra starten av
		
	}
	
	
	public void addroom(Room room){
		roomlist.add(room);
	}
	
	public void removeroom(Room room){
		roomlist.remove(room);
	}
	
	
	
	
}
