package view;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.Appointment;

public class CalendarProgram extends JFrame {
	// TODO: Add show hidden option
	
	private final String FRAME_TITLE = "Calendar Program";
	static int realYear, realMonth, realDay, currentYear, currentMonth;

	private static JTable table;
	private static DefaultTableModel mtblCalendar;
	private JScrollPane stblCalendar;
	private JButton logoutButton;
	private JButton newAppointmentButton;
	private static JComboBox comboBox;
	private static JLabel lblNewLabel;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private static MouseListener mouseListener;
	private ArrayList<Appointment> dailyAppointmentList;
	private Date selectedDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		CalendarProgram window = new CalendarProgram();
	}

	/**
	 * Create the application.
	 */
	public CalendarProgram() {
		this.setTitle(FRAME_TITLE);
		this.setResizable(false);
		this.setBounds(100, 100, 600, 375);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		//create controls
		mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
			
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 296, 341);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 6, 284, 37);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JButton button = new JButton("<<");
		button.setAction(action_1);
		button.setBounds(112, 7, 45, 29);
		panel_4.add(button);
		
		JButton button_1 = new JButton(">>");
		button_1.setAction(action);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(150, 7, 45, 29);
		panel_4.add(button_1);
		
		comboBox = new JComboBox();
		comboBox.setBounds(195, 8, 83, 27);
		panel_4.add(comboBox);
		
		lblNewLabel = new JLabel("Month");
		lblNewLabel.setBounds(6, 12, 106, 16);
		panel_4.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 55, 284, 280);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		table = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(table);
		panel_5.add(stblCalendar);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(314, 6, 280, 341);
		this.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 6, 268, 37);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		newAppointmentButton = new JButton("Add New Event");
		newAppointmentButton.setBounds(6, 6, 124, 29);
		panel_2.add(newAppointmentButton);
		
		logoutButton = new JButton("Log out");
		logoutButton.setBounds(138, 6, 124, 29);
		panel_2.add(logoutButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 55, 268, 280);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 10, 248, 260);
		panel_3.add(textArea);

		//Add mouse listener
		table.addMouseListener(new MouseHandler());
		
		//Refresh calendar
		refreshCalendar(realMonth, realYear); //Refresh calendar
		
		
		comboBox.addActionListener(new cmbYear_Action());
		
		this.setVisible(true);
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
			int day = (Integer) table.getModel().getValueAt(row, col);
			Date clickedDay = new Date(currentYear - 1900, currentMonth, day);
			selectedDate = clickedDay;
			return clickedDay;
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	public void refreshEventWindow(Date date){
		System.out.println(date.toString());
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
	
	public Date getSelectDate() {
		return selectedDate;
	}
	
	public void setDailyAppointmentList(ArrayList<Appointment> dailyAppointmentList) {
		this.dailyAppointmentList = dailyAppointmentList;
	}
}
