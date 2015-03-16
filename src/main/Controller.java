package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Appointment;
import model.Group;
import model.Room;
import model.User;
import view.AppointmentCreationView;
import view.CalendarView;
import view.GroupInvitationView;
import view.LoginView;
import view.RegistrationView;
import view.RoomReservationView;
import view.SeeParticipantsView;
import view.UserInvitationView;
import db.AppointmentDBC;
import db.UserDBC;

public class Controller {
	private LoginView loginView;
	private RegistrationView registrationView;
	private CalendarView calendarView;
	private AppointmentCreationView appointmentCreationView;
	private UserInvitationView userInvitationView;
	private GroupInvitationView groupInvitationView;
	private RoomReservationView roomReservationView;
	private SeeParticipantsView seeParticipantsView;

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	private DateFormat simpleDateFormat;

	private User user;
	private List<User> userList;
	private List<User> invitedUserList;
	private List<String> participantList;
	private List<Group> groupList;
	private List<Group> invitedGroupList;
	private Date selectedDate;
	private Appointment selectedAppointment;
	private ArrayList<Appointment> dailyAppointmentList;
	private List<Room> availableRoomList;
	private Room reservedRoom;
	private Room releasedRoom;

	@SuppressWarnings("deprecation")
	public Controller() {
		simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

		userList = new ArrayList<User>();
		invitedUserList = new ArrayList<User>();
		groupList = new ArrayList<Group>();
		invitedGroupList = new ArrayList<Group>();
		dailyAppointmentList = new ArrayList<Appointment>();
		availableRoomList = new ArrayList<Room>();

		selectedDate = new Date();
		selectedDate.setHours(0);
		selectedDate.setMinutes(0);
		
		reservedRoom = new Room(0, "", 0);
		releasedRoom = null;

		openLoginView();
	}
	
