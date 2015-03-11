package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import model.Room;
import model.User;

public class RoomReservationView extends JFrame {

	private static final ListModel String = null;
	private JButton btnCancel, btnConfirm;
	private JList list;
	private JPanel panel;
	private DefaultListModel<Room> roomListModel;
	private JList roomListBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		RoomReservationView chooseRoom = new RoomReservationView();
	}

	/**
	 * Create the application.
	 */
	public RoomReservationView() {
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblInviteOtherPersons = new JLabel("Choose one room");
		lblInviteOtherPersons.setBounds(6, 6, 261, 16);
		panel.add(lblInviteOtherPersons);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(6, 221, 117, 29);
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(151, 221, 117, 29);
		panel.add(btnCancel);
	
		roomListModel = new DefaultListModel();
		roomListBox = new JList(roomListModel);
		roomListBox.setBounds(10, 25, 248, 180);
		roomListBox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		roomListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.add(roomListBox);

		this.setVisible(true);
	}
	
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		btnCancel.addActionListener(cancelButtonListener);
	}
	
	public void addConfirmButtonListener(ActionListener confirmButtonListener) {
		btnConfirm.addActionListener(confirmButtonListener);
	}
	
	public void setRoomList(List<Room> roomList) {
		roomListModel.clear();
		for (Room room : roomList) {
			roomListModel.addElement(room);
		}
	}
	
	public Room getSelectedRoom(){
		return (Room) roomListBox.getSelectedValue();
	}
}
