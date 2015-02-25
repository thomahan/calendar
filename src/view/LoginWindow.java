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
	
	static JLabel lblWelcome, lblUserName, lblPassword;
	static JButton btnNewUser, btnLogIn;
	static JTextField fieldUserName, fieldPassword;
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
		lblWelcome = new JLabel ("Welcome!");
		lblUserName= new JLabel ("Username");
		lblPassword= new JLabel ("Password");
		btnNewUser = new JButton ("New user?");
		btnLogIn = new JButton ("Log in");
		pnlCalendar = new JPanel(null);
		fieldUserName = new JTextField();
		fieldPassword = new JTextField();
		
		//Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
				
		//Register action listeners
		btnNewUser.addActionListener(new btnPrev_Action());
		btnLogIn.addActionListener(new btnNext_Action());
		btnNewUser.addActionListener(new btnNewUser_Action());
		btnLogIn.addActionListener(new btnLogIn_Action());
		
		//Add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(lblWelcome);
		pnlCalendar.add(lblUserName);
		pnlCalendar.add(lblPassword);
		pnlCalendar.add(btnLogIn);
		pnlCalendar.add(btnNewUser);
		pnlCalendar.add(fieldUserName);
		pnlCalendar.add(fieldPassword);
		
		//Set bounds
		pnlCalendar.setBounds(0, 0, 320, 335);
		lblWelcome.setBounds(160-lblWelcome.getPreferredSize().width/2, 25, 100, 25);
		lblUserName.setBounds(30, 125, 100, 25);
		lblPassword.setBounds(30,150,100,25);
		btnLogIn.setBounds(200, 125, 100, 25);
		btnNewUser.setBounds(200, 150, 100, 25);
		fieldUserName.setBounds(100, 125, 100, 25);
		fieldPassword.setBounds(100, 150, 100, 25);
		
		//Make frame visible
		frmMain.setResizable(false);
		frmMain.setVisible(true);
	}
	
	static class btnLogIn_Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	static class btnNewUser_Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}

}