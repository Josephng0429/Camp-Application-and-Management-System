package ui;

import java.util.ArrayList;

import camp.Camp;
import database.CampDatabase;
import database.UserDatabase;
import user.Student;
import utils.ModifiedScanner;

public class StudentUI {
    private static StudentUI studentUI = new StudentUI();
    private static CampUI campUI = CampUI.getInstance();
    private static ModifiedScanner scanner = ModifiedScanner.getInstance();
    private static UserDatabase userDatabase = UserDatabase.getInstance();
    private static CampDatabase campDatabase = CampDatabase.getInstance();

    private StudentUI() {

    }

    public static StudentUI getInstance() {
        return studentUI;
    }

    public static void displayStudentOptions() {
        System.out.println("------------------");
        System.out.println(" Student Options: ");
        System.out.println("------------------");
        System.out.println("(1)  Change password");
        System.out.println("(2)  View available camps");
        System.out.println("(3)  View registered camps");
        System.out.println("(4)  Register for a camp");
        System.out.println("(5)  Withdraw from a camp");
        System.out.println("(6)  Submit enquiry");
        System.out.println("(7)  View my enquiries");
        System.out.println("(8)  Edit my enquiry");
        System.out.println("(9)  Delete my enquiry");
        System.out.println("(10) Logout");
    }

    public static void displayCommitteeOptions() {
        System.out.println("------------------");
        System.out.println(" Committee Member Options: ");
        System.out.println("------------------");
        System.out.println("(11) View Committee Enquiries");
        System.out.println("(12) Reply to enquiries");
        System.out.println("(13) Create suggestion");
        System.out.println("(14) Edit suggestion");
        System.out.println("(15) Delete suggestion");
        System.out.println("(16) View suggestions");
    }

    public void changePassword(Student currentStudent) {
        System.out.print("Enter new password: ");

        String newPassword;
        newPassword = scanner.nextLine();

        while (true) {
            if (!(newPassword.equals(null) || newPassword.length() == 0)) {
                currentStudent.setPassword(newPassword);
                break;
            } else {
                System.out.print("New password cannot be empty! Try again: ");
                newPassword = scanner.nextLine();
            }
        }
    }

    public void viewVisibleCamps(Student currentStudent) {
        String studentFaculty = currentStudent.getFaculty();
        ArrayList<Camp> visibleCamps = campDatabase.getVisibleCamps(studentFaculty);
        for (Camp camp : visibleCamps) {
            campUI.viewCamp(camp);
        }

    }

    public void menu(Student currentStudent) {
        boolean cont = true;
        int input;
        while (cont) {
            displayStudentOptions();
            if (currentStudent.getCommitteeCamp() != null)
                displayCommitteeOptions();
            System.out.print("Select an option: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    changePassword(currentStudent);
                    break;
                case 2:
                    viewVisibleCamps(currentStudent);
                    break;
                case 10:
                    cont = false;
                    userDatabase.saveDatabase();
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
}
