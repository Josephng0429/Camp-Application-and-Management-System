package user;

import camp.*;
import enquiry.*;
import suggestion.*;
import ui.CampUI;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Staff extends User {
	private ArrayList<Camp> myCamps;

	public Staff(String name, String password, String email, String faculty) {
		super(name, password, email, faculty);
		this.myCamps = new ArrayList<>();
	}

	public ArrayList<Camp> getMyCamps() {
		return myCamps;
	}

	public void addCamp(Camp camp) {
		myCamps.add(camp);
	}

	public void editCamp(Camp camp, CampInfo newCampInfo, int newNumStudents, int newNumCommittee, boolean newVisible) {
		if (myCamps.contains(camp)) {
			camp.getCampInfo().setCampName(newCampInfo.getCampName());
			camp.getCampInfo().setStartDate(newCampInfo.getStartDate());
			camp.getCampInfo().setEndDate(newCampInfo.getEndDate());
			camp.getCampInfo().setRegCloseDate(newCampInfo.getRegCloseDate());
			camp.getCampInfo().setCampFaculty(newCampInfo.getCampFaculty());
			camp.getCampInfo().setLocation(newCampInfo.getLocation());
			camp.getCampInfo().setNumAttendeeSlots(newCampInfo.getNumAttendeeSlots());
			camp.getCampInfo().setNumCommitteeSlots(newCampInfo.getNumCommitteeSlots());
			camp.getCampInfo().setDescription(newCampInfo.getDescription());
			camp.getCampInfo().setStaffInCharge(newCampInfo.getStaffInCharge());
			camp.setVisible(newVisible);

			System.out.println("Camp updated successfully.");
		} else {
			System.out.println("You do not have permission to edit this camp.");
		}
	}

	public void delCamp(Camp camp) {
		if (myCamps.contains(camp)) {
			myCamps.remove(camp);
			System.out.println("Camp '" + camp.getCampInfo().getCampName() + "' deleted successfully.");
		} else {
			System.out.println("Camp not found. Deletion failed.");
		}
	}

	public void setCampVisibility(Camp camp, boolean visible) {
		if (myCamps.contains(camp)) {
			camp.setVisible(visible);
			System.out.println("Visibility of camp '" + camp.getCampInfo().getCampName() + "' set to "
					+ (visible ? "visible" : "invisible"));
		} else {
			System.out.println("Camp not found. Visibility update failed.");
		}
	}

	public void viewAllCamps(List<Staff> allStaffMembers) {
		System.out.println("All Camps:");
		for (Staff staffMember : allStaffMembers) {
			displayStaffCamps(staffMember);
		}
	}

	private void displayStaffCamps(Staff staffMember) {
		System.out.println("Camps created by " + staffMember.getName() + ":");
		for (Camp camp : staffMember.getMyCamps()) {
		}
		System.out.println("------------------------------");
	}

	public void viewMyCamps() {
		if (myCamps.isEmpty()) {
			System.out.println("You have no camps to display.");
		} else {
			System.out.println("List of all camps:");

			for (Camp camp : myCamps) {
			}
		}
	}

	public void processSuggestions(List<Suggestion> suggestions) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Camp Suggestions:");
		for (Suggestion suggestion : suggestions) {
			displaySuggestionInfo(suggestion);

			System.out.println("Do you want to approve this suggestion? (yes/no)");
			String approvalDecision = scanner.nextLine().toLowerCase();

			if (approvalDecision.equals("yes")) {
				approveSuggestion(suggestion);
			} else {
				rejectSuggestion(suggestion);
			}
		}
	}

	private void displaySuggestionInfo(Suggestion suggestion) {
		System.out.println("Suggestion by: " + suggestion.getSender().getName());
		System.out.println("Suggestion: " + suggestion.getTextBody());
		System.out.println("------------------------------");
	}

	private void approveSuggestion(Suggestion suggestion) {
		suggestion.setApproved(true);
		System.out.println("Suggestion approved successfully.");
	}

	private void rejectSuggestion(Suggestion suggestion) {
		suggestion.setApproved(false);
		System.out.println("Suggestion rejected.");
	}

	public void generatePerformanceReport(ArrayList<Camp> myCamps) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Generate Performance Report");
		System.out.println("1. Attendees");
		System.out.println("2. Camp Committee Members");
		System.out.print("Select an option: ");
		int option = scanner.nextInt();

		switch (option) {
			case 1:
				generateReportForRole(myCamps, "attendee");
				break;
			case 2:
				generateReportForRole(myCamps, "camp committee");
				break;
			default:
				System.out.println("Invalid option.");
				break;
		}
	}

	private void generateReportForRole(ArrayList<Camp> myCamps, String role) {
		System.out.println("Generating Performance Report for " + role + "s");

		try {
			FileWriter writer = new FileWriter("performance_report_" + role + ".csv");
			writer.append("Camp Name,Start Date,End Date,Location,Total Slots,Num Committee Slots,Role\n");

			for (Camp camp : myCamps) {
				for (Student participant : getParticipantsForRole(camp, role)) {
					writeParticipantInfo(writer, camp, participant, role);
				}
			}

			writer.flush();
			writer.close();
			System.out.println("Performance Report generated successfully.");
		} catch (IOException e) {
			System.out.println("Error generating the report.");
			e.printStackTrace();
		}
	}

	private ArrayList<Student> getParticipantsForRole(Camp camp, String role) {
		ArrayList<Student> participants = new ArrayList<>();
		if (role.equalsIgnoreCase("attendee")) {
			participants.addAll(camp.getAttendeeList());
		} else if (role.equalsIgnoreCase("camp committee")) {
			participants.addAll(camp.getCommitteeList());
		}
		return participants;
	}

	private void writeParticipantInfo(FileWriter writer, Camp camp, Student participant, String role)
			throws IOException {
		writer.append(String.format("%s,%s,%s,%s,%d,%d,%s\n",
				camp.getCampInfo().getCampName(),
				camp.getCampInfo().getStartDate(),
				camp.getCampInfo().getEndDate(),
				camp.getCampInfo().getLocation(),
				camp.getCampInfo().getTotalSlots(),
				camp.getCampInfo().getNumCommitteeSlots(),
				role));
	}

	public void viewAndReplyToEnquiries(ArrayList<Camp> myCamps) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("View and Reply to Enquiries");

		System.out.println("List of Camps You Have Created:");
		for (int i = 0; i < myCamps.size(); i++) {
			System.out.println((i + 1) + ". " + myCamps.get(i).getCampInfo().getCampName());
		}

		System.out.print("Select a camp (enter the corresponding number): ");
		int campIndex = scanner.nextInt() - 1;

		if (campIndex < 0 || campIndex >= myCamps.size()) {
			System.out.println("Invalid camp selection.");
			return;
		}

		Camp selectedCamp = myCamps.get(campIndex);

		ArrayList<Enquiry> enquiryList = selectedCamp.getEnquiryList();
		System.out.println("Enquiries for " + selectedCamp.getCampInfo().getCampName() + ":");
		for (int i = 0; i < enquiryList.size(); i++) {
			Enquiry enquiry = enquiryList.get(i);
			System.out.println((i + 1) + ". " + enquiry.getTextBody());
			System.out.println("   From: " + enquiry.getSender().getName());
			System.out.println();
		}

		System.out.print("Select an enquiry to reply (enter the corresponding number): ");
		int enquiryIndex = scanner.nextInt() - 1;

		if (enquiryIndex < 0 || enquiryIndex >= enquiryList.size()) {
			System.out.println("Invalid enquiry selection.");
			return;
		}

		Enquiry selectedEnquiry = enquiryList.get(enquiryIndex);

		System.out.println("Selected Enquiry:");
		System.out.println("From: " + selectedEnquiry.getSender().getName());
		System.out.println("Message: " + selectedEnquiry.getTextBody());

		System.out.print("Enter your reply: ");
		scanner.nextLine();
		String reply = scanner.nextLine();

		selectedEnquiry.setReplied(true);
		selectedEnquiry.setReplyBody(reply);
		selectedEnquiry.setReplier(this);

		System.out.println("Reply sent successfully.");
	}

}
