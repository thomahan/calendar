package view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.User;

@SuppressWarnings("serial")
public class UserInvitationView extends JFrame {
	private JPanel panel;
	private JList<User> userListBox;
	private DefaultListModel<User> userListModel;
	private JButton inviteButton;
	private JButton cancelButton;

	public static void main(String[] args) {
		new UserInvitationView();
	}

	public UserInvitationView() {
		this.setBounds(300, 300, 300, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		JLabel lblInviteOtherPersons = new JLabel("Invite other persons using the menu");
		lblInviteOtherPersons.setBounds(6, 6, 261, 16);
		panel.add(lblInviteOtherPersons);

		userListModel = new DefaultListModel<User>();
		userListBox = new JList<User>(userListModel);
		userListBox.setBounds(10, 25, 248, 180);
		userListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		userListBox.setLayoutOrientation(JList.VERTICAL);
		userListBox.setAutoscrolls(true);

		JScrollPane scrollPane = new JScrollPane(userListBox);
		scrollPane.setBounds(10, 25, 248, 180);
		panel.add(scrollPane);
	
		inviteButton = new JButton("Invite");
		inviteButton.setBounds(6, 221, 117, 29);
		panel.add(inviteButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(151, 221, 117, 29);
		panel.add(cancelButton);
	
		this.setVisible(true);
	}
		
	public void addConfirmButtonListener(ActionListener confirmButtonListener) {
		inviteButton.addActionListener(confirmButtonListener);
	}

	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		cancelButton.addActionListener(cancelButtonListener);
	}
		
	public List<User> getInvitedPersons(){
		return userListBox.getSelectedValuesList();
	}

	public void setUserList(List<User> userList) {
		userListModel.clear();
		for (User user : userList) {
			userListModel.addElement(user);
		}
	}

}
