package model;

public class Invitation {

	private CalendarEvent event;
	public boolean reply;
	// HashMap(User user, boolean answer);
	
	
	public Invitation(CalendarEvent event){
		this.event = event;
	}
	
	
	public void sendInvitationToUser(User user){
		System.out.println("Do you want to attend " + event.getEventName() + " at " + event.getDate() + " at room " + event.getRoom().getRoomName()) + "?";
	}// må sjekke at gruppemetoden heter getGroupName() når Group pushes opp. Hvordan skal bruker svare?
	// Lage en metode i User for å svare?
	
	public void sendInvitationToGroup(Group group){
		
	}
	
}
