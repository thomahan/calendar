package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JList;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private final Action action = new SwingAction();
	private JPasswordField passwordField;
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setBounds(26, 11, 62, 16);
		txtrUsername.setText("Username");
		panel.add(txtrUsername);
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 5, 134, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setBounds(26, 44, 59, 16);
		txtrPassword.setText("Password");
		panel.add(txtrPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 38, 134, 28);
		panel.add(passwordField);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setAction(action);
		btnLogIn.setBounds(141, 78, 117, 29);
		panel.add(btnLogIn);
		
		JTextArea txtrNotRegisteredYet = new JTextArea();
		txtrNotRegisteredYet.setText("Not registered yet? Register by clicking the button.");
		txtrNotRegisteredYet.setBounds(63, 172, 320, 21);
		panel.add(txtrNotRegisteredYet);
		
		JButton btnNewUser = new JButton("New user");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewUser.setAction(action_1);
		btnNewUser.setBounds(141, 205, 117, 29);
		panel.add(btnNewUser);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Log in");
			putValue(SHORT_DESCRIPTION, "Validates username and password and opens calendar if information is correct");
		}
		public void actionPerformed(ActionEvent e) {
			CalendarWindow cp = new CalendarWindow();
			cp.main(null);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "New User");
			putValue(SHORT_DESCRIPTION, "Opens window for new user");
		}
		public void actionPerformed(ActionEvent e) {
			NewUser nu = new NewUser();
			nu.setVisible(true);
		}
	}
}
