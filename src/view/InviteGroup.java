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

public class InviteGroup extends JFrame {

	private static final ListModel String = null;
	private JButton btnCancel, btnConfirm;
	private JList list;
	private JPanel panel;
	private DefaultListModel<User> groupListModel;
	private JList groupListBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		InvitePerson window = new InvitePerson();
	}

	/**
	 * Create the application.
	 */
	public InviteGroup() {
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblInviteOtherGroups = new JLabel("Invite other groups using the menu");
		lblInviteOtherGroups.setBounds(6, 6, 261, 16);
		panel.add(lblInviteOtherGroups);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(6, 221, 117, 29);
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(151, 221, 117, 29);
		panel.add(btnCancel);
	
		groupListModel = new DefaultListModel();
		groupListBox = new JList(groupListModel);
		groupListBox.setBounds(10, 25, 248, 180);
		groupListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		groupListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(groupListBox);

		this.setVisible(true);
	}
	
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		btnCancel.addActionListener(cancelButtonListener);
	}
	
	public void addConfirmButtonListener(ActionListener confirmButtonListener) {
		btnConfirm.addActionListener(confirmButtonListener);
	}
	
	public void setUserList(ArrayList<User> users) {
		groupListModel.clear();
		for (User user : users) {
			System.out.println(user);
			groupListModel.addElement(user);
		}
	}
	
	public List<User> getInvitedPersons(){
		return groupListBox.getSelectedValuesList();
	}	
}
