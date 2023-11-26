package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ModifiedScanner {
	private static ModifiedScanner modifiedScanner = new ModifiedScanner();
	private static Scanner scanner = new Scanner(System.in);
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

	private ModifiedScanner() {
	}

	public int nextInt() {
		int result = 0;
		try {
			result = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Enter an integer");
		}
		scanner.nextLine(); // consumes empty line after nextInt
		return result;
	}

	public String nextLine() {
		return scanner.nextLine();
	}

	public String next() {
		return scanner.next();
	}

	public LocalDate nextLocalDate() {
		System.out.print("Enter date (dd/mm/yy): ");
		while (true) {
			String input = nextLine();
			LocalDate localDate;
			try {
				localDate = LocalDate.parse(input, formatter);
				return localDate;
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format");
				System.out.print("Enter date (dd/mm/yy): ");
			}
		}
	}

	public static ModifiedScanner getInstance() {
		return modifiedScanner;
	}

	public void close() {
		scanner.close();
	}
}
