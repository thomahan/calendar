package model;

import java.util.ArrayList;
import java.util.Date;

public class TestMain {

	private static ArrayList<Room> roomList;
	
	public static ArrayList<Room> getRoomList() {
		return roomList;
	}



	public static void main(String[] args) {
		
		
		
		User Adrian = new User("Adrian", "Isdahl", "adrianti", "ntnu");
		User Adrian1 = new User("Adrian1", "Isdahl", "adrianti", "ntnu");
		User Adrian2 = new User("Adrian2", "Isdahl", "adrianti", "ntnu");
		User Adrian3 = new User("Adrian3", "Isdahl", "adrianti", "ntnu");
		
		ArrayList<User> brukere = new ArrayList<User>();
		brukere.add(Adrian);
		brukere.add(Adrian1);
		brukere.add(Adrian2);
		brukere.add(Adrian3);
//		ArrayList<User> brukere1 = new ArrayList<User>();
//		brukere1.add(Helene);
//		brukere1.add(Aksel);
		
		roomList = new ArrayList<Room>();
		Room rom1 = new Room(3, "Zevs", 5);
		Room rom2 = new Room(4, "Biblo", 8);
		Room rom3 = new Room(5, "F", 10);
		Room rom4 = new Room(6, "EL", 12);
		Room rom5 = new Room(7, "Stripa", 15);
		roomList.add(rom1);
		roomList.add(rom2);
		roomList.add(rom3);
		//roomList.add(rom4);
		//roomList.add(rom5);
		
		
		Date startdato1 = new Date(2000, 7, 7, 11, 45);
		Date sluttdato1 = new Date(2000, 7, 7, 11, 50);
		Interval interval1 = new Interval(startdato1, sluttdato1);
		Date startdato2 = new Date(2000, 7, 7, 11, 50);
		Date sluttdato2 = new Date(2000, 7, 7, 11, 54);
		Interval interval2 = new Interval(startdato2, sluttdato2);
		Date startdato3 = new Date(2000, 7, 7, 11, 54);
		Date sluttdato3 = new Date(2000, 7, 7, 11, 57);
		
		
		Appointment tivoli = new Appointment(1, startdato1, sluttdato1, startdato1, "Skolen", true, "Lekser", true);
		Appointment tivoli1 = new Appointment(1, startdato2, sluttdato2, startdato2, "hjemem", true, "Ler", true);
		Appointment tivoli2 = new Appointment(1, startdato3, sluttdato3, startdato3, "hjdf", true, "Lsd", true);
//		tivoli.setRoom(rom1);
//		tivoli1.setRoom(rom2);
		System.out.println(tivoli.getRoom().getName());
		System.out.println(tivoli1.getRoom().getName());
		
		
		tivoli.addParticipant(Adrian);
		tivoli1.addParticipant(Adrian1);
		tivoli2.addParticipant(Adrian2);
		Adrian.getCalendar().showEventsOnADay(startdato1);		
		Adrian1.getCalendar().showEventsOnADay(startdato1);		
		Adrian2.getCalendar().showEventsOnADay(startdato1);		
	}
}
