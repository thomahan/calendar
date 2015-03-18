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
public class SeeParticipantsView extends JFrame {
	private JPanel panel;
	private JList<User> participantListBox;
	private DefaultListModel<User> participantListModel;
	private JButton closeButton;
	private JButton removeButton;

	public static void main(String[] args) {
		new SeeParticipantsView();
	}

	public SeeParticipantsView() {
		this.setBounds(300, 300, 300, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		JLabel lblParticipants = new JLabel("List of participants:");
		lblParticipants.setBounds(6, 6, 261, 16);
		panel.add(lblParticipants);

		participantListModel = new DefaultListModel<User>();
		participantListBox = new JList<User>(participantListModel);
		participantListBox.setBounds(10, 25, 248, 180);
		participantListBox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		participantListBox.setLayoutOrientation(JList.VERTICAL);
		participantListBox.setAutoscrolls(true);
		participantListBox.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(participantListBox);
		scrollPane.setBounds(10, 25, 248, 180);
		panel.add(scrollPane);
		
		removeButton = new JButton("Remove");
		removeButton.setBounds(20, 221, 117, 29);
		removeButton.setVisible(false);
		removeButton.setEnabled(false);
		panel.add(removeButton);

		closeButton = new JButton("Close");
		closeButton.setBounds(151, 221, 117, 29);
		panel.add(closeButton);
		
		this.setVisible(true);
	}
			
	public void addRemoveButtonListener(ActionListener removeButtonListener) {
		removeButton.addActionListener(removeButtonListener);
	}
		
	public void addCloseButtonListener(ActionListener closeButtonListener) {
		closeButton.addActionListener(closeButtonListener);
	}
	
	public void addBtnRemoveListener(ActionListener btnRemoveListener) {
		removeButton.addActionListener(btnRemoveListener);
	}
		
	
	public void setParticipantList(List<User> participantList) {
		participantListModel.clear();
		for (User user : participantList) {
			participantListModel.addElement(user);
		}
	}
			
	public List<User> getRemovedParticipantList(){
		return participantListBox.getSelectedValuesList();
	}

	public void setEnableRemoveParticipant(boolean hasRemovePermission) {
		removeButton.setVisible(hasRemovePermission);
		removeButton.setEnabled(hasRemovePermission);
		participantListBox.setEnabled(hasRemovePermission);
	}
}
