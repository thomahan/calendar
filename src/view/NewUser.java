package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import db.UserDBC;
import main.PasswordHash;
import model.User;

public class NewUser {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPanel panel_1;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser window = new NewUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(186, 54, 154, 173);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(96, 6, 244, 38);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextArea txtrPleaseFillIn = new JTextArea();
		txtrPleaseFillIn.setText("Please fill in required information");
		panel_1.add(txtrPleaseFillIn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(69, 54, 123, 173);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setLineWrap(true);
		txtrUsername.setBackground(SystemColor.window);
		txtrUsername.setText("Username");
		panel_2.add(txtrUsername);
		
		JTextArea txtrGivenName = new JTextArea();
		txtrGivenName.setLineWrap(true);
		txtrGivenName.setBackground(SystemColor.window);
		txtrGivenName.setText("Given name");
		panel_2.add(txtrGivenName);
		
		JTextArea txtrLastName = new JTextArea();
		txtrLastName.setLineWrap(true);
		txtrLastName.setBackground(SystemColor.window);
		txtrLastName.setText("Last name");
		panel_2.add(txtrLastName);
		
		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setLineWrap(true);
		txtrPassword.setBackground(SystemColor.window);
		txtrPassword.setText("Password");
		panel_2.add(txtrPassword);
		
		JTextArea txtrConfirmPassword = new JTextArea();
		txtrConfirmPassword.setLineWrap(true);
		txtrConfirmPassword.setBackground(SystemColor.window);
		txtrConfirmPassword.setText("Confirm password");
		panel_2.add(txtrConfirmPassword);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(124, 236, 273, 36);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setAction(action);
		panel_3.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setAction(action_1);
		panel_3.add(btnCancel);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Confirm");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String userName = textField.getText().toLowerCase();
			String givenName = textField_1.getText();
			String lastName = textField_2.getText();
			String password = textField_3.getText();
			String confirmPassword = textField_4.getText();
			try{
				if (password.equals(confirmPassword)){					
					User user = new User(givenName, lastName, userName, password);
					UserDBC.addUser(user);
					CalendarWindow cw = new CalendarWindow();
					cw.main(null);
				} else JOptionPane.showMessageDialog(frame, "Password does not match. Please try again!");
			}
			catch (IllegalArgumentException f){
				System.out.println("Hello mann");
				JOptionPane.showMessageDialog(frame, "Username already taken. Please try again!");
			}
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Quit");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
