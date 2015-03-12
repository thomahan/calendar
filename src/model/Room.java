package model;

public class Room {
	
	private final int id;
	private String name;
	private int seatCount;
	private Calendar calendar;
	
	public Room(int id, String name, int size){
		this.id = id;
		this.name = name;
		this.seatCount = size;
		main.Controller.getRoomlist().add(this);
		this.calendar = new Calendar(this);
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
	
	public Calendar getCalendar() {
		return calendar;
	}
	
	public int getSeatCount(){
		return seatCount;
	}

	@Override
	public String toString() {
		String nounForm = (seatCount == 1) ? "seat" : "seats";
		return name+" ("+seatCount+" "+nounForm+")";
	}
}
