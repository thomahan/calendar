package db;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DBInitializer {
	private static String initializationFile = "src/db/dbinit.sql";
	private static String testDataFile = "src/db/db_test_data_insertion.sql";
	
	/**
	 * Creates empty tables in the database
	 */
	public static void initializeDB() {
		executeSqlFile(initializationFile);
	}

	/**
	 * Populates the tables with test data
	 */
	public static void insertTestData() {
		executeSqlFile(testDataFile);
	}

	/**
	 * Reads an SQL-file and returns a list of executable statements
	 * @param fileName
	 * @return Statements
	 */
	private static List<String> readSqlFile(String fileName) {
		ArrayList<String> statementList = new ArrayList<String>();
		String statement = "";

		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				statement = statement.concat(line);

				if (line.contains(";")) {
					statementList.add(statement);
					statement = "";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return statementList;
	}
	
	/**
	 * Executes an SQL-file on the database
	 * @param fileName
	 */
	private static void executeSqlFile(String fileName) {
		List<String> statementList = readSqlFile(fileName);
		for (String s : statementList) {
			DBConnector.makeStatement(s);
		}
	}
}
