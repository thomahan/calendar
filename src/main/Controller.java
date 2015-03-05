package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Room;
import model.User;
import view.CalendarProgram;
import view.LogIn;
import view.NewUser;
import db.UserDBC;

public class Controller {
	private LogIn loginView;
	private CalendarProgram calendarView;
	private NewUser registerView;
	private User user;
	private static ArrayList<Room> roomlist = new ArrayList<Room>();
	
	public Controller() {
		openLoginView();
	}
	
	class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String username;
			String password;
			
			try {
				username = loginView.getUsername();
				password = loginView.getPassword();
				
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

	class RegisterButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			closeLoginView();
			openRegisterView();
		}
	}

	class RegisterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String username;
			String firstName;
			String lastName;
			String password;
			String passwordConfirm;
			User user;

			try{
				username = registerView.getUsername();
				firstName = registerView.getFirstName();
				lastName = registerView.getLastName();
				password = registerView.getPassword();
				passwordConfirm = registerView.getPasswordConfirm();

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

	class CancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			closeRegisterView();
			openLoginView();
		}
	}

	class LogoutButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			closeCalendarView();
			openLoginView();
		}
	}

	private void openLoginView() {
		this.loginView = new LogIn();
		this.loginView.addLoginListener(new LoginListener());
		this.loginView.addRegisterButtonListener(new RegisterButtonListener());
	}

	
	private void closeLoginView() {
		loginView.dispose();
		loginView = null;
	}
	
	
	private void openRegisterView() {
		this.registerView = new NewUser();
		this.registerView.addRegisterListener(new RegisterListener());
		this.registerView.addCancelButtonListener(new CancelButtonListener());
	}

	private void closeRegisterView() {
		registerView.dispose();
		registerView = null;
	}
	
	private void openCalendarView() {
		calendarView = new CalendarProgram();
		this.calendarView.addLogoutButtonListener(new LogoutButtonListener());
	}
	
	private void closeCalendarView() {
		calendarView.dispose();
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
