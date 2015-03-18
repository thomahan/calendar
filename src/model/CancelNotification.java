package model;

public class CancelNotification {
	Appointment appointment;
	User canceller;
	
	public CancelNotification(Appointment appointment, User canceller) {
		this.appointment = appointment;
		this.canceller = canceller;
		appointment.setShowDateByDefault(true);
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public User getCanceller() {
		return canceller;
	}

	@Override
	public String toString() {
		return appointment.toString().substring(0, appointment.toString().length()-12) +"<hmtl>" + canceller.toString() + " has cancelled.<br><br></html>";
	}
	
}
