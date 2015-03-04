package model;

public class Room {
	
	private final int id;
	private String name;
	private int seatCount;
	
	public Room(int id, String name, int size){
		this.id = id;
		this.name = name;
		this.seatCount = size;
		main.Controller.getRoomlist().add(this);
	}
		
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getSeatCount(){
		return seatCount;
	}

}
