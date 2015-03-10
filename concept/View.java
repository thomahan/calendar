
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame {
	private JTextField valueField;
	private JButton getValueButton;
	private JButton setValueButton;
	
	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);

		JPanel panel = new JPanel();
		this.add(panel);

		valueField = new JTextField(8);
		panel.add(valueField);

		getValueButton = new JButton("Get value");
		panel.add(getValueButton);
	
		setValueButton = new JButton("Set value");
		panel.add(setValueButton);

		this.setVisible(true);
	}
	
	public int getValue() {
		return Integer.parseInt(valueField.getText());
	}
	
	public void setValue(int value) {
		valueField.setText(Integer.toString(value));
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	public void addGetValueButtonListener(ActionListener getValueButtonListener) {
		getValueButton.addActionListener(getValueButtonListener);
	}

	public void addSetValueButtonListener(ActionListener setValueButtonListener) {
		setValueButton.addActionListener(setValueButtonListener);
	}
}
