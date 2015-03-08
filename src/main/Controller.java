package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Appointment;
import model.Room;
import model.User;
import view.CalendarProgram;
import view.LogIn;
import view.NewEvent;
import view.NewUser;
import db.AppointmentDBC;
import db.UserDBC;

public class Controller {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	private DateFormat simpleDateFormat;

	private LogIn loginView;
	private NewUser registrationView;
	private CalendarProgram calendarView;
	private NewEvent appointmentCreationView;

	private User user;
	private Date selectedDate;
	private ArrayList<Appointment> dailyAppointmentList;
	private static ArrayList<Room> roomlist = new ArrayList<Room>();

	public Controller() {
		openLoginView();
		//openCalendarView();
		simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

		selectedDate = new Date();
		selectedDate.setHours(0);
		selectedDate.setMinutes(0);
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
				}else if (!user.isPasswordCorrect(password)) {
					throw new Exception("Password incorrect.");
				}
				
				loginView.displayLoginMessage(user.getName());

				closeLoginView();
				openCalendarView();

			} catch (Exception e) {
				user = null;
				loginView.displayErrorMessage(e.getMessage());
			}
		}
	}

	class OpenRegistrationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			closeLoginView();
			openRegisterView();
		}
	}

	class RegisterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try{
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
				
				user = new User(firstName, lastName, username, password);
				UserDBC.addUser(user);

				registrationView.displayRegisterMessage(username);

				closeRegisterView();
				openLoginView();

			} catch (Exception e){
				user = null;
				registrationView.displayErrorMessage(e.getMessage());
			}

		}
		
	}

	class CancelRegistrationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			closeRegisterView();
			openLoginView();
		}
	}

	class LogoutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			closeCalendarView();
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
				String alarmTime = appointmentCreationView.getAlarmTime();
				String title = appointmentCreationView.getAppointmentTitle();
				String description = appointmentCreationView.getDescription();
				String location = appointmentCreationView.getAppointmentLocation();
				int roomId = appointmentCreationView.getRoomId();

				Date startDate = simpleDateFormat.parse(startTime);
				Date endDate = simpleDateFormat.parse(endTime);
				Date alarmDate;
				if (alarmTime.length() > 0) {
					alarmDate = simpleDateFormat.parse(alarmTime);
				} else {
					alarmDate = null;
				}
				
				if (title.length() <= 0) {
					throw new Exception("Title must be specified.");
				} else if (startTime == null) {
					throw new Exception("Start time must be specified.");
				} else if (endTime == null) {
					throw new Exception("End time must be specified.");
				}
				
				location = location.length() > 0 ? location : null;
				description = description.length() > 0 ? description : null;

				int appointmentId = AppointmentDBC.addAppointment(startDate, endDate, alarmDate, title, description, location, user.getUsername(), roomId);
				AppointmentDBC.addInvitation(appointmentId, user.getUsername(), "Accepted");

				appointmentCreationView.displayAppointmentCreationMessage(title);

				closeAppointmentCreationView();
				
				dailyAppointmentList = AppointmentDBC.getAppointmentList(user.getUsername(), selectedDate);
				calendarView.setDailyAppointmentList(dailyAppointmentList);
			} catch (Exception e) {
				e.printStackTrace();
				appointmentCreationView.displayErrorMessage(e.getMessage());
			}
		}
	}
	
	class CancelAppointmentCreationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			closeAppointmentCreationView();
		}
	}
	
	class SelectDateListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			selectedDate = calendarView.getSelectDate();
			dailyAppointmentList = AppointmentDBC.getAppointmentList(user.getUsername(), selectedDate);
			calendarView.setDailyAppointmentList(dailyAppointmentList);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	
	private void openLoginView() {
		loginView = new LogIn();
		loginView.addLoginButtonListener(new LoginListener());
		loginView.addRegisterButtonListener(new OpenRegistrationListener());
	}
	
	private void closeLoginView() {
		loginView.dispose();
		loginView = null;
	}
	
	private void openRegisterView() {
		registrationView = new NewUser();
		registrationView.addRegisterListener(new RegisterListener());
		registrationView.addCancelButtonListener(new CancelRegistrationListener());
	}

	private void closeRegisterView() {
		registrationView.dispose();
		registrationView = null;
	}
	
	private void openCalendarView() {
		calendarView = new CalendarProgram();
		calendarView.addNewAppointmentButtonListener(new OpenAppointmentCreationListener());
		calendarView.addLogoutButtonListener(new LogoutListener());
		calendarView.addSelectDateListener(new SelectDateListener());
	}
	
	private void closeCalendarView() {
		calendarView.dispose();
		calendarView = null;
		if (appointmentCreationView != null) {
			closeAppointmentCreationView();
		}
		user = null;
	}
	
	private void openAppointmentCreationView() {
		appointmentCreationView = new NewEvent();
		appointmentCreationView.addCreateButtonListener(new CreateAppointmentListener());
		appointmentCreationView.addCancelButtonListener(new CancelAppointmentCreationListener());
	}
		
	private void closeAppointmentCreationView() {
		appointmentCreationView.dispose();
		appointmentCreationView = null;
	}

	public void addToRoomlist(Room room){
		roomlist.add(room);
	}
	
	public void removeFromRoomlist(Room room){
		roomlist.remove(room);
	}
	
	public static ArrayList<Room> getRoomlist(){
		return roomlist;
	}
}
