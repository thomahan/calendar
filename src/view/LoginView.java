package view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginView extends JFrame {
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
	public LoginView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);
	
		JPanel loginPanel = new JPanel();
		this.add(loginPanel);

		usernameField = new JTextField(10);
		loginPanel.add(usernameField);

		passwordField = new JPasswordField(10);
		loginPanel.add(passwordField);

		loginButton = new JButton("Log in");
		loginPanel.add(loginButton);

		this.setVisible(true);
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPassword() {
		String password = "";
		for (char c : this.passwordField.getPassword()) {
			password = password + c;
		}
		return password;
	}

	public void addLoginListener(ActionListener listenerForLoginButton) {
		loginButton.addActionListener(listenerForLoginButton);
	}
	
	public void displayLoginMessage(String name) {
		JOptionPane.showMessageDialog(this, "You have successfully logged in to the system, "+name+". Congratulations!", "Login successful!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
