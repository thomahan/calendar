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
				
				user = UserDBC.getUser(username);
				
				if (user == null) {
					throw new Exception("Username does not exist");
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
			registerView.main(null);
		
		}
	}
}
