package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewUser extends JFrame {
	private final String FRAME_TITLE = "Calendar Program Registration";
	private JPanel panel_1;
	private JTextField usernameField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	private JButton registerButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		NewUser window = new NewUser();
		window.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public NewUser() {
		this.setTitle(FRAME_TITLE);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(186, 54, 154, 173);
		this.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		usernameField = new JTextField();
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		firstNameField = new JTextField();
		panel.add(firstNameField);
		firstNameField.setColumns(10);
		
		lastNameField = new JTextField();
		panel.add(lastNameField);
		lastNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		passwordConfirmField = new JPasswordField();
		panel.add(passwordConfirmField);
		passwordConfirmField.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(96, 6, 244, 38);
		this.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel txtrPleaseFillIn = new JLabel();
		txtrPleaseFillIn.setText("Please fill in required information");
		panel_1.add(txtrPleaseFillIn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(69, 54, 123, 173);
		this.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel txtrUsername = new JLabel();
		txtrUsername.setBackground(SystemColor.window);
		txtrUsername.setText("Username");
		panel_2.add(txtrUsername);
		
		JLabel txtrGivenName = new JLabel();
		txtrGivenName.setBackground(SystemColor.window);
		txtrGivenName.setText("Given name");
		panel_2.add(txtrGivenName);
		
		JLabel txtrLastName = new JLabel();
		txtrLastName.setBackground(SystemColor.window);
		txtrLastName.setText("Last name");
		panel_2.add(txtrLastName);
		
		JLabel txtrPassword = new JLabel();
		txtrPassword.setBackground(SystemColor.window);
		txtrPassword.setText("Password");
		panel_2.add(txtrPassword);
		
		JLabel txtrConfirmPassword = new JLabel();
		txtrConfirmPassword.setBackground(SystemColor.window);
		txtrConfirmPassword.setText("Confirm password");
		panel_2.add(txtrConfirmPassword);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(124, 236, 273, 36);
		this.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		registerButton = new JButton("Register");
		panel_3.add(registerButton);
		
		cancelButton = new JButton("Cancel");
		panel_3.add(cancelButton);

		this.setVisible(true);
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
		for (char c : this.passwordConfirmField.getPassword()) {
			password = password + c;
		}
		return password;
	}

	public void addRegisterListener(ActionListener listenerForRegisterButton) {
		registerButton.addActionListener(listenerForRegisterButton);
	}
	
	public void addCancelButtonListener(ActionListener listenerForCancelButton) {
		cancelButton.addActionListener(listenerForCancelButton);
	}
	
	public void displayRegisterMessage(String username) {
		JOptionPane.showMessageDialog(this, "'"+username+"' successfully created.", "Registration successful!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
