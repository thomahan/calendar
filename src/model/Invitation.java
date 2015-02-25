package model;

public class Invitation {

	private CalendarEvent event;
	public boolean reply;
	// HashMap(User user, boolean answer);
	
	
	public Invitation(CalendarEvent event){
		this.event = event;
	}
	
	
	public void sendInvitationToUser(User user){
//		System.out.println("Do you want to attend " + event.getEventName() + " at " + event.getStartDate() + " at room " + event.getRoom().getName() + "? Answer YES or NO in capital letters.");
		System.out.println("Hva velger du?");
		if (user.answerInvitation(this)== true){
			reply = true;
		} else {
			reply = false;
		}
	}
	
	public void sendInvitationToGroup(Group group){
		int n = group.getMembers().size();
		for (int i = 0; i < n; i++) {
			sendInvitationToUser(group.getMembers().get(i));
		}
	}
	
}
