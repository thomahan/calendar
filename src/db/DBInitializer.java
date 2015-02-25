package db;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DBInitializer {
	private static String initializationFile = "src/db/dbinit.sql";

	/**
	 * Reads an SQL-file and returns a list of executable statements
	 * @param fileName
	 * @return Statements
	 */
	public static List<String> readSqlFile(String fileName) {
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
	 * Executes a list of statements from file on the database
	 */
	public static void initializeDB() {
		List<String> statementList = readSqlFile(initializationFile);
		for (String s : statementList) {
			DBConnector.makeStatement(s);
		}
	}
}
