package model;

public class CancelNotification {
	Appointment appointment;
	User canceller;
	
	public CancelNotification(Appointment appointment, User canceller) {
		this.appointment = appointment;
		this.canceller = canceller;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public User getCanceller() {
		return canceller;
	}

	@Override
	public String toString() {
		return appointment.toString() + canceller.toString() + " has cancelled.";
	}
	
}
