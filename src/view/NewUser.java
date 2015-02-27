package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewUser extends JFrame{
	
	public NewUser() {
		super("Create a new user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new JLabel("Empty JFrame"));
		pack();
		setVisible(true);
	
	}
}
