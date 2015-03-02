package main;

import view.LogIn;

public class Main {
	public static void main(String[] args) {
		LogIn loginView = new LogIn();
		Controller controller = new Controller(loginView);
	}

}
