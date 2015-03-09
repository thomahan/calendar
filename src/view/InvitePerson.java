package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import model.User;

public class InvitePerson extends JFrame {

	private static final ListModel String = null;
	private JButton btnCancel, btnConfirm;
	private JList list;
	private JPanel panel;
	private DefaultListModel<User> userListModel;
	private JList userListBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		InvitePerson window = new InvitePerson();
	}

	/**
	 * Create the application.
	 */
	public InvitePerson() {
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblInviteOtherPersons = new JLabel("Invite other persons using the menu");
		lblInviteOtherPersons.setBounds(6, 6, 261, 16);
		panel.add(lblInviteOtherPersons);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(6, 221, 117, 29);
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(151, 221, 117, 29);
		panel.add(btnCancel);
	
		userListModel = new DefaultListModel();
		userListBox = new JList(userListModel);
		userListBox.setBounds(10, 25, 248, 180);
		userListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		userListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(userListBox);

		this.setVisible(true);
	}
	
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		btnCancel.addActionListener(cancelButtonListener);
	}
	
	public void addConfirmButtonListener(ActionListener confirmButtonListener) {
		btnConfirm.addActionListener(confirmButtonListener);
	}
	
	public void setUserList(List<User> userList) {
		userListModel.clear();
		for (User user : userList) {
			System.out.println(user);
			userListModel.addElement(user);
		}
	}
	
	public List<User> getInvitedPersons(){
		return userListBox.getSelectedValuesList();
	}
	
}
