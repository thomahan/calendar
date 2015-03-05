package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class NewEvent {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEvent window = new NewEvent();
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
	public NewEvent() {
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
		panel.setBounds(6, 6, 438, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 6, 118, 254);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 6, 61, 16);
		panel_1.add(lblName);
		
		JLabel lblDate = new JLabel("Startdate");
		lblDate.setBounds(6, 51, 61, 16);
		panel_1.add(lblDate);
		
		JLabel lblEnddate = new JLabel("Enddate");
		lblEnddate.setBounds(6, 90, 61, 16);
		panel_1.add(lblEnddate);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(6, 131, 61, 16);
		panel_1.add(lblRoom);
		
		JLabel lblDescriptions = new JLabel("Descriptions");
		lblDescriptions.setBounds(6, 175, 93, 16);
		panel_1.add(lblDescriptions);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setAction(action);
		btnConfirm.setBounds(6, 219, 117, 29);
		panel_1.add(btnConfirm);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(136, 6, 296, 254);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 6, 134, 28);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 46, 134, 28);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(6, 86, 134, 28);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(6, 126, 134, 28);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(6, 166, 284, 82);
		panel_2.add(textArea);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Confirm");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
