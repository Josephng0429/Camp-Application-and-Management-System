package ui;

import utils.ModifiedScanner;
import java.util.ArrayList;
import database.*;
import user.Staff;
import camp.*;

public class StaffUI {
    private static StaffUI staffUI = new StaffUI();
    private static CampUI campUI = CampUI.getInstance();
    static UserDatabase userDatabase = UserDatabase.getInstance();
    static CampDatabase campDatabase = CampDatabase.getInstance();
    private static ModifiedScanner scanner = ModifiedScanner.getInstance();

    private StaffUI() {
    }

    public static StaffUI getInstance() {
        return staffUI;
    }

    public void changePassword(Staff currentStaff){ 
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        while (true) {
            if (!(newPassword.equals(null) || newPassword.length() == 0)) {
                currentStaff.setPassword(newPassword);
                break;
            } else {
                System.out.print("New password cannot be empty! Try again: ");
                newPassword = scanner.nextLine();
            }
        }
        System.out.print("Password changed succesfully!");
    }

    public void viewMyCamps(Staff currentStaff){
        ArrayList<Camp> myCamps = currentStaff.getMyCamps();
        for(Camp camp: myCamps){
            campUI.viewCamp(camp);
        }
    }
    
    public void viewAllCamps(){
        ArrayList<Camp> allCamps = campDatabase.getAllCamps();
        for(Camp camp: allCamps){
            campUI.viewCamp(camp);
        }
    }

    public void menu(Staff currentStaff) {
        boolean cont = true;
        int input;
        while (cont) {
            System.out.println("~~~~~~~~~~~~~~~~");
            System.out.println(" Staff Options: ");
            System.out.println("~~~~~~~~~~~~~~~~");
            System.out.println("(1)  Change password");
            System.out.println("(2)  View all Camps");
            System.out.println("(3)  View my Camps");
            System.out.println("(4)  Create a Camp");
            System.out.println("(5)  Edit my Camps");
            System.out.println("(6)  Delete my Camps");
            System.out.println("(7)  Set Camp's visibility");
            System.out.println("(8)  Process suggestions");
            System.out.println("(9)  Generate performance report");
            System.out.println("(10)  View Committee Enquiries");
            System.out.println("(11) Reply to enquiries");
            System.out.println("(12) Logout");
            System.out.print("Select an option: ");
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    changePassword(currentStaff);
                    break;
                case 2:
                    viewAllCamps();
                    break;
                case 3:
                    viewMyCamps(currentStaff);
                    break;
                case 4:
                    Camp newCamp = campUI.createCamp(currentStaff);
                    currentStaff.addCamp(newCamp);
                    campDatabase.addCamp(newCamp);
                    System.out.println("Camp created successfully.");
                    break;
                case 12:
                    cont = false;
                    userDatabase.saveDatabase();
                    campDatabase.saveDatabase();
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

}
