package model;

import java.util.ArrayList;
import java.util.Date;

public class TestMain {

	
	
	public static void main(String[] args) {
		
		
		
		User Adrian = new User("Adrian", "Isdahl", "adrianti", "ntnu");
		User Adrian1 = new User("Adrian", "Isdahl", "adrianti", "ntnu");
		User Adrian2 = new User("Adrian", "Isdahl", "adrianti", "ntnu");
		User Adrian3 = new User("Adrian", "Isdahl", "adrianti", "ntnu");
		
		ArrayList<User> brukere = new ArrayList<User>();
		brukere.add(Adrian);
		brukere.add(Adrian1);
		brukere.add(Adrian2);
		brukere.add(Adrian3);
//		ArrayList<User> brukere1 = new ArrayList<User>();
//		brukere1.add(Helene);
//		brukere1.add(Aksel);
		Room rom1 = new Room(3, "Zevs", 5);
		Room rom2 = new Room(6, "Biblo", 8);
		
		
		Date startdato1 = new Date(2000, 7, 7, 11, 45);
		Date sluttdato1 = new Date(2000, 7, 7, 12, 11);
		Interval interval1 = new Interval(startdato1, sluttdato1);
		Date startdato2 = new Date(2000, 7, 7, 10, 15);
		Date sluttdato2 = new Date(2000, 7, 7, 11, 44);
		Interval interval2 = new Interval(startdato2, sluttdato2);
		CalendarEvent tivoli = new CalendarEvent("Tivoli", startdato1, Adrian, sluttdato1, "Skolen", 5, "Lekser");
		CalendarEvent tivoli1 = new CalendarEvent("Lanparty", startdato2, Adrian, sluttdato2, "Tyholt", 8, "Lekser");
		tivoli.setRoom(rom1);
		tivoli1.setRoom(rom2);
		
		Adrian.getCalendar().showMyEvents();
		
		
		
//		
//		tivoli.setRoom(rom1);
//		Adrian.getCalendar().showMyEvents();
//		Mikkel.getCalendar().showMyEvents();
//		tivoli.setEventName("Dyrepark");
//		tivoli.removeParticipant(Mikkel);
//		tivoli.addParticipant(Mikkel);
//		
		
		
		
		
		
		
	}
}
