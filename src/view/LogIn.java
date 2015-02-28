package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class LogIn {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
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
	public LogIn() {
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
		panel.setBounds(119, 67, 218, 133);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setBounds(5, 11, 62, 16);
		txtrUsername.setText("Username");
		panel.add(txtrUsername);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(72, 18, 1, 1);
		panel.add(desktopPane);
		
		textField = new JTextField();
		textField.setBounds(72, 5, 134, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setBounds(10, 44, 59, 16);
		txtrPassword.setText("Password");
		panel.add(txtrPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(72, 38, 134, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setAction(action_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(72, 75, 117, 29);
		panel.add(btnNewButton);
		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.setAction(action);
		btnNewUser.setBounds(72, 104, 117, 29);
		panel.add(btnNewUser);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "New User");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			NewUser nu = new NewUser();
			nu.main(null);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Confirm");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			CalendarWindow cw = new CalendarWindow();
			cw.main(null);
		}
	}
}
