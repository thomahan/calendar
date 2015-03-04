package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import db.Query;

public class Room {
	
	
	private String name;
	private int size;
	private final int id;
	
	
	
	public Room(String name, int id, int size){
		this.name = name;
		this.id = id;
		this.size = size;
		main.Controller.getRoomlist().add(this);
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getId(){
		return id;
	}
	

}
