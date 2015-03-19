package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AppointmentCreationView extends JFrame {
	private JTextField descriptionField;
	private JTextField startTimeField;
	private JTextField endTimeField;
	private JTextField locationField;
	private JTextField minSeatCountField;
	private JButton createButton;
	private JButton cancelButton;
	private JButton invitePersonButton;
	private JButton inviteGroupButton;
	private JButton btnChooseRoom;
	
	private final String FRAME_TITLE = "Create new appointment";
	private boolean isNewAppointment;

	public static void main(String[] args) {
		new AppointmentCreationView();
	}

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
		
		startTimeField = new JTextField(10);
		startTimeField.setBounds(6, 6, 134, 28);
		inputPanel.add(startTimeField);
		
		endTimeField = new JTextField(10);
		endTimeField.setBounds(6, 46, 134, 28);
		inputPanel.add(endTimeField);
			
		descriptionField = new JTextField(10);
		descriptionField.setBounds(6, 86, 134, 28);
		inputPanel.add(descriptionField);

		locationField = new JTextField(10);
		locationField.setBounds(6, 126, 134, 28);
		inputPanel.add(locationField);

		invitePersonButton = new JButton("Invite persons");
		invitePersonButton.setBounds(162, 7, 117, 29);
		inputPanel.add(invitePersonButton);
		
		inviteGroupButton= new JButton("Invite groups");
		inviteGroupButton.setBounds(162, 47, 117, 29);
		inputPanel.add(inviteGroupButton);
		
		JLabel minSeatCountLabel = new JLabel("Seats:");
		minSeatCountLabel.setBounds(180, 100, 117, 29);
		inputPanel.add(minSeatCountLabel);
	
		minSeatCountField = new JTextField();
		minSeatCountField.setBounds(230, 100, 50, 25);
		inputPanel.add(minSeatCountField);
	
		btnChooseRoom = new JButton("Choose room");
		btnChooseRoom.setBounds(162, 127, 117, 29);
		inputPanel.add(btnChooseRoom);
			
		createButton = new JButton("Create");
		createButton.setBounds(15, 190, 110, 29);
		inputPanel.add(createButton);
			
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(162, 190, 110, 29);
		inputPanel.add(cancelButton);

		this.setVisible(true);
		
		setNewAppointment(true);
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
		return descriptionField.getText();
	}

	public String getAppointmentLocation() {
		return locationField.getText();
	}
	
	public String getMinSeatCount() {
		return minSeatCountField.getText();
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
		JOptionPane.showMessageDialog(this, "Appointment '"+title+"' created.", "Success!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void displayAppointmentSaveMessage(String title) {
		JOptionPane.showMessageDialog(this, "Changes to '"+title+"' saved.", "Success!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void setAppointmentTitleField(String title) {
		descriptionField.setText(title);
	}
	
	public void setLocation(String location){
		locationField.setText(location);
	}
	
	public void setDescription(String description) {
		descriptionField.setText(description);
	}
	
	public void setNewAppointment(boolean isNewAppointment) {
		this.isNewAppointment = isNewAppointment;
	}
	
	public boolean isNewAppointment() {
		return isNewAppointment;
	}
	
	public void setCreateButtonText(String text) {
		createButton.setText(text);
	}
}
