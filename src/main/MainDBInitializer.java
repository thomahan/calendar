package main;

import db.DBInitializer;

public class MainDBInitializer {
	public static void main(String[] args) {
		System.out.println("Creating tables...");
		DBInitializer.createTables();
		System.out.println("Table creation complete!");
		System.out.println("Inserting test data...");
		DBInitializer.insertValues();
		System.out.println("Test data insertion complete!");
	}
}
