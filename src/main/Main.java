package main;

import view.ProofOfConceptView;

public class Main {
	public static void main(String[] args) {
		ProofOfConceptView pocView = new ProofOfConceptView();
		Controller controller = new Controller(pocView);
		
		pocView.setVisible(true);
	}

}