	class OpenRegistrationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			registrationView = new RegistrationView();
			registrationView.addRegisterButtonListener(new RegisterListener());
			registrationView.addCancelButtonListener(new CancelRegistrationListener());
		}
	}

	class RegisterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				String username = registrationView.getUsername();
				String firstName = registrationView.getFirstName();
				String lastName = registrationView.getLastName();
				String password = registrationView.getPassword();
				String passwordConfirm = registrationView.getPasswordConfirm();

				if (username.length() <= 0) {
					throw new Exception("Username must be specified.");
				} else if (UserDBC.getUser(username) != null) {
					throw new Exception("Username is already taken.");
				} else if (password.length() <= 0) {
					throw new Exception("Password must be specified.");
				} else if (!password.equals(passwordConfirm)){					
					throw new Exception("Passwords do not match.");
				}
				
				User newUser = new User(firstName, lastName, username, password);
				UserDBC.addUser(newUser);

				registrationView.displayRegisterMessage(username);
				registrationView.dispose();
			} catch (Exception e){
				registrationView.displayErrorMessage(e.getMessage());
			}
		}
	}

	class CancelRegistrationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			registrationView.dispose();
		}
	}

	class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				String username = loginView.getUsername();
				String password = loginView.getPassword();
				
				if (username.length() <= 0) {
					throw new Exception("Username must be specified.");
				}

				user = UserDBC.getUser(username);
				
				if (user == null) {
					throw new Exception("Username does not exist.");
				} else if (!user.isPasswordCorrect(password)) {
					throw new Exception("Password incorrect.");
				}

				loginView.dispose();
				openCalendarView();
			} catch (Exception e) {
				user = null;
				loginView.displayErrorMessage(e.getMessage());
			}
		}
	}

	class LogoutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			calendarView.dispose();
			user = null;
			openLoginView();
		}
	}
	
	class OpenAppointmentCreationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			openAppointmentCreationView();
			appointmentCreationView.setStartTime(simpleDateFormat.format(selectedDate));
			appointmentCreationView.setEndTime(simpleDateFormat.format(selectedDate));
		}
	}
	
	class CreateAppointmentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				String startTime = appointmentCreationView.getStartTime();
				String endTime = appointmentCreationView.getEndTime();
				String description = appointmentCreationView.getDescription();
				String location = appointmentCreationView.getAppointmentLocation();

				Date startDate = simpleDateFormat.parse(startTime);
				Date endDate = simpleDateFormat.parse(endTime);
			
				if (startTime == null) {
					throw new Exception("Start time must be specified.");
				} else if (endTime == null) {
					throw new Exception("End time must be specified.");
				}else if (description.length() <= 0) {
					throw new Exception("Description must be specified.");
				} 
				
				location = location.length() > 0 ? location : null;


				int appointmentId;
				if (appointmentCreationView.isNewAppointment()) {
					if (reservedRoom.getId() != 0) {
						availableRoomList = AppointmentDBC.getAvailableRoomList(startDate, endDate, 0);
						if (!availableRoomList.contains(reservedRoom)) {
							reservedRoom = new Room(0, "", 0);
							throw new Exception("The room could not be reserved for the new time period.");
						}	
					}

					appointmentId = AppointmentDBC.addAppointment(startDate, endDate, description, location, user.getUsername(), reservedRoom.getId());
					AppointmentDBC.addInvitation(appointmentId, user.getUsername(), "Accepted");
				} else {

					appointmentId = selectedAppointment.getId();
					System.out.println(appointmentId);
					System.out.println("New location "+location);
					System.out.println("Old location "+selectedAppointment.getLocation());
					Appointment editedAppointment = new Appointment(appointmentId, startDate, endDate, description, true, selectedAppointment.getStatus());
					editedAppointment.setLocation(location);
					
					if (reservedRoom.getId() == 0) {
						reservedRoom = selectedAppointment.getRoom();
					}

					if (selectedAppointment.getRoom().getId() != 0) {
						AppointmentDBC.releaseRoom(appointmentId);
						releasedRoom = selectedAppointment.getRoom();
					}
					editedAppointment.setRoom(reservedRoom);

					if (reservedRoom.getId() != 0) {
						availableRoomList = AppointmentDBC.getAvailableRoomList(startDate, endDate, 0);
						if (!availableRoomList.contains(reservedRoom)) {
							releasedRoom = selectedAppointment.getRoom();
							reservedRoom = new Room(0, "", 0);
							selectedAppointment.setRoom(reservedRoom);
							throw new Exception("The room could not be reserved for the new time period.");
						}
					}
					
					if (releasedRoom != null && selectedAppointment.getRoom().getId() != 0) {
						AppointmentDBC.setAppointmentRoom(selectedAppointment.getId(), selectedAppointment.getRoom().getId());
						releasedRoom = null;
					}

					System.out.println(selectedAppointment);
					System.out.println(editedAppointment);
					System.out.println(selectedAppointment.equals(editedAppointment));
					if (!selectedAppointment.equals(editedAppointment)) {
						System.out.println("Updating appointment: "+appointmentId);
						AppointmentDBC.updateAppointment(appointmentId, startDate, endDate, description, location, reservedRoom.getId());
						AppointmentDBC.addChangeNotification(appointmentId, user.getUsername());
					}
				}
				reservedRoom = new Room(0, "", 0);
				releasedRoom = null;

				for (User u : invitedUserList) {
					AppointmentDBC.addInvitation(appointmentId, u.getUsername(), null);
				}
				invitedUserList.clear();
				
				if (!invitedGroupList.isEmpty()) {
					GroupInviter.inviteGroupList(appointmentId, invitedGroupList);
				}
				invitedGroupList.clear();
				
				if (appointmentCreationView.isNewAppointment()) {
					appointmentCreationView.displayAppointmentCreationMessage(description);
				} else {
					appointmentCreationView.displayAppointmentSaveMessage(description);
				}

				appointmentCreationView.dispose();
				
				updateDailyAppointments();
			} catch (Exception e) {
				e.printStackTrace();
				appointmentCreationView.displayErrorMessage(e.getMessage());
			}
		}
	}
	
	class CancelAppointmentCreationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (releasedRoom != null) {
				AppointmentDBC.setAppointmentRoom(selectedAppointment.getId(), releasedRoom.getId());
			}
			appointmentCreationView.dispose();
		}
	}
	
	class OpenUserInvitationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			userInvitationView = new UserInvitationView();
			userInvitationView.addConfirmButtonListener(new InviteUserListener());
			userInvitationView.addCancelButtonListener(new CancelUserInvitationListener());
			
			userList = UserDBC.getUserList();
			// This should be done differently
			List<User> removeList = new ArrayList<User>();
			for (User u : userList) {
				if (u.getUsername().equals(user.getUsername())) {
					removeList.add(u);
				}
			}
			
			for (User r : removeList) {
				userList.remove(r);
				
			}
			userInvitationView.setUserList(userList);
		}

	}
	
	class InviteUserListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			invitedUserList = userInvitationView.getInvitedPersons();
			userInvitationView.dispose();
		}
	}
	
	class CancelUserInvitationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			userInvitationView.dispose();
		}
	}
	
	class OpenGroupInvitationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			groupInvitationView = new GroupInvitationView();
			groupInvitationView.addInviteButtonListener(new InviteGroupListener());
			groupInvitationView.addCancelButtonListener(new CancelGroupInvitationListener());
			
			groupList = UserDBC.getGroupList();
			groupInvitationView.setGroupList(groupList);
			
		}
	}
	
	class InviteGroupListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			invitedGroupList = groupInvitationView.getInvitedGroupList();
			groupInvitationView.dispose();
		}
	}

	class CancelGroupInvitationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			groupInvitationView.dispose();
		}
	}

	class OpenRoomReservationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int minSeatCount = 0;
			try {
				String startTime = appointmentCreationView.getStartTime();
				String endTime = appointmentCreationView.getEndTime();
				Date startDate = simpleDateFormat.parse(startTime);
				Date endDate = simpleDateFormat.parse(endTime);
				String minSeatCountString = appointmentCreationView.getMinSeatCount();
				if (minSeatCountString.length() > 0) {
					minSeatCount = Integer.parseInt(minSeatCountString);
				}

				roomReservationView = new RoomReservationView();
				roomReservationView.addReserveButtonListener(new ReserveRoomListener());
				roomReservationView.addCancelButtonListener(new CancelRoomReservationListener());

				availableRoomList = AppointmentDBC.getAvailableRoomList(startDate, endDate, minSeatCount);
				roomReservationView.setRoomList(availableRoomList);

			} catch (Exception ex) {
				appointmentCreationView.displayErrorMessage("Start and end time must be specified.");
			}
		}
	}
	
	class ReserveRoomListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			reservedRoom = roomReservationView.getSelectedRoom();
			roomReservationView.dispose();
		}
	}
	
	class CancelRoomReservationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			roomReservationView.dispose();
		}
	}
	
	class SelectMonthListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//calendarView.setMonthlyAppointmentList();
		}
	}
	
	class SelectDateListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			updateDailyAppointments();
		}

		@Override public void mouseEntered(MouseEvent arg0) {}
		@Override public void mouseExited(MouseEvent arg0) {}
		@Override public void mousePressed(MouseEvent arg0) {}
		@Override public void mouseReleased(MouseEvent arg0) {}
	}
		
	class SelectAppointmentListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			selectedAppointment = calendarView.getSelectedAppointment();

			if (selectedAppointment != null) {
				if (selectedAppointment.isEditable()) {
					calendarView.setAppointmentAccess("Owned");
				} else {
					calendarView.setAppointmentAccess(selectedAppointment.getStatus());
				}
			}
		}

		@Override public void mouseEntered(MouseEvent arg0) {}
		@Override public void mouseExited(MouseEvent arg0) {}
		@Override public void mousePressed(MouseEvent arg0) {}
		@Override public void mouseReleased(MouseEvent arg0) {}
	}

	class OpenAppointmentEditingListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			openAppointmentCreationView();
			appointmentCreationView.setNewAppointment(false);
			appointmentCreationView.setTitle("Edit appointment");
			appointmentCreationView.setCreateButtonText("Save");

			appointmentCreationView.setStartTime(simpleDateFormat.format(selectedAppointment.getStartDate()));
			appointmentCreationView.setEndTime(simpleDateFormat.format(selectedAppointment.getEndDate()));
			appointmentCreationView.setDescription(selectedAppointment.getDescription());
			appointmentCreationView.setLocation(selectedAppointment.getLocation());
		}	
	}

	class AcceptAppointmentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			setInvitationStatus("Accepted");
			updateDailyAppointments();
		}
	}

	class DeclineAppointmentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			setInvitationStatus("Declined");
			updateDailyAppointments();
		}
	}

	class HideAppointmentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			setInvitationStatus("Hidden");
			updateDailyAppointments();
		}
	}

	class DeleteAppointmentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			Appointment appointment = calendarView.getSelectedAppointment();

			if (appointment != null) {
				if (appointment.isEditable()) {
					AppointmentDBC.removeAppointment(appointment.getId());
				} else {
					setInvitationStatus("Cancelled");
				}
			}

			updateDailyAppointments();
		}
	}
	
	class OpenSeeParticipantsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Open participant list");
			seeParticipantsView = new SeeParticipantsView();
			seeParticipantsView.addCloseButtonListener(new CloseSeeParticipantsViewListener());
			
			participantList = AppointmentDBC.getParticipantList(selectedAppointment.getId());
			seeParticipantsView.setParticipantList(participantList);
			
		}
	}
	
	class CloseSeeParticipantsViewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			seeParticipantsView.dispose();
		}
	}

	private void openLoginView() {
		loginView = new LoginView();
		loginView.addLoginButtonListener(new LoginListener());
		loginView.addRegisterButtonListener(new OpenRegistrationListener());
	}
	
	private void openCalendarView() {
		calendarView = new CalendarView();

		calendarView.addNewAppointmentButtonListener(new OpenAppointmentCreationListener());
		calendarView.addLogoutButtonListener(new LogoutListener());
		calendarView.addSelectDateListener(new SelectDateListener());
		calendarView.addSelectedAppointmentListener(new SelectAppointmentListener());

		calendarView.addEditButtonListener(new OpenAppointmentEditingListener());
		calendarView.addAcceptButtonListener(new AcceptAppointmentListener());
		calendarView.addDeclineButtonListener(new DeclineAppointmentListener());
		calendarView.addHideButtonListener(new HideAppointmentListener());
		calendarView.addDeleteButtonListener(new DeleteAppointmentListener());
		calendarView.addSeeParticipantListener(new OpenSeeParticipantsListener());
		
		calendarView.setTitle(calendarView.getTitle()+" ("+user.getName()+")");
	}
	
	private void openAppointmentCreationView() {
		appointmentCreationView = new AppointmentCreationView();
		appointmentCreationView.addCreateButtonListener(new CreateAppointmentListener());
		appointmentCreationView.addCancelButtonListener(new CancelAppointmentCreationListener());
		appointmentCreationView.addInvitePersonButtonListener(new OpenUserInvitationListener());
		appointmentCreationView.addInviteGroupButtonListener(new OpenGroupInvitationListener());
		appointmentCreationView.addChooseRoomButtonListener(new OpenRoomReservationListener());
	}

	private void setInvitationStatus(String status) {
		Appointment appointment = calendarView.getSelectedAppointment();
			
		AppointmentDBC.setInvitationStatus(appointment.getId(), user.getUsername(), status);
		if (status.equals("Cancelled")) {
			AppointmentDBC.addCancelNotification(appointment.getId(), user.getUsername());
		}

		updateDailyAppointments();
	}

	private void updateDailyAppointments() {
		selectedDate = calendarView.getSelectedDate();

		dailyAppointmentList = AppointmentDBC.getAppointmentList(user.getUsername(), selectedDate);
		
		ArrayList<Appointment> removeList = new ArrayList<Appointment>();
		for (Appointment a : dailyAppointmentList) {
			if (a != null) {
				if (a.getStatus().equals("Hidden") || a.getStatus().equals("Cancelled")) {
					removeList.add(a);
				}
			}
		}

		for (Appointment a : removeList) {
			dailyAppointmentList.remove(a);
		}

		calendarView.setDailyAppointmentList(dailyAppointmentList);
		calendarView.setAppointmentAccess("");
	}
}