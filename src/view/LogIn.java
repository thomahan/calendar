package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LogIn extends JFrame implements ActionListener{

	static JButton btnNewUser, btnLogIn;
	static JLabel lblUserName, lblPassword;
	static JTextField fieldUsername, fieldPassword;
	static JFrame frmMain;
	static Container pane;
	static JPanel pnlCalendar;
	
	public LogIn(){
		super("Login to calendar"); //create frame
		this.setSize(330,375); // set size
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when X is clicked
		pane = this.getContentPane();
		pane.setLayout(null); //apply null layout
		
		//Create controls
		lblUserName= new JLabel ("Username");
		lblPassword= new JLabel ("Password");
		btnNewUser = new JButton ("New user?");
		btnLogIn = new JButton ("Log in");
		pnlCalendar = new JPanel(null);
		fieldUsername = new JTextField();
		fieldPassword = new JTextField();
		
		//Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		
		
		btnNewUser= new JButton("New User?");
		btnNewUser.addActionListener(this);
		btnNewUser.setActionCommand("Open");
		add(btnNewUser);
		pack();
	}
	
	public void
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Open")){
			dispose();
			new NewUser();
		}
	}
	
	public static void main(String[] args) {
		
			
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new LogIn().setVisible(true);	
			}
		});
	}
}
