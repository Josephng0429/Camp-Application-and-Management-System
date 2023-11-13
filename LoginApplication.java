import utils.ModifiedScanner;
import database.*;
import ui.StaffUI;
import ui.StudentUI;
import user.*;

public class LoginApplication {
    static ModifiedScanner scanner = ModifiedScanner.getInstance();
    static UserDatabase userDatabase = UserDatabase.getInstance();
    static StaffUI staffUI = StaffUI.getInstance();
    static StudentUI studentUI = StudentUI.getInstance();

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
            if(choice == 1){
                User currentUser = login();
                // if User is Staff:
                if (currentUser instanceof Staff) {
                    staffUI.menu((Staff) currentUser);
                } else if (currentUser instanceof Student) {
                    studentUI.menu((Student) currentUser);
                }
            }
        } while (choice != 2);

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
