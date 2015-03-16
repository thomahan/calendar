package view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import model.Appointment;
import model.Room;
import model.User;

import javax.swing.ListModel;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SeeChangesView extends JFrame {
	private JPanel panel;
	private JList<Appointment> appointmentListBox;
	private JList<User> userListBox;
	private DefaultListModel<User> userListModel;
	private DefaultListModel<Appointment> appointmentListModel;
	private JButton cancelButton;
	private JButton btnConfirm;

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
		
		userListModel = new DefaultListModel<User>();
		userListBox = new JList<User>(userListModel);
		userListBox.setBounds(291, 25, 248, 180);
		userListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		userListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(userListBox);
		
		cancelButton = new JButton("Close");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(139, 217, 117, 29);
		panel.add(cancelButton);
		
		JLabel lblAnswersToYour = new JLabel("Answers to your invitations");
		lblAnswersToYour.setBounds(291, 6, 248, 16);
		panel.add(lblAnswersToYour);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(10, 217, 117, 29);
		panel.add(btnConfirm);
	
		this.setVisible(true);
	}

	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		cancelButton.addActionListener(cancelButtonListener);
	}
	
	public void addConfirmButtonListener(ActionListener confirmButtonListener) {
		btnConfirm.addActionListener(confirmButtonListener);
	}
		
	public List<Appointment> getSelectedAppointments(){
		return appointmentListBox.getSelectedValuesList();
	}
	
	public List<User> getSelectedUsers() {
		return userListBox.getSelectedValuesList();
	}
	
	public void setAppointmentList(List<Appointment> appointmentList){
		appointmentListModel.clear();
		for (Appointment appointment : appointmentList) {
			appointmentListModel.addElement(appointment);
		}
	}
	
	public void setUserList(List<User> userList) {
		userListModel.clear();
		for (User user : userList) {
			userListModel.addElement(user);
		}
	}
}
