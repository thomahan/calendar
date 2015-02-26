package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.User;

//import view.CalendarProgram.btnNext_Action;
//import view.CalendarProgram.btnPrev_Action;
//import view.LoginWindow.btnLogIn_Action;
//import view.LoginWindow.btnNewUser_Action;

public class NewUserWindow {
	
	static JLabel lblInstruction, lblUserName, lblGivenName, lblLastName, lblPassword, lblError;
	static JButton btnConfirm;
	static JTextField fieldUserName, fieldGivenName, fieldLastName, fieldPassword;
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
		frmMain = new JFrame ("New user"); //Create frame
		frmMain.setSize(330, 375); //Set size to 400x400 pixels
		pane = frmMain.getContentPane(); //Get content pane
		pane.setLayout(null); //Apply null layout
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked

		//Create controls
		lblInstruction= new JLabel ("Please fill out the required information");
		lblUserName= new JLabel ("Username");
		lblGivenName= new JLabel ("Given name");
		lblLastName= new JLabel ("Last name");
		lblPassword= new JLabel ("Password");
		lblError= new JLabel ("Password taken. Try again.");
		btnConfirm = new JButton ("Confirm");
		pnlCalendar = new JPanel(null);
		fieldUserName = new JTextField();
		fieldGivenName = new JTextField();
		fieldLastName = new JTextField();		
		fieldPassword = new JTextField();
		
		//Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
				
		//Register action listeners
		btnConfirm.addActionListener(new btnConfirm_Action());
		
		//Add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(lblInstruction);
		pnlCalendar.add(lblUserName);
		pnlCalendar.add(lblGivenName);
		pnlCalendar.add(lblLastName);
		pnlCalendar.add(lblPassword);
		pnlCalendar.add(btnConfirm);
		pnlCalendar.add(fieldUserName);
		pnlCalendar.add(fieldGivenName);
		pnlCalendar.add(fieldLastName);
		pnlCalendar.add(fieldPassword);
		
		//Set bounds
		pnlCalendar.setBounds(0, 0, 320, 335);
		lblInstruction.setBounds(30,25,500,25);
		//lblInstruction.getPreferredSize().width
		lblUserName.setBounds(30, 50, 100, 25);
		lblGivenName.setBounds(30, 75, 100, 25);
		lblLastName.setBounds(30, 100, 100, 25);
		lblPassword.setBounds(30,125,100,25);
		btnConfirm.setBounds(200, 300, 100, 25);
		fieldUserName.setBounds(100, 50, 100, 25);
		fieldGivenName.setBounds(100, 75, 100, 25);
		fieldLastName.setBounds(100, 100, 100, 25);
		fieldPassword.setBounds(100, 125, 100, 25);
		
		//Make frame visible
		frmMain.setResizable(false);
		frmMain.setVisible(true);
	}
	
	static class btnConfirm_Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String userName = fieldUserName.getText();
			String givenName = fieldGivenName.getText();
			String lastName = fieldLastName.getText();
			String password = fieldPassword.getText();
			User newUser;
			try{
				newUser = new User(userName);
			}
			catch (IllegalArgumentException a){
				pnlCalendar.add(lblError);
				lblError.setBounds(100,200,100,25);
				System.out.println("Username taken. Try again.");
			}
		}
	}
}
