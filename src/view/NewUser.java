package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea txtrPassword;
	private JTextArea txtrConfirmPassword;
	private JTextArea txtrLastName;
	private JTextArea txtrGivenName;
	private JTextArea txtrUsername;
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
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
	public NewUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Confirm information");
		btnNewButton.setBounds(52, 210, 159, 38);
		btnNewButton.setAction(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JTextArea txtrPleaseFillInn = new JTextArea();
		txtrPleaseFillInn.setText("Please fill inn correct information");
		txtrPleaseFillInn.setBounds(116, 6, 210, 16);
		contentPane.add(txtrPleaseFillInn);
		
		JButton btnExit = new JButton("Cancel");
		btnExit.setAction(action_1);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setBounds(222, 210, 178, 38);
		contentPane.add(btnExit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 129, 180, 23);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(142, 156, 178, 22);
		contentPane.add(passwordField_1);
		
		textField = new JTextField("");
		textField.setBounds(140, 100, 180, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("");
		textField_1.setBounds(140, 75, 180, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField("");
		textField_2.setBounds(140, 50, 180, 23);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		txtrPassword = new JTextArea();
		txtrPassword.setText("Password");
		txtrPassword.setBounds(65, 133, 65, 16);
		contentPane.add(txtrPassword);
		
		txtrConfirmPassword = new JTextArea();
		txtrConfirmPassword.setText("Confirm password");
		txtrConfirmPassword.setBounds(10, 159, 120, 16);
		contentPane.add(txtrConfirmPassword);
		
		txtrLastName = new JTextArea();
		txtrLastName.setText("Last name");
		txtrLastName.setBounds(63, 104, 65, 19);
		contentPane.add(txtrLastName);
		
		txtrGivenName = new JTextArea();
		txtrGivenName.setText("Given name");
		txtrGivenName.setBounds(52, 79, 78, 19);
		contentPane.add(txtrGivenName);
		
		txtrUsername = new JTextArea();
		txtrUsername.setText("Username");
		txtrUsername.setBounds(59, 54, 65, 16);
		contentPane.add(txtrUsername);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Confirm information");
			putValue(SHORT_DESCRIPTION, "By pressing this button you confirm information, validate information and proceeds to the calendarwindow if everything is OK.");
		}
		public void actionPerformed(ActionEvent e) {
			CalendarWindow cp = new CalendarWindow();
			cp.main(null);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Cancel");
		putValue(SHORT_DESCRIPTION, "Quits the process and returns to loginwindow");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}
}
