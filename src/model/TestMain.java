package model;

import java.util.ArrayList;
import java.util.Date;

public class TestMain {

	
	
	public static void main(String[] args) {
		
		
		
		User Adrian = new User("Adrian");
		User Mikkel = new User("Mikkel");
		ArrayList<User> brukere = new ArrayList<User>();
		brukere.add(Adrian);
		
		
		Date startdato = new Date(2000, 7, 7, 11, 45);
		Date sluttdato = new Date(2000, 7, 7, 12, 11);
		CalendarEvent tivoli = new CalendarEvent("Tivoli", startdato, brukere, sluttdato );
		
		tivoli.addParticipant(Mikkel);
		
		
		
		
		
	}
}
