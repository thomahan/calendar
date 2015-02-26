package model;

import java.util.ArrayList;
import java.util.Date;

public class TestMain {

	
	
	public static void main(String[] args) {
		
		
		
		User Adrian = new User("Adrian");
		User Mikkel = new User("Mikkel");
		User Helene = new User("Helene");
		User Aksel = new User("Aksel");
		User Thomas = new User("Thomas");
		ArrayList<User> brukere = new ArrayList<User>();
		brukere.add(Adrian);
		brukere.add(Mikkel);
		ArrayList<User> brukere1 = new ArrayList<User>();
		brukere1.add(Helene);
		brukere1.add(Aksel);
		Room rom1 = new Room("Zevs");
		
		
		Date startdato = new Date(2000, 7, 7, 11, 45);
		Date sluttdato = new Date(2000, 7, 7, 12, 11);
		CalendarEvent tivoli = new CalendarEvent("Tivoli", startdato, brukere, sluttdato );
		
		tivoli.setRoom(rom1);
		Adrian.getCalendar().showMyEvents();
		Mikkel.getCalendar().showMyEvents();
		tivoli.setEventName("Dyrepark");
		tivoli.removeParticipant(Mikkel);
		tivoli.addParticipant(Mikkel);
		
		
		
		
		
		
		
	}
}
