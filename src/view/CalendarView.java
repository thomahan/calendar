package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Appointment;

@SuppressWarnings("serial")
public class CalendarView extends JFrame {
	private static JLabel monthLabel, warningNextMonth, warningPrevMonth;
	private static JComboBox<String> yearComboBox;
	private static JTable table;
	private static DefaultTableModel mtblCalendar;
	private JScrollPane stblCalendar;
	
	private JButton previousMonthButton;
	private JButton nextMonthButton;

	private JButton newAppointmentButton;
	private JButton logoutButton;

	private DefaultListModel<Appointment> dailyAppointmentListModel;
	private JList<Appointment> dailyAppointmentListBox;

	private JButton editButton;
	private JButton acceptButton;
	private JButton declineButton;
	private JButton hideButton;
	private JButton deleteButton;
	private JButton seeParticipantsButton;
	private JButton seeChangesButton;

	private final Action nextMonthAction = new NextMonthAction();
	private final Action previousMonthAction = new PreviousMonthAction();

	private final String FRAME_TITLE = "Calendar Program";
	static int realYear, realMonth, realDay, currentYear, currentMonth;

	private Date selectedDate;

	public static void main(String[] args) {
		new CalendarView();
	}

	public CalendarView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(FRAME_TITLE);
		this.setBounds(100, 100, 747, 380);
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
		
		previousMonthButton = new JButton("<<");
		previousMonthButton.setBounds(112, 7, 45, 29);
		previousMonthButton.setAction(previousMonthAction);
		calendarControlPanel.add(previousMonthButton);
		
		nextMonthButton = new JButton(">>");
		nextMonthButton.setBounds(150, 7, 45, 29);
		nextMonthButton.setAction(nextMonthAction);
		calendarControlPanel.add(nextMonthButton);
		
		yearComboBox = new JComboBox<String>();
		yearComboBox.setBounds(195, 8, 83, 27);
		calendarControlPanel.add(yearComboBox);
		
		monthLabel = new JLabel();
		monthLabel.setBounds(6, 12, 106, 16);
		calendarControlPanel.add(monthLabel);
		
		warningNextMonth = new JLabel();
		warningNextMonth.setBounds(168, 0, 15, 15);
		calendarControlPanel.add(warningNextMonth);
		warningNextMonth.setText("*");
		warningNextMonth.setVisible(false);
		
		warningPrevMonth = new JLabel();
		warningPrevMonth.setBounds(132, 0, 15, 15);
		calendarControlPanel.add(warningPrevMonth);
		warningPrevMonth.setText("*");
		warningPrevMonth.setVisible(false);
		
		JPanel calendarDisplayPanel = new JPanel();
		calendarDisplayPanel.setBounds(6, 55, 284, 280);
		leftPanel.add(calendarDisplayPanel);
		calendarDisplayPanel.setLayout(null);
		
		table = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(table);
		stblCalendar.setBounds(6,6,272,268);
		calendarDisplayPanel.add(stblCalendar);
		
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
			yearComboBox.addItem(String.valueOf(i));

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
		
		dailyAppointmentListModel = new DefaultListModel<Appointment>();
		dailyAppointmentListBox = new JList<Appointment>(dailyAppointmentListModel);
		dailyAppointmentListBox.setBounds(10, 10, 248, 260);
		dailyAppointmentListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dailyAppointmentListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		dailyAppointmentListBox.setAutoscrolls(true);

		JScrollPane scrollPane = new JScrollPane(dailyAppointmentListBox);
		scrollPane.setBounds(10, 10, 248, 260);
		appointmentPanel.add(scrollPane);
		
		editButton = new JButton("Edit");
		editButton.setBounds(606, 67, 124, 29);
		editButton.setEnabled(false);
		getContentPane().add(editButton);

		acceptButton = new JButton("Accept");
		acceptButton.setBounds(606, 108, 124, 29);
		acceptButton.setEnabled(false);
		getContentPane().add(acceptButton);
		
		declineButton = new JButton("Decline");
		declineButton.setBounds(606, 149, 124, 29);
		declineButton.setEnabled(false);
		getContentPane().add(declineButton);
		
		hideButton = new JButton("Hide");
		hideButton.setBounds(606, 190, 124, 29);
		hideButton.setEnabled(false);
		getContentPane().add(hideButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(606, 231, 124, 29);
		deleteButton.setEnabled(false);
		getContentPane().add(deleteButton);
	
		seeParticipantsButton = new JButton("See participants");
		seeParticipantsButton.setBounds(606, 272, 124, 29);
		seeParticipantsButton.setEnabled(false);
		getContentPane().add(seeParticipantsButton);
		
		seeChangesButton = new JButton("See changes");
		seeChangesButton.setBackground(UIManager.getColor("Button.select"));
		seeChangesButton.setBounds(606, 313, 124, 29);
		seeChangesButton.setEnabled(true);
		getContentPane().add(seeChangesButton);
		
		yearComboBox.addActionListener(new cmbYear_Action());
		table.addMouseListener(new MouseHandler());
		
		refreshCalendar(realMonth, realYear);

		this.setVisible(true);
		selectedDate = new Date();	
	}
	
