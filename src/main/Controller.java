package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.User;
import view.ProofOfConceptView;
import db.UserDBC;

public class Controller {
	private ProofOfConceptView pocView;
	private User user;
	
	public Controller(ProofOfConceptView pocView) {
		this.pocView = pocView;
		this.pocView.addLoginListener(new LoginListener());
	}
	
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String username;
			String password;
			
			try {
				username = pocView.getUsername();
				password = pocView.getPassword();
				
				user = UserDBC.getUser(username);
				
				if (user == null) {
					throw new Exception("Username does not exist");
				}else if (!user.isPasswordCorrect(password)) {
					throw new Exception("Password incorrect.");
				}
				
				pocView.displayLoginMessage();

			} catch (Exception e) {
				user = null;
				pocView.displayErrorMessage(e.getMessage());
			}

		}
		
	}

}
