package model;

public class Invitation {

	private Appointment event;
	public boolean reply;
	// HashMap(User user, boolean answer);
	
	
	public Invitation(Appointment event){
		this.event = event;
	}
	
	
	public void sendInvitationToUser(User user){
//		System.out.println("Do you want to attend " + event.getEventName() + " at " + event.getStartDate() + " at room " + event.getRoom().getName() + "? Answer YES or NO in capital letters.");
		System.out.println("Hva velger du " + user.getFirstName() + "?");
		if (user.answerInvitation()== true){
			reply = true;
		} else {
			reply = false;
		}
	}
	
	
}
