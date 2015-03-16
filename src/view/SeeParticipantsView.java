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
import model.User;

@SuppressWarnings("serial")
public class SeeParticipantsView extends JFrame {
	private JPanel panel;
	private JList<String> participantListBox;
	private DefaultListModel<String> participantListModel;
	private JButton closeButton;

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

		participantListModel = new DefaultListModel<String>();
		participantListBox = new JList<String>(participantListModel);
		participantListBox.setBounds(10, 25, 248, 180);
		participantListBox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		participantListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(participantListBox);
		
		closeButton = new JButton("Close");
		closeButton.setBounds(151, 221, 117, 29);
		panel.add(closeButton);
	
		this.setVisible(true);
	}
		
	public void addCloseButtonListener(ActionListener closeButtonListener) {
		closeButton.addActionListener(closeButtonListener);
	}
		
	
	public void setParticipantList(List<String> participantList) {
		participantListModel.clear();
		for (String user : participantList) {
			participantListModel.addElement(user);
		}
	}
}
