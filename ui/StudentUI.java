package ui;

import java.util.ArrayList;

import camp.Camp;
import database.CampDatabase;
import database.EnquiryDatabase;
import database.SuggestionDatabase;
import enquiry.Enquiry;
import suggestion.Suggestion;
import user.Student;
import utils.ModifiedScanner;

public class StudentUI {
	private static StudentUI studentUI = new StudentUI();
	private static CampUI campUI = CampUI.getInstance();
	private static EnquiryUI enquiryUI = EnquiryUI.getInstance();
	private static SuggestionUI suggestionUI = SuggestionUI.getInstance();
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();
	private static CampDatabase campDatabase = CampDatabase.getInstance();
	private static EnquiryDatabase enquiryDatabase = EnquiryDatabase.getInstance();
	private static SuggestionDatabase suggestionDatabase = SuggestionDatabase.getInstance();

	private StudentUI() {

	}

	public static StudentUI getInstance() {
		return studentUI;
	}

	private void displayStudentOptions() {
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

	private void displayCommitteeOptions() {
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
		System.out.println("-----------------");
		System.out.println("Changing password");
		System.out.println("-----------------");
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
		System.out.println("Password change successfully!");
	}

	public void viewVisibleCamps(Student currentStudent) {
		System.out.println("---------------------------");
		System.out.println("Showing all available camps");
		System.out.println("---------------------------");
		String studentFaculty = currentStudent.getFaculty();
		ArrayList<Camp> visibleCamps = campDatabase.getVisibleCamps(studentFaculty);
		campUI.viewCampList(visibleCamps);
	}

	public void viewRegisteredCamps(Student currentStudent) {
		System.out.println("----------------------------");
		System.out.println("Showing all registered camps");
		System.out.println("----------------------------");
		campUI.viewCampList(currentStudent.getMyCamps());
	}

	public void viewCommitteeEnquiries(Student currentStudent) {
		Camp committeeCamp = currentStudent.getCommitteeCamp();
		ArrayList<Enquiry> enquiryList = committeeCamp.getEnquiryList();
		enquiryUI.viewEnquiryList(enquiryList);
	}

	public void submitSuggestion(Student currentStudent) {
		Camp camp = currentStudent.getCommitteeCamp();
		Suggestion suggestion = suggestionUI.createSuggestion(currentStudent, camp);
		System.out.print(suggestion);
		currentStudent.addSuggestion(suggestion);
		camp.addSuggestion(suggestion);
		suggestionDatabase.addSuggestion(suggestion);
	}

	public void editSuggestion(Student currentStudent) {
		ArrayList<Suggestion> mySuggestions = currentStudent.getSuggestions();

		Suggestion selectedSuggestion;
		if ((selectedSuggestion = suggestionUI.chooseSuggestion(mySuggestions)) == null)
			return;
		suggestionUI.editSuggestion(selectedSuggestion);
	}

	public void deleteSuggestion(Student currentStudent) {
		ArrayList<Suggestion> mySuggestions = currentStudent.getSuggestions();

		Suggestion selectedSuggestion;
		if ((selectedSuggestion = suggestionUI.chooseSuggestion(mySuggestions)) == null)
			return;
		suggestionUI.deleteSuggestion(selectedSuggestion);
	}

	public void viewSuggestions(Student currentStudent) {
		ArrayList<Suggestion> mySuggestions = currentStudent.getSuggestions();
		suggestionUI.viewSuggestionList(mySuggestions);
	}

	public void registerForCamp(Student currentStudent) {
		System.out.println("------------------------------");
		System.out.println("Choose a camp for registration");
		System.out.println("------------------------------");
		String studentFaculty = currentStudent.getFaculty();
		ArrayList<Camp> visibleCamps = campDatabase.getVisibleCamps(studentFaculty);
		Camp selectedCamp;
		if ((selectedCamp = campUI.chooseCamp(visibleCamps)) == null)
			return;
		System.out.println("------------------");
		System.out.println("Options: ");
		System.out.println("------------------");
		System.out.println("(0) Exit");
		System.out.println("(1) Register as Attendee");
		System.out.println("(2) Register as Committee");
		System.out.print("Select an option: ");
		int input = scanner.nextInt();
		while (true) {
			switch (input) {
				case 0:
					return;
				case 1:
					selectedCamp.addAttendee(currentStudent);
					return;
				case 2:
					if (currentStudent.getCommitteeCamp() != null) {
						System.out.println("You can only be a committee for one camp");
					} else
						selectedCamp.addCommittee(currentStudent);
					return;
				default:
					System.out.println("Invalid option.");
					System.out.print("Select an option: ");
					input = scanner.nextInt();
			}
		}
	}

	public void withdrawFromCamp(Student currentStudent) {
		ArrayList<Camp> myCamps = currentStudent.getMyCamps();
		Camp selectedCamp;
		if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
			return;
		System.out.println("------------------");
		System.out.println("Options: ");
		System.out.println("------------------");
		System.out.println("(0) Exit");
		System.out.println("(1) Withdraw from Camp");
		System.out.print("Select an option: ");
		int input = scanner.nextInt();
		while (true) {
			switch (input) {
				case 0:
					return;
				case 1:
					selectedCamp.withdrawAttendee(currentStudent);
					return;
				default:
					System.out.println("Invalid option.");
					System.out.print("Select an option: ");
					input = scanner.nextInt();
			}
		}
	}

	public void submitEnquiry(Student currentStudent) {
		ArrayList<Camp> myCamps = currentStudent.getMyCamps();
		Camp selectedCamp;
		if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
			return;
		Enquiry enquiry = enquiryUI.createEnquiry(currentStudent, selectedCamp);
		selectedCamp.addEnquiry(enquiry);
		enquiryDatabase.addEnquiry(enquiry);
	}

	public void editEnquiry(Student currentStudent) {
		ArrayList<Enquiry> myEnquiries = currentStudent.getEnquiries();
		Enquiry selectedEnquiry;
		if ((selectedEnquiry = enquiryUI.chooseEnquiry(myEnquiries)) == null)
			return;
		enquiryUI.editEnquiry(selectedEnquiry);
	}

	public void deleteEnquiry(Student currentStudent) {
		ArrayList<Enquiry> myEnquiries = currentStudent.getEnquiries();
		Enquiry selectedEnquiry;
		if ((selectedEnquiry = enquiryUI.chooseEnquiry(myEnquiries)) == null)
			return;
		enquiryUI.deleteEnquiry(selectedEnquiry);
	}

	public void viewMyEnquiries(Student currentStudent) {
		enquiryUI.viewEnquiryList(currentStudent.getEnquiries());
	}

	public void replyCommitteeEnquiries(Student currentStudent) {
		ArrayList<Enquiry> committeeEnquiries = currentStudent.getCommitteeCamp().getEnquiryList();
		Enquiry selectedEnquiry;
		if ((selectedEnquiry = enquiryUI.chooseEnquiry(committeeEnquiries)) == null)
			return;
		enquiryUI.replyEnquiry(selectedEnquiry, currentStudent);
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
			if (currentStudent.getCommitteeCamp() == null && input > 10) {
				System.out.println("Invalid option. Try again.");
				continue;
			}
			switch (input) {
				case 1:
					changePassword(currentStudent);
					break;
				case 2:
					viewVisibleCamps(currentStudent);
					break;
				case 3:
					viewRegisteredCamps(currentStudent);
					break;
				case 4:
					registerForCamp(currentStudent);
					break;
				case 5:
					withdrawFromCamp(currentStudent);
					break;
				case 6:
					submitEnquiry(currentStudent);
					break;
				case 7:
					viewMyEnquiries(currentStudent);
					break;
				case 8:
					editEnquiry(currentStudent);
					break;
				case 9:
					deleteEnquiry(currentStudent);
					break;
				case 10:
					cont = false;
					break;
				case 11:
					viewCommitteeEnquiries(currentStudent);
					break;
				case 12:
					replyCommitteeEnquiries(currentStudent);
					break;
				case 13:
					submitSuggestion(currentStudent);
					break;
				case 14:
					editSuggestion(currentStudent);
					break;
				case 15:
					deleteSuggestion(currentStudent);
					break;
				case 16:
					viewSuggestions(currentStudent);
					break;
				default:
					System.out.println("Invalid option. Try again.");
					break;
			}
			System.out.println("---------------------------");
			System.out.println("Press any key to continue");
			scanner.nextLine();
		}
	}
}
