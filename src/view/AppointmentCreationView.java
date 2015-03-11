package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import model.User;

public class AppointmentCreationView extends JFrame {
	// TODO: Add field for alarmTime, roomId. Add cancel button?

	private final String FRAME_TITLE = "Create new appointment";
	private JTextField appointmentDescriptionField;
	private JTextField startTimeField;
	private JTextField endTimeField;
	private JTextField locationField;
	private JTextArea descriptionArea;
	private JButton createButton;
	private JButton cancelButton;
	private JButton invitePersonButton;
	private JButton inviteGroupButton;
	private ArrayList<User> invitedUsers;
	private JButton btnChooseRoom;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AppointmentCreationView window = new AppointmentCreationView();
	}

	/**
	 * Create the application.
	 */
	public AppointmentCreationView() {
		this.setTitle(FRAME_TITLE);
		this.setBounds(200, 200, 450, 280);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 316);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(6, 6, 118, 304);
		panel.add(labelPanel);
		labelPanel.setLayout(null);
			
		JLabel lblDate = new JLabel("Start");
		lblDate.setBounds(6, 6, 61, 16);
		labelPanel.add(lblDate);
		
		JLabel lblEnddate = new JLabel("End");
		lblEnddate.setBounds(6, 51, 61, 16);
		labelPanel.add(lblEnddate);
			
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(6, 90, 61, 16);
		labelPanel.add(lblDescription);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(6, 131, 61, 16);
		labelPanel.add(lblLocation);

		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(136, 6, 296, 254);
		panel.add(inputPanel);
		inputPanel.setLayout(null);
		
		startTimeField = new JTextField();
		startTimeField.setBounds(6, 6, 134, 28);
		inputPanel.add(startTimeField);
		startTimeField.setColumns(10);
		
		endTimeField = new JTextField();
		endTimeField.setBounds(6, 46, 134, 28);
		inputPanel.add(endTimeField);
		endTimeField.setColumns(10);
			
		appointmentDescriptionField = new JTextField();
		appointmentDescriptionField.setBounds(6, 86, 134, 28);
		inputPanel.add(appointmentDescriptionField);
		appointmentDescriptionField.setColumns(10);

		locationField = new JTextField();
		locationField.setBounds(6, 126, 134, 28);
		inputPanel.add(locationField);
		locationField.setColumns(10);

		invitePersonButton = new JButton("Invite persons");
		invitePersonButton.setBounds(162, 7, 117, 29);
		inputPanel.add(invitePersonButton);
		
		inviteGroupButton= new JButton("Invite groups");
		inviteGroupButton.setBounds(162, 47, 117, 29);
		inputPanel.add(inviteGroupButton);
		
		btnChooseRoom = new JButton("Choose room");
		btnChooseRoom.setBounds(162, 127, 117, 29);
		inputPanel.add(btnChooseRoom);
			
		createButton = new JButton("Save");
		createButton.setBounds(15, 190, 110, 29);
		inputPanel.add(createButton);
			
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(162, 190, 110, 29);
		inputPanel.add(cancelButton);

		this.setVisible(true);
	}
	
			
	public void addCreateButtonListener(ActionListener createButtonListener) {
		createButton.addActionListener(createButtonListener);
	}
			
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		cancelButton.addActionListener(cancelButtonListener);
	}
	
	public void addInvitePersonButtonListener(ActionListener invitePersonButtonListener) {
		invitePersonButton.addActionListener(invitePersonButtonListener);
	}
	
	public void addInviteGroupButtonListener(ActionListener inviteGroupButtonListener) {
		inviteGroupButton.addActionListener(inviteGroupButtonListener);
	}
	
	public void addChooseRoomButtonListener(ActionListener chooseRoomButtonListener) {
		btnChooseRoom.addActionListener(chooseRoomButtonListener);
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
		
	public String getDescription() {
		return appointmentDescriptionField.getText();
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
	
	public void setAppointmentTitleField(String title) {
		appointmentDescriptionField.setText(title);
	}
	
	public void setLocationField(String location){
		locationField.setText(location);
	}
	
	public void setDescriptionArea(String description) {
		descriptionArea.setText(description);
	}
}
