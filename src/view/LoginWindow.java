package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import view.CalendarProgram.btnNext_Action;
import view.CalendarProgram.btnPrev_Action;
import view.CalendarProgram.cmbYear_Action;

public class LoginWindow {
	
	static JLabel lblMonth, lblYear;
	static JButton newUser, logIn;
	static JTable tblCalendar;
	static JTextField userName, password;
	static JComboBox cmbYear;
	static JFrame frmMain;
	static Container pane;
	static JPanel pnlCalendar;
	
	public static void main (String args[]){
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Prepare frame
		frmMain = new JFrame ("Login to calendar"); //Create frame
		frmMain.setSize(330, 375); //Set size to 400x400 pixels
		pane = frmMain.getContentPane(); //Get content pane
		pane.setLayout(null); //Apply null layout
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked

		//Create controls
		lblMonth = new JLabel ("January");
		lblYear = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		newUser = new JButton ("New user?");
		logIn = new JButton ("Log in");
		pnlCalendar = new JPanel(null);
		
		//Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
				
		//Register action listeners
		newUser.addActionListener(new btnPrev_Action());
		logIn.addActionListener(new btnNext_Action());
		
		//Add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(lblMonth);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(logIn);
		pnlCalendar.add(newUser);
		
		//Set bounds
		pnlCalendar.setBounds(0, 0, 320, 335);
		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
		lblYear.setBounds(10, 305, 80, 20);
		cmbYear.setBounds(230, 305, 80, 20);
		logIn.setBounds(10, 25, 50, 25);
		newUser.setBounds(260, 25, 50, 25);
		
		//Make frame visible
		frmMain.setResizable(false);
		frmMain.setVisible(true);
	}
}
