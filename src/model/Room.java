package model;

public class Room {
	private final int id;
	private String name;
	private int seatCount;
	
	public Room(int id, String name, int size){
		this.id = id;
		this.name = name;
		this.seatCount = size;
	}
		
	public int getId(){
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Room room = (Room) obj;
		return this.getId() == room.getId();
	}

	@Override
	public String toString() {
		String nounForm = (seatCount == 1) ? "seat" : "seats";
		return name+" ("+seatCount+" "+nounForm+")";
	}
		
}
