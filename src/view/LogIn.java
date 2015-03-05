package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LogIn extends JFrame {
	private final String FRAME_TITLE = "Calendar Program Login";
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton registerButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LogIn login = new LogIn();
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		this.setTitle(FRAME_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(119, 67, 218, 133);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel txtrUsername = new JLabel();
		txtrUsername.setBounds(5, 11, 62, 16);
		txtrUsername.setText("Username");
		panel.add(txtrUsername);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(72, 18, 1, 1);
		panel.add(desktopPane);
		
		usernameField = new JTextField();
		usernameField.setBounds(72, 5, 134, 28);
		usernameField.setColumns(10);
		panel.add(usernameField);
		
		JLabel txtrPassword = new JLabel();
		txtrPassword.setBounds(10, 44, 59, 16);
		txtrPassword.setText("Password");
		panel.add(txtrPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(72, 38, 134, 28);
		passwordField.setColumns(10);
		panel.add(passwordField);
		
		loginButton = new JButton("Log in");
		loginButton.setBounds(72, 75, 117, 29);
		panel.add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(72, 104, 117, 29);
		panel.add(registerButton);

		this.setVisible(true);
	}
		
	public void addLoginButtonListener(ActionListener loginButtonListener) {
		loginButton.addActionListener(loginButtonListener);
	}
	
	public void addRegisterButtonListener(ActionListener registerButtonListener) {
		registerButton.addActionListener(registerButtonListener);
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

	public void displayLoginMessage(String name) {
		JOptionPane.showMessageDialog(this, "Logged in as "+name+".", "Login successful!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
