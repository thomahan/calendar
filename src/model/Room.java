package model;

import java.util.Date;

public class Room {
	
	
	private String name;
	private Date date;
	
	
	public Room(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
//	public boolean isAvailable(){
//		
//	}

}
