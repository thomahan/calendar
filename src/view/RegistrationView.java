package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class RegistrationView extends JFrame {
	private JPanel headerPanel;
	private JTextField usernameField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JButton registerButton;
	private JButton cancelButton;

	private final String FRAME_TITLE = "User Registration";

	public static void main(String[] args) {
		RegistrationView window = new RegistrationView();
		window.setVisible(true);
	}

	public RegistrationView() {
		this.setBounds(200, 200, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle(FRAME_TITLE);
		this.getContentPane().setLayout(null);
			
		headerPanel = new JPanel();
		headerPanel.setBounds(96, 6, 244, 38);
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.getContentPane().add(headerPanel);
		
		JLabel txtrPleaseFillIn = new JLabel("Please fill in required information");
		headerPanel.add(txtrPleaseFillIn);
	
		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(69, 54, 123, 173);
		labelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		this.getContentPane().add(labelPanel);
		
		JLabel usernameLabel = new JLabel("Username");
		labelPanel.add(usernameLabel);
		
		JLabel firstNameLabel = new JLabel("First name");
		labelPanel.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last name");
		labelPanel.add(lastNameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		labelPanel.add(passwordLabel);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm password");
		labelPanel.add(confirmPasswordLabel);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(186, 54, 154, 173);
		inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.getContentPane().add(inputPanel);
		
		usernameField = new JTextField(10);
		inputPanel.add(usernameField);
		
		firstNameField = new JTextField(10);
		inputPanel.add(firstNameField);
		
		lastNameField = new JTextField(10);
		inputPanel.add(lastNameField);
		
		passwordField = new JPasswordField(10);
		inputPanel.add(passwordField);
		
		confirmPasswordField = new JPasswordField(10);
		inputPanel.add(confirmPasswordField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(124, 236, 273, 36);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.getContentPane().add(buttonPanel);
		
		registerButton = new JButton("Register");
		buttonPanel.add(registerButton);
		
		cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);

		this.setVisible(true);
	}
	
	public void addRegisterButtonListener(ActionListener listenerForRegisterButton) {
		registerButton.addActionListener(listenerForRegisterButton);
	}
	
	public void addCancelButtonListener(ActionListener listenerForCancelButton) {
		cancelButton.addActionListener(listenerForCancelButton);
	}		

	public String getUsername() {
		return usernameField.getText();
	}
				
	public String getFirstName() {
		return firstNameField.getText();
	}
				
	public String getLastName() {
		return lastNameField.getText();
	}
	
	public String getPassword() {
		String password = "";
		for (char c : this.passwordField.getPassword()) {
			password = password + c;
		}
		return password;
	}
		
	public String getPasswordConfirm() {
		String password = "";
		for (char c : this.confirmPasswordField.getPassword()) {
			password = password + c;
		}
		return password;
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void displayRegisterMessage(String username) {
		JOptionPane.showMessageDialog(this, "'"+username+"' successfully registered.", "Registration complete!", JOptionPane.INFORMATION_MESSAGE);
	}

}
