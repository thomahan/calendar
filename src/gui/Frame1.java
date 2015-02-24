package gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame1 extends JFrame{
	
	JPanel pane = new JPanel();
	
	public Frame1() {
		super("Here you can log in"); setBounds(100,100,300,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane();
		con.add(pane);
		setVisible(true);
	}
	
	public static void main(String args[]){
		new Frame1();
	}

}
