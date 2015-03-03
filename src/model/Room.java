package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import db.Query;

public class Room {
	
	
	private String name;
	// private final int id;
	
	
	public Room(String name){
		this.name = name;
		RoomList.addroom(this);
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	

}
