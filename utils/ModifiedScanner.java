package utils;

import java.util.Scanner;
import java.time.LocalDate;

public class ModifiedScanner {
	private static ModifiedScanner modifiedScanner = new ModifiedScanner();
	private static Scanner scanner = new Scanner(System.in);

	private ModifiedScanner() {
	}

	public int nextInt() {
		int result = scanner.nextInt();
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
		System.out.println("Enter year:");
		int year, month, day;
		year = scanner.nextInt();
		while (true) {
			System.out.println("Enter month:");
			month = scanner.nextInt();
			if (month < 0 || month > 12) {
				System.out.println("Invalid month");
			} else
				break;
		}
		while (true) {
			System.out.println("Enter day:");
			day = scanner.nextInt();
			scanner.nextLine(); // consumes empty line after nextInt
			if (day < 1 || day > 31) {
				System.out.println("Invalid day");
			} else
				break;
		}
		return LocalDate.of(year, month, day);
	}

	public static ModifiedScanner getInstance() {
		return modifiedScanner;
	}

	public void close() {
		scanner.close();
	}
}
