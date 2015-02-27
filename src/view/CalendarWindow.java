package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import view.CalendarProgram.btnNext_Action;
import view.CalendarProgram.btnPrev_Action;
import view.CalendarProgram.cmbYear_Action;
import view.CalendarProgram.tblCalendarRenderer;

public class CalendarWindow {

	static JLabel lblMonth, lblYear;
	static JButton btnPrev, btnNext;
	static JTable tblCalendar;
	static JComboBox cmbYear;
	static JFrame frmMain;
	static Container pane;
	static DefaultTableModel mtblCalendar;
	static JScrollPane stblCalendar;
	static JPanel pnlCalendar;
	static int realYear, realMonth, realDay, currentYear, currentMonth; 
	
	public static void main (String args[]) {
		
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Prepare frame
		frmMain = new JFrame ("Calendar program"); //Create frame
		frmMain.setSize(330, 375); //Set size to 400x400 pixels
		pane = frmMain.getContentPane(); //Get content pane
		pane.setLayout(null); //Apply null layout
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
	
		//Create controls
		lblMonth = new JLabel ("January");
		lblYear = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
		mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);
		pnlCalendar = new JPanel(null);	
		
		//Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		//Add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(lblMonth);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(stblCalendar);
		
		//Set bounds
		pnlCalendar.setBounds(0, 0, 320, 335);
		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
		lblYear.setBounds(10, 305, 100, 20);
		cmbYear.setBounds(230, 305, 85, 20);
		btnPrev.setBounds(10, 25, 50, 25);
		btnNext.setBounds(260, 25, 50, 25);
		stblCalendar.setBounds(10, 50, 300, 250);
		
		//Make frame visible
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		//No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);
		
		//Set row/column count
		tblCalendar.setRowHeight(38);
		mtblCalendar.setColumnCount(0);
		mtblCalendar.setRowCount(6);
		
		//Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Add headers to mtblCalendar
		String[] headers = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}; 
		for (int i=0; i<7; i++)
			mtblCalendar.addColumn(headers[i]);
		
		//Set background
		tblCalendar.getParent().setBackground(tblCalendar.getBackground()); 
		
		//No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);
		
		//Register action listeners
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());
		
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
			cmbYear.addItem(String.valueOf(i));
	
		//Refresh calendar
		refreshCalendar(realMonth, realYear); //Refresh calendar
		
	}
	
	public static void refreshCalendar(int month, int year) {
		//Variables
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som; //Number Of Days, Start Of Month
		
		//Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear - 10){btnPrev.setEnabled(false);} //Too early
		if (month == 11 && year >= realYear + 10){btnNext.setEnabled(false);} //Too late
		lblMonth.setText(months[month]); //Refresh the month label at the top
		lblMonth.setBounds(160 - lblMonth.getPreferredSize().width/2,25,180,25); // Re-align label
		cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
		
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
		
		//Apply renderers: If so: create a tblCalendarRenderer-class which extends DefaultTableCellRenderer
//		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}
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
//	}
	
	static class btnPrev_Action implements ActionListener {
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
	
	static class btnNext_Action implements ActionListener {
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
	
	static class cmbYear_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (cmbYear.getSelectedItem() != null){
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}
		
	}
	
}
	

