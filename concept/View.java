
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame {
	private JLabel outputLabel;
	private JButton loadButton;
	private JTextField inputField;
	private JButton storeButton;
	
	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(120, 150);

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new FlowLayout());

		outputLabel = new JLabel();
		panel.add(outputLabel);

		loadButton = new JButton("Load value");
		panel.add(loadButton);

		inputField = new JTextField(8);
		panel.add(inputField);

		storeButton = new JButton("Store value");
		panel.add(storeButton);

		this.setVisible(true);
	}
	
	public int getValue() {
		return Integer.parseInt(inputField.getText());
	}
	
	public void setValue(int value) {
		outputLabel.setText(Integer.toString(value));
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	public void addLoadButtonListener(ActionListener loadButtonListener) {
		loadButton.addActionListener(loadButtonListener);
	}

	public void addStoreButtonListener(ActionListener storeButtonListener) {
		storeButton.addActionListener(storeButtonListener);
	}
}
