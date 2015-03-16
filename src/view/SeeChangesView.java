package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import model.Appointment;
import model.CancelNotification;
import model.User;

@SuppressWarnings("serial")
public class SeeChangesView extends JFrame {
	private JPanel panel;
	private JList<Appointment> appointmentListBox;
	private JList<CancelNotification> cancelNotificationListBox;
	private DefaultListModel<CancelNotification> cancelNotificationListModel;
	private DefaultListModel<Appointment> appointmentListModel;
	private JButton closeButton;
	private JButton btnHideUser;
	private JButton btnHideAppointment;

	public static void main(String[] args) {
		new SeeChangesView();
	}

	public SeeChangesView() {
		this.setBounds(300, 300, 572, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 560, 266);
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		JLabel lblInviteOtherPersons = new JLabel("Edited appointments");
		lblInviteOtherPersons.setBounds(10, 6, 257, 16);
		panel.add(lblInviteOtherPersons);

		appointmentListModel = new DefaultListModel<Appointment>();
		appointmentListBox = new JList<Appointment>(appointmentListModel);
		appointmentListBox.setBounds(10, 25, 248, 180);
		appointmentListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		appointmentListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(appointmentListBox);
		
		cancelNotificationListModel = new DefaultListModel<CancelNotification>();
		cancelNotificationListBox = new JList<CancelNotification>(cancelNotificationListModel);
		cancelNotificationListBox.setBounds(291, 25, 248, 180);
		cancelNotificationListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		cancelNotificationListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(cancelNotificationListBox);
		
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		closeButton.setBounds(223, 217, 117, 29);
		panel.add(closeButton);
		
		JLabel lblAnswersToYour = new JLabel("Answers to your invitations");
		lblAnswersToYour.setBounds(291, 6, 248, 16);
		panel.add(lblAnswersToYour);
		
		btnHideUser = new JButton("Hide");
		btnHideUser.setBounds(10, 217, 117, 29);
		panel.add(btnHideUser);
		
		btnHideAppointment = new JButton("Hide");
		btnHideAppointment.setBounds(422, 217, 117, 29);
		panel.add(btnHideAppointment);
	
		this.setVisible(true);
	}

	public void addCloseButtonListener(ActionListener closeButtonListener) {
		closeButton.addActionListener(closeButtonListener);
	}
	
	public void addHideUserButtonListener(ActionListener hideUserListener) {
		btnHideUser.addActionListener(hideUserListener);
	}
	
	public void addHideAppointmentButtonListener(ActionListener hideAppointmentListener) {
		btnHideAppointment.addActionListener(hideAppointmentListener);
	}
		
	public List<Appointment> getSelectedAppointments(){
		return appointmentListBox.getSelectedValuesList();
	}
	
	public List<CancelNotification> getSelectedUsers() {
		return cancelNotificationListBox.getSelectedValuesList();
	}
	
	public void setAppointmentList(List<Appointment> appointmentList){
		appointmentListModel.clear();
		for (Appointment appointment : appointmentList) {
			appointmentListModel.addElement(appointment);
		}
	}
	
	public void setCancelNotificationList(List<CancelNotification> cancelNotificationList) {
		cancelNotificationListModel.clear();
		for (CancelNotification cancelNotification : cancelNotificationList) {
			cancelNotificationListModel.addElement(cancelNotification);
		}
	}

	public List<Appointment> getSelectedAppointmentList() {
		return appointmentListBox.getSelectedValuesList();
	}

	public List<CancelNotification> getSelectedCancelNotificationList() {
		return cancelNotificationListBox.getSelectedValuesList();
	}

}