	public static void refreshCalendar(int month, int year) {
		//Variables
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int numberOfDays, startOfMonth;
	
		//Allow/disallow buttons
		yearComboBox.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
		monthLabel.setText(months[month]); //Refresh the month label at the top
		
		//Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++)
				mtblCalendar.setValueAt(null, i, j);	
		}
		
		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month,1);
		numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); //number of days of current month
		startOfMonth = (cal.get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7 + 1; 
		
		//Draw calendar
		for (int i=1; i<=numberOfDays; i++){
			int row = new Integer((i+startOfMonth-2)/7);
			int column  =  (i+startOfMonth-2)%7;
			mtblCalendar.setValueAt(i, row, column);
		}		

	}

	private class NextMonthAction extends AbstractAction {
		public NextMonthAction() {
			putValue(NAME, ">>");
			putValue(SHORT_DESCRIPTION, "Go to next month");
		}
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11){
				currentMonth = 0;
				currentYear += 1;
			}else{
				currentMonth++;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	private class PreviousMonthAction extends AbstractAction {
		public PreviousMonthAction() {
			putValue(NAME, "<<");
			putValue(SHORT_DESCRIPTION, "Go to previous month");
		}
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0){
				currentMonth = 11;
				currentYear -= 1;
			} else {
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	
	private class cmbYear_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yearComboBox.getSelectedItem() != null){
				String b = yearComboBox.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}	
	}
	
	private class MouseHandler implements MouseListener {
		@SuppressWarnings("deprecation")
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.rowAtPoint(e.getPoint());
		    int col = table.columnAtPoint(e.getPoint());
			if (table.getModel().getValueAt(row, col) != null) {
				int day = (Integer) table.getModel().getValueAt(row, col);
				selectedDate = new Date(currentYear - 1900, currentMonth, day);
			}
		}
	
		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
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

	public void addAcceptButtonListener(ActionListener acceptButtonListener) {
		acceptButton.addActionListener(acceptButtonListener);
	}
	
	public void addDeclineButtonListener(ActionListener declineButtonListener) {
		declineButton.addActionListener(declineButtonListener);
	}
	
	public void addHideButtonListener(ActionListener hideButtonListener) {
		hideButton.addActionListener(hideButtonListener);
	}
			
	public void addDeleteButtonListener(ActionListener deleteButtonListener) {
		deleteButton.addActionListener(deleteButtonListener);
	}

	public void addSeeParticipantListener(ActionListener seeParticipantListener){
		seeParticipantsButton.addActionListener(seeParticipantListener);
	}
	
	public void addSeeChangesListener(ActionListener seeChangesListener){
		seeChangesButton.addActionListener(seeChangesListener);
	}
	
	public void addPreviousMonthButtonListener(ActionListener previousMonthButtonListener) {
		previousMonthButton.addActionListener(previousMonthButtonListener);
	}

	public void addNextMonthButtonListener(ActionListener nextMonthButtonListener) {
		nextMonthButton.addActionListener(nextMonthButtonListener);
	}
		
	public Date getSelectedDate() {
		return selectedDate;
	}

	public Appointment getSelectedAppointment() {
		return dailyAppointmentListBox.getSelectedValue();
	}
	
	public void setAppointmentAccess(String appointment) {
		editButton.setEnabled(false);
		acceptButton.setEnabled(false);
		declineButton.setEnabled(false);
		hideButton.setEnabled(false);
		deleteButton.setEnabled(false);
		seeParticipantsButton.setEnabled(false);

		if (appointment.equals("Not replied")) {
			acceptButton.setEnabled(true);
			declineButton.setEnabled(true);
			seeParticipantsButton.setEnabled(true);
		} else if (appointment.equals("Accepted")) {
			deleteButton.setEnabled(true);
			seeParticipantsButton.setEnabled(true);
		} else if (appointment.equals("Declined")) {
			acceptButton.setEnabled(true);
			hideButton.setEnabled(true);
			seeParticipantsButton.setEnabled(true);
		} else if (appointment.equals("Owned")) {
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
			seeParticipantsButton.setEnabled(true);
		}
	}
	
	public void setSeeChangesButton(boolean status) {
		seeChangesButton.setEnabled(status);
		if (status){
			seeChangesButton.setBackground(new Color(240,144,144));
			seeChangesButton.setContentAreaFilled(false);
			seeChangesButton.setOpaque(true);
		} else {
			seeChangesButton.setBackground(null);
			seeChangesButton.setContentAreaFilled(false);
			seeChangesButton.setOpaque(true);
		}
	}

	public void setDailyAppointmentList(ArrayList<Appointment> dailyAppointmentList) {
		dailyAppointmentListModel.clear();
		if (!dailyAppointmentList.isEmpty()) {
			for (Appointment a : dailyAppointmentList) {
				dailyAppointmentListModel.addElement(a);
			}
		}
	}
	
	public void changeCellColor(Date date, String status) {
		Color color = Color.gray;
		/*
		if (status.equals("Accepted")){
			color = Color.green;
		}else if (status.equals("Declined")){
			color = Color.gray;
		}else if (status.equals("Not replied")){
			color = Color.red;
		}
		*/
		tblCellRenderer tcr = new tblCellRenderer(date,color);
		table.setDefaultRenderer(table.getColumnClass(0), tcr);
	}
	
	static class tblCellRenderer extends DefaultTableCellRenderer{
		private int dayOfMonth;
		private Color color;
		
		public tblCellRenderer(Date date, Color color) {
			this.dayOfMonth = date.getDate();
			this.color = color;
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			if (value != null){
				if (Integer.parseInt(value.toString()) == dayOfMonth ){ //Today
					setBackground(color);
				}else{
					setBackground(Color.white);
				}
			}
			return this;
		}	
	}
	
	public Date getCurrentMonth() {
		return new Date(currentYear - 1900, currentMonth, 1);
	}
		
}
