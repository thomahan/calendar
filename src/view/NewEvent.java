package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewEvent extends JFrame {
	// TODO: Add field for alarmTime, roomId. Add cancel button?

	private final String FRAME_TITLE = "Create new appointment";
	private JTextField appointmentTitleField;
	private JTextField startTimeField;
	private JTextField endTimeField;
	private JTextField locationField;
	private JTextArea descriptionArea;
	private JButton createButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		NewEvent window = new NewEvent();
	}

	/**
	 * Create the application.
	 */
	public NewEvent() {
		this.setTitle(FRAME_TITLE);
		this.setBounds(100, 100, 450, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 316);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(6, 6, 118, 304);
		panel.add(labelPanel);
		labelPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(6, 6, 61, 16);
		labelPanel.add(lblTitle);
		
		JLabel lblDate = new JLabel("Start");
		lblDate.setBounds(6, 51, 61, 16);
		labelPanel.add(lblDate);
		
		JLabel lblEnddate = new JLabel("End");
		lblEnddate.setBounds(6, 90, 61, 16);
		labelPanel.add(lblEnddate);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(6, 131, 61, 16);
		labelPanel.add(lblLocation);
		
		JLabel lblDescriptions = new JLabel("Description");
		lblDescriptions.setBounds(6, 175, 93, 16);
		labelPanel.add(lblDescriptions);
		
		createButton = new JButton("Create");
		createButton.setBounds(6, 219, 110, 29);
		labelPanel.add(createButton);
			
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(6, 260, 110, 29);
		labelPanel.add(cancelButton);


		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(136, 6, 296, 254);
		panel.add(inputPanel);
		inputPanel.setLayout(null);
		
		appointmentTitleField = new JTextField();
		appointmentTitleField.setBounds(6, 6, 134, 28);
		inputPanel.add(appointmentTitleField);
		appointmentTitleField.setColumns(10);
		
		startTimeField = new JTextField();
		startTimeField.setBounds(6, 46, 134, 28);
		inputPanel.add(startTimeField);
		startTimeField.setColumns(10);
		
		endTimeField = new JTextField();
		endTimeField.setBounds(6, 86, 134, 28);
		inputPanel.add(endTimeField);
		endTimeField.setColumns(10);
		
		locationField = new JTextField();
		locationField.setBounds(6, 126, 134, 28);
		inputPanel.add(locationField);
		locationField.setColumns(10);
		
		descriptionArea = new JTextArea();
		descriptionArea.setBounds(6, 166, 284, 82);
		inputPanel.add(descriptionArea);

		this.setVisible(true);
	}
	
			
	public void addCreateButtonListener(ActionListener createButtonListener) {
		createButton.addActionListener(createButtonListener);
	}
			
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		cancelButton.addActionListener(cancelButtonListener);
	}

	public String getStartTime() {
		return startTimeField.getText();
	}

	public String getEndTime() {
		return endTimeField.getText();
	}

	public String getAlarmTime() {
		return "";//alarmTimeField.getText();
	}
			
	public String getAppointmentTitle() {
		return appointmentTitleField.getText();
	}

	public String getDescription() {
		return descriptionArea.getText();
	}

	public String getAppointmentLocation() {
		return locationField.getText();
	}
	
	public int getRoomId() {
		return 0;
	}
	
	public void setStartTime(String startTime) {
		startTimeField.setText(startTime);
	}
	
	public void setEndTime(String endTime) {
		endTimeField.setText(endTime);
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void displayAppointmentCreationMessage(String title) {
		JOptionPane.showMessageDialog(this, "Appointment '"+title+"' created.", "Appointment creation successful!", JOptionPane.INFORMATION_MESSAGE);
	}

}
