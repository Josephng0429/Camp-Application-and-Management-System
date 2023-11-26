import utils.ModifiedScanner;
import database.*;
import user.*;
import ui.MenuUI;

public class LoginApplication {
	static ModifiedScanner scanner = ModifiedScanner.getInstance();
	static UserDatabase userDatabase = UserDatabase.getInstance();
	static CampDatabase campDatabase = CampDatabase.getInstance();
	static EnquiryDatabase enquiryDatabase = EnquiryDatabase.getInstance();
	static SuggestionDatabase suggestionDatabase = SuggestionDatabase.getInstance();
	static MenuUI menuUI = MenuUI.getInstance();

	// LOGIN PAGE START
	public static void main(String[] args) {
		int choice = 0;
		do {
			System.out.println("*********************************************");
			System.out.println("Camp Application and Management System (CAMS)");
			System.out.println("*********************************************");
			System.out.println("(1) Login");
			System.out.println("(2) Exit Program");
			System.out.print("Select an option: ");
			choice = scanner.nextInt();
			if (choice == 1) {
				User currentUser = login();
				menuUI.menu(currentUser);
			}
		} while (choice != 2);
		userDatabase.saveDatabase();
		campDatabase.saveDatabase();
		enquiryDatabase.saveDatabase();
		suggestionDatabase.saveDatabase();
	}

	public static User login() {
		User currentUser;
		while (true) {
			System.out.print("Username: ");
			String username = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();
			currentUser = userDatabase.getUserByID(username);
			if (currentUser == null || !currentUser.validPassword(password))
				System.out.println("\nLogin failed. Incorrect username or password. Please try again.\n");
			else
				break;
		}
		System.out.println("\nLogin successful. Welcome, " + currentUser.getName() + "!");
		// check if first time login for user, then prompt them to reset password
		return currentUser;
	}
}
