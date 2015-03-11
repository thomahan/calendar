package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.Appointment;

@SuppressWarnings("serial")
public class CalendarProgram extends JFrame {
	// TODO: Add show hidden option
	
	private final String FRAME_TITLE = "Calendar Program";
	static int realYear, realMonth, realDay, currentYear, currentMonth;

	private static JTable table;
	private static DefaultTableModel mtblCalendar;
	private JScrollPane stblCalendar;
	private JButton logoutButton;
	private JButton newAppointmentButton;
	private JButton editButton;
	private JButton deleteButton, btnAccept, btnDecline, btnHide;
	private static JComboBox<String> comboBox;
	private static JLabel lblNewLabel;
	private JList<Appointment> dailyAppointmentListBox;
	private DefaultListModel<Appointment> dailyAppointmentListModel;

	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	private List<Appointment> dailyAppointmentList;
	private Date selectedDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new CalendarProgram();
	}

	/**
	 * Create the application.
	 */
	public CalendarProgram() {
		this.setTitle(FRAME_TITLE);
		this.setBounds(100, 100, 734, 424);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		//create controls
		mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
			
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(6, 6, 296, 341);
		this.getContentPane().add(leftPanel);
		leftPanel.setLayout(null);
		
		JPanel calendarControlPanel = new JPanel();
		calendarControlPanel.setBounds(6, 6, 284, 37);
		leftPanel.add(calendarControlPanel);
		calendarControlPanel.setLayout(null);
		
		JButton button = new JButton("<<");
		button.setAction(action_1);
		button.setBounds(112, 7, 45, 29);
		calendarControlPanel.add(button);
		
		JButton button_1 = new JButton(">>");
		button_1.setAction(action);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(150, 7, 45, 29);
		calendarControlPanel.add(button_1);
		
		comboBox = new JComboBox();
		comboBox.setBounds(195, 8, 83, 27);
		calendarControlPanel.add(comboBox);
		
		lblNewLabel = new JLabel("Month");
		lblNewLabel.setBounds(6, 12, 106, 16);
		calendarControlPanel.add(lblNewLabel);
		
		JPanel calendarDisplayPanel = new JPanel();
		calendarDisplayPanel.setBounds(6, 55, 284, 280);
		leftPanel.add(calendarDisplayPanel);
		calendarDisplayPanel.setLayout(null);
		
		table = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(table);
		calendarDisplayPanel.add(stblCalendar);
		stblCalendar.setBounds(6,6,272,268);
		
		//No resize/reorder
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		//set row/column count
		table.setRowHeight(38);
		mtblCalendar.setColumnCount(0);
		mtblCalendar.setRowCount(6);
		
		//Single cell selection
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Add headers to mtblCalendar
		String[] headers = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}; 
		for (int i=0; i<7; i++)
			mtblCalendar.addColumn(headers[i]);
		
		//Set background
		table.getParent().setBackground(table.getBackground()); 
		
		//No resize/reorder
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		//Get real month and year
		GregorianCalendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		realDay = cal.get(Calendar.DAY_OF_MONTH); //Get day of month
		realMonth = cal.get(Calendar.MONTH); //Get month of year
		realYear = cal.get(Calendar.YEAR); // Get year
		currentMonth = realMonth;
		currentYear = realYear;		
		
		//Populate JComboBox with a selection of years
		for (int i=realYear-10; i<=realYear+10; i++)
			comboBox.addItem(String.valueOf(i));

		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(314, 6, 280, 400);
		this.getContentPane().add(rightPanel);
		rightPanel.setLayout(null);
		
		JPanel topButtonPanel = new JPanel();
		topButtonPanel.setBounds(6, 6, 268, 37);
		rightPanel.add(topButtonPanel);
		topButtonPanel.setLayout(null);
		
		newAppointmentButton = new JButton("Add appointment");
		newAppointmentButton.setBounds(6, 6, 124, 29);
		topButtonPanel.add(newAppointmentButton);
		
		logoutButton = new JButton("Log out");
		logoutButton.setBounds(138, 6, 124, 29);
		topButtonPanel.add(logoutButton);
		
		JPanel appointmentPanel = new JPanel();
		appointmentPanel.setBounds(6, 55, 268, 400);
		rightPanel.add(appointmentPanel);
		appointmentPanel.setLayout(null);
		
		dailyAppointmentListModel = new DefaultListModel();
		dailyAppointmentListBox = new JList(dailyAppointmentListModel);
		dailyAppointmentListBox.setBounds(10, 10, 248, 260);
		dailyAppointmentListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dailyAppointmentListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		appointmentPanel.add(dailyAppointmentListBox);
		
		editButton = new JButton("Edit");
		editButton.setBounds(606, 67, 124, 29);
		getContentPane().add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(606, 108, 124, 29);
		getContentPane().add(deleteButton);
		
		btnAccept = new JButton("Accept");
		btnAccept.setBounds(606, 149, 124, 29);
		getContentPane().add(btnAccept);
		
		btnDecline = new JButton("Decline");
		btnDecline.setBounds(606, 190, 124, 29);
		getContentPane().add(btnDecline);
		
		btnHide = new JButton("Hide");
		btnHide.setBounds(606, 231, 124, 29);
		getContentPane().add(btnHide);

		//Add mouse listener
		table.addMouseListener(new MouseHandler());
		
		
		//Refresh calendar
		refreshCalendar(realMonth, realYear); //Refresh calendar
		
		
		comboBox.addActionListener(new cmbYear_Action());
		
		this.setVisible(true);
		selectedDate = new Date();
	}
	
	public static void refreshCalendar(int month, int year) {
		//Variables
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som; //Number Of Days, Start Of Month
	
		//Allow/disallow buttons
		comboBox.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
		lblNewLabel.setText(months[month]); //Refresh the month label at the top
		
		//Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++)
				mtblCalendar.setValueAt(null, i, j);	
		}
		
		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month,1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); //number of days of current month
		som = (cal.get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7 + 1; 
		
		//Draw calendar
		for (int i=1; i<=nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			mtblCalendar.setValueAt(i, row, column);
		}		
//		//Apply renderers: If so: create a tblCalendarRenderer-class which extends DefaultTableCellRenderer
//				table.setDefaultRenderer(table.getColumnClass(0), new tblCalendarRenderer());
//	}
//	
//	static class tblCalendarRenderer extends DefaultTableCellRenderer{
//		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
//			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
//			if (column == 5 || column == 6){ //Week-end
//				setBackground(new Color(255, 220, 220));
//			}
//			else{ //Week
//				setBackground(new Color(255, 255, 255));
//			}
//			if (value != null){
//				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
//					setBackground(new Color(220, 220, 255));
//				}
//			}
//			setBorder(null);
//			setForeground(Color.black);
//			return this;  
//		}
	}

	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, ">>");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11) {
				currentMonth = 0;
				currentYear += 1;
			}else {
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}

	}
	@SuppressWarnings("serial")
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "<<");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0){
				currentMonth = 11;
				currentYear -= 1;
			}else{
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	
	private class cmbYear_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comboBox.getSelectedItem() != null){
				String b = comboBox.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}	
	}
	
	
	private class MouseHandler implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.rowAtPoint(e.getPoint());
		    int col = table.columnAtPoint(e.getPoint());
			refreshEventWindow(getDate(row,col));
		}
		
		@SuppressWarnings("deprecation")
		private Date getDate(int row, int col) {
			if (table.getModel().getValueAt(row, col) != null) {
				int day = (Integer) table.getModel().getValueAt(row, col);
				Date clickedDay = new Date(currentYear - 1900, currentMonth, day);
				selectedDate = clickedDay;
				return clickedDay;			
			} else {
				return selectedDate;
			}
		}

		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	
	public void refreshEventWindow(Date date){
		dailyAppointmentList = new ArrayList<Appointment>();
	}
	
	public void addNewAppointmentButtonListener(ActionListener newAppointmentButtonListener) {
		newAppointmentButton.addActionListener(newAppointmentButtonListener);
	}
	
	public void addLogoutButtonListener(ActionListener logoutButtonListener) {
		logoutButton.addActionListener(logoutButtonListener);
	}
		
	public void addSelectDateListener(MouseListener selectDateListener) {
		table.addMouseListener(selectDateListener);
	}
	
	public void addSelectedAppointmentListener(MouseListener selectAppointmentListener) {
		dailyAppointmentListBox.addMouseListener(selectAppointmentListener);
	}
		
	public void addEditButtonListener(ActionListener editButtonListener) {
		editButton.addActionListener(editButtonListener);
	}
		
	public void addDeleteButtonListener(ActionListener deleteButtonListener) {
		deleteButton.addActionListener(deleteButtonListener);
	}
	
	public void addAcceptButtonListener(ActionListener acceptButtonListener) {
		btnAccept.addActionListener(acceptButtonListener);
	}
	
	public void addDeclineButtonListener(ActionListener declineButtonListener) {
		btnDecline.addActionListener(declineButtonListener);
	}
	
	public void addHideButtonListener(ActionListener hideButtonListener) {
		btnHide.addActionListener(hideButtonListener);
	}
	
	
	
	

	public Date getSelectDate() {
		return selectedDate;
	}
	public Appointment getSelectedAppointment() {
		return dailyAppointmentListBox.getSelectedValue();
	}
	
	public void setDailyAppointmentList(ArrayList<Appointment> dailyAppointmentList) {
		this.dailyAppointmentList = dailyAppointmentList;

		dailyAppointmentListModel.clear();
		if (!dailyAppointmentList.isEmpty()) {
			for (Appointment a : dailyAppointmentList) {
				dailyAppointmentListModel.addElement(a);
			}
		}
	}
}
