package view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class ProofOfConceptView extends JFrame {
	
	private JTextField usernameField = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField(10);
	private JButton loginButton = new JButton("Log in");
	
	public ProofOfConceptView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);
	
		JPanel pocPanel = new JPanel();
	
		pocPanel.add(usernameField);
		pocPanel.add(passwordField);
		pocPanel.add(loginButton);
		
		this.add(pocPanel);
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
