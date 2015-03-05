package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewEvent extends JFrame {

	private final String FRAME_TITLE = "Create new appointment";
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton createButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		NewEvent window = new NewEvent();
	}

	/**
	 * Create the application.
	 */
	public NewEvent() {
		this.setTitle(FRAME_TITLE);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 6, 118, 254);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 6, 61, 16);
		panel_1.add(lblName);
		
		JLabel lblDate = new JLabel("Startdate");
		lblDate.setBounds(6, 51, 61, 16);
		panel_1.add(lblDate);
		
		JLabel lblEnddate = new JLabel("Enddate");
		lblEnddate.setBounds(6, 90, 61, 16);
		panel_1.add(lblEnddate);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(6, 131, 61, 16);
		panel_1.add(lblRoom);
		
		JLabel lblDescriptions = new JLabel("Descriptions");
		lblDescriptions.setBounds(6, 175, 93, 16);
		panel_1.add(lblDescriptions);
		
		createButton = new JButton("Create");
		createButton.setBounds(6, 219, 117, 29);
		panel_1.add(createButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(136, 6, 296, 254);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 6, 134, 28);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 46, 134, 28);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(6, 86, 134, 28);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(6, 126, 134, 28);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(6, 166, 284, 82);
		panel_2.add(textArea);

		this.setVisible(true);
	}
	
			
	public void addCreateButtonListener(ActionListener createButtonListener) {
		createButton.addActionListener(createButtonListener);
	}

}
