package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Room;
import model.User;
import view.CalendarProgram;
import view.LogIn;
import view.NewEvent;
import view.NewUser;
import db.UserDBC;

public class Controller {
	private LogIn loginView;
	private NewUser registerView;
	private CalendarProgram calendarView;
	private NewEvent createAppointmentView;

	private User user;
	private static ArrayList<Room> roomlist = new ArrayList<Room>();
	
	public Controller() {
		openLoginView();
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
				String username = registerView.getUsername();
				String firstName = registerView.getFirstName();
				String lastName = registerView.getLastName();
				String password = registerView.getPassword();
				String passwordConfirm = registerView.getPasswordConfirm();

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

				registerView.displayRegisterMessage(username);

				closeRegisterView();
				openLoginView();

			} catch (Exception e){
				user = null;
				registerView.displayErrorMessage(e.getMessage());
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
			openNewAppointmentView();
		}
	}
	
	class CreateAppointmentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			closeNewAppointmentView();
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
		registerView = new NewUser();
		registerView.addRegisterListener(new RegisterListener());
		registerView.addCancelButtonListener(new CancelRegistrationListener());
	}

	private void closeRegisterView() {
		registerView.dispose();
		registerView = null;
	}
	
	private void openCalendarView() {
		calendarView = new CalendarProgram();
		calendarView.addLogoutButtonListener(new LogoutListener());
	}
	
	private void closeCalendarView() {
		calendarView.dispose();
		calendarView = null;
		user = null;
	}
	
	private void openNewAppointmentView() {
		createAppointmentView = new NewEvent();
		createAppointmentView.addCreateButtonListener(new CreateAppointmentListener());
	}
		
	private void closeNewAppointmentView() {
		createAppointmentView.dispose();
		calendarView = null;
		user = null;
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
