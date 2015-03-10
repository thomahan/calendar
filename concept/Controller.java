import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
	private View view;
	private DB db;
	
	public Controller() {
		db = new DB();

		view = new View();
		view.addLoadButtonListener(new LoadValueListener());
		view.addStoreButtonListener(new StoreValueListener());
	}

	class LoadValueListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int value = db.getValue();
			view.setValue(value);
		}
	}

	class StoreValueListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int value = view.getValue();
				db.setValue(value);
			} catch (Exception ex) {
				view.displayErrorMessage("Input must be integer.");
			}
		}
	}

}
