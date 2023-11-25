package ui;

import utils.ModifiedScanner;
import java.util.ArrayList;
import database.*;
import suggestion.Suggestion;
import user.Staff;
import camp.*;

public class StaffUI {
	private static StaffUI staffUI = new StaffUI();
	private static CampUI campUI = CampUI.getInstance();
	private static SuggestionUI suggestionUI = SuggestionUI.getInstance();
	static UserDatabase userDatabase = UserDatabase.getInstance();
	static CampDatabase campDatabase = CampDatabase.getInstance();
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();

	private StaffUI() {
	}

	public static StaffUI getInstance() {
		return staffUI;
	}

	public void changePassword(Staff currentStaff) {
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

	public void viewMyCamps(Staff currentStaff) {
		ArrayList<Camp> myCamps = currentStaff.getMyCamps();
		campUI.viewCampList(myCamps);
	}

	public void viewAllCamps() {
		ArrayList<Camp> allCamps = campDatabase.getAllCamps();
		campUI.viewCampList(allCamps);
	}

	public void createCamp(Staff currentStaff) {
		Camp newCamp = campUI.createCamp(currentStaff);
		currentStaff.addCamp(newCamp);
		campDatabase.addCamp(newCamp);
		System.out.println("Camp created successfully.");
	}

	public void editCamp(Staff currentStaff) {
		ArrayList<Camp> myCamps = currentStaff.getMyCamps();
		Camp selectedCamp;
		if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
			return;
		campUI.editCamp(selectedCamp);
	}

	public void deleteCamp(Staff currentStaff) {
		ArrayList<Camp> myCamps = currentStaff.getMyCamps();
		Camp selectedCamp;
		if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
			return;
		campUI.deleteCamp(selectedCamp);

	}

	public void setCampVisiblity(Staff currentStaff) {
		ArrayList<Camp> myCamps = currentStaff.getMyCamps();
		Camp selectedCamp;
		if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
			return;
		campUI.setCampVisibility(selectedCamp);
	}

	public void processSuggestions(Staff currentStaff) {
		ArrayList<Camp> myCamps = currentStaff.getMyCamps();
		Camp selectedCamp;
		if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
			return;
		ArrayList<Suggestion> campSuggestions = selectedCamp.getSuggestions();
		Suggestion selectedSuggestion;
		if ((selectedSuggestion = suggestionUI.chooseSuggestion(campSuggestions)) == null)
			return;
		suggestionUI.reviewSuggestion(selectedSuggestion);

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
					createCamp(currentStaff);
					break;
				case 5:
					editCamp(currentStaff);
					break;
				case 6:
					deleteCamp(currentStaff);
					break;
				case 7:
					setCampVisiblity(currentStaff);
					break;
				case 8:
					processSuggestions(currentStaff);
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
