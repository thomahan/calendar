package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public Controller(LogIn loginView) {
		this.loginView = loginView;
		this.loginView.addLoginListener(new LoginListener());
		this.loginView.addRegisterButtonListener(new RegisterButtonListener());
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

				loginView.dispose();
				loginView = null;
				calendarView = new CalendarProgram();
				calendarView.main(null);
				

			} catch (Exception e) {
				user = null;
				loginView.displayErrorMessage(e.getMessage());
			}

		}
		
	}

	class RegisterButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			loginView.dispose();
			loginView = null;
			registerView = new NewUser();
			registerView.addRegisterListener(new RegisterListener());
			registerView.addCancelButtonListener(new CancelButtonListener());
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

				registerView.dispose();
				registerView = null;
				loginView = new LogIn();

			} catch (Exception e){
				user = null;
				registerView.displayErrorMessage(e.getMessage());
			}

		}
		
	}

	class CancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Successful listen!");
			registerView.dispose();
			registerView = null;
			loginView =  new LogIn();
			loginView.addLoginListener(new LoginListener());
			loginView.addRegisterButtonListener(new RegisterButtonListener());
		}
	}

}
