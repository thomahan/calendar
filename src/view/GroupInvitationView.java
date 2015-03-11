package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import model.Group;

@SuppressWarnings("serial")
public class GroupInvitationView extends JFrame {
	private JPanel panel;
	private JList<Group> groupListBox;
	private DefaultListModel<Group> groupListModel;
	private JButton cancelButton, inviteButton;

	public static void main(String[] args) {
		new UserInvitationView();
	}

	public GroupInvitationView() {
		this.setBounds(300, 300, 300, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		JLabel lblInviteOtherGroups = new JLabel("Invite other groups using the menu");
		lblInviteOtherGroups.setBounds(6, 6, 261, 16);
		panel.add(lblInviteOtherGroups);
	
		groupListModel = new DefaultListModel<Group>();
		groupListBox = new JList<Group>(groupListModel);
		groupListBox.setBounds(10, 25, 248, 180);
		groupListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		groupListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(groupListBox);
	
		inviteButton = new JButton("Invite");
		inviteButton.setBounds(6, 221, 117, 29);
		panel.add(inviteButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(151, 221, 117, 29);
		panel.add(cancelButton);
	
		this.setVisible(true);
	}
		
	public void addInviteButtonListener(ActionListener inviteButtonListener) {
		inviteButton.addActionListener(inviteButtonListener);
	}
	
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		cancelButton.addActionListener(cancelButtonListener);
	}

	public List<Group> getInvitedGroups(){
		return groupListBox.getSelectedValuesList();
	}	

	public void setUserList(ArrayList<Group> groupList) {
		groupListModel.clear();
		for (Group group : groupList) {
			System.out.println(group);
			groupListModel.addElement(group);
		}
	}
	
}
