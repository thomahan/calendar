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

import model.Room;

@SuppressWarnings("serial")
public class RoomReservationView extends JFrame {
	private JPanel panel;
	private JList<Room> roomListBox;
	private DefaultListModel<Room> roomListModel;
	private JButton reserveButton;
	private JButton cancelButton;

	public static void main(String[] args) {
		new RoomReservationView();
	}

	public RoomReservationView() {
		this.setBounds(300, 300, 300, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		JLabel lblInviteOtherPersons = new JLabel("Choose one room");
		lblInviteOtherPersons.setBounds(6, 6, 261, 16);
		panel.add(lblInviteOtherPersons);

		roomListModel = new DefaultListModel<Room>();
		roomListBox = new JList<Room>(roomListModel);
		roomListBox.setBounds(10, 25, 248, 180);
		roomListBox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		roomListBox.setLayoutOrientation(JList.VERTICAL);
		roomListBox.setAutoscrolls(true);

		JScrollPane scrollPane = new JScrollPane(roomListBox);
		scrollPane.setBounds(10, 25, 248, 180);
		panel.add(scrollPane);
	
		reserveButton = new JButton("Reserve");
		reserveButton.setBounds(6, 221, 117, 29);
		panel.add(reserveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(151, 221, 117, 29);
		panel.add(cancelButton);
	
		this.setVisible(true);
	}
		
	public void addReserveButtonListener(ActionListener reserveButtonListener) {
		reserveButton.addActionListener(reserveButtonListener);
	}

	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		cancelButton.addActionListener(cancelButtonListener);
	}
		
	public Room getSelectedRoom(){
		return (Room) roomListBox.getSelectedValue();
	}

	public void setRoomList(List<Room> roomList) {
		roomListModel.clear();
		for (Room room : roomList) {
			roomListModel.addElement(room);
		}
	}

}
