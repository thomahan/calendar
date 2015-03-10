import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
	private View view;
	private DB db;
	
	public Controller() {
		db = new DB();

		view = new View();
		view.addGetValueButtonListener(new GetValueListener());
		view.addSetValueButtonListener(new SetValueListener());
	}

	class GetValueListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int value = db.getValue();
			view.setValue(value);
		}
	}

	class SetValueListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int value = view.getValue();
			db.setValue(value);
		}
	}

}
