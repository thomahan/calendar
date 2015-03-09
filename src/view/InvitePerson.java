package view;

import java.awt.Dimension;
import java.awt.EventQueue;
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

public class InvitePerson {

	private static final ListModel String = null;
	private JFrame frame;
	private JButton btnCancel, btnConfirm;
	private JList list;
	private JPanel panel;
	private DefaultListModel userListModel;
	private JList userListBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvitePerson window = new InvitePerson();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InvitePerson() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		frame.getContentPane().add(panel);
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
		userListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(userListBox);
		userListModel.addElement("a");
		userListModel.addElement("b");
		userListModel.addElement("c");

		
	}
	
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		btnCancel.addActionListener(cancelButtonListener);
	}
	
	public void addConfirmButtonListener(ActionListener confirmButtonListener) {
		btnConfirm.addActionListener(confirmButtonListener);
	}
	
	public void setUserList(ArrayList<User> users) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (User user : users) {
			listModel.addElement(user.toString());
		}
		list = new JList<>(listModel);
		frame.add(list);
	}

	
}
