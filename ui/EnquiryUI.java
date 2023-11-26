package ui;

import java.util.ArrayList;

import camp.Camp;
import database.EnquiryDatabase;
import enquiry.Enquiry;
import user.Student;
import user.User;
import utils.ModifiedScanner;

public class EnquiryUI {
	private static EnquiryUI enquiryUI = new EnquiryUI();
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();
	private static EnquiryDatabase enquiryDatabase = EnquiryDatabase.getInstance();

	private EnquiryUI() {
	}

	public static EnquiryUI getInstance() {
		return enquiryUI;
	}

	public Enquiry createEnquiry(User user, Camp camp) {
		System.out.println("Enter the enquiry message:");
		String message = scanner.nextLine();
		Enquiry enquiry = new Enquiry(message, user, camp);
		return enquiry;
	}

	public void editEnquiry(Enquiry enquiry) {
		if (enquiry.isReplied()) {
			System.out.println("Can't edit enquiry it has already been responded to.");
			return;
		}
		System.out.println("Enter new enquiry message:");
		String message = scanner.nextLine();
		enquiry.setTextBody(message);
	}

	public void viewEnquiry(Enquiry enquiry) {
		System.out.printf("Senders name: %s \n", enquiry.getSender().getName());
		System.out.printf("Senders message: %s \n", enquiry.getTextBody());
		if (!enquiry.isReplied())
			System.out.println("This has not been replied yet.");
		else {
			System.out.printf("Repliers name: %s \n", enquiry.getReplier().getName());
			System.out.printf("Repliers message: %s \n", enquiry.getReplyBody());
		}
	}

	public Enquiry chooseEnquiry(ArrayList<Enquiry> enquiries) {
		int index = 0;
		for (Enquiry enquiry : enquiries) {
			index++;
			System.out.printf("---------\nChoice %d:\n---------\n", index);
			viewEnquiry(enquiry);
		}
		int input = 0;
		while (true) {
			System.out.print("Your choice (0 to exit):  ");
			input = scanner.nextInt();
			if (input == 0)
				return null;
			if (input > enquiries.size() || input < 0) {
				System.out.println("Invalid enquiry number, choose again.");
			} else {
				break;
			}
		}
		Enquiry selectedEnquiry = enquiries.get(input - 1);
		System.out.println("---------\nEnquiry selected:\n---------");
		viewEnquiry(selectedEnquiry);
		return selectedEnquiry;
	}

	public void deleteEnquiry(Enquiry enquiry) {
		if (enquiry.isReplied()) {
			System.out.println("Can't delete enquiry it has already been responded to.");
			return;
		}
		enquiryDatabase.removeEnquiry(enquiry);
		System.out.println("Deleted Enquiry");
	}

	public void replyEnquiry(Enquiry enquiry, User user) {
		if (enquiry.isReplied()) {
			System.out.println("Enquiry has already been replied.");
			return;
		}
		System.out.println("Enter your reply:");
		String reply = scanner.nextLine();
		enquiry.setReplier(user);
		enquiry.setReplyBody(reply);
		enquiry.setReplied(true);
	}

	public void viewEnquiryList(ArrayList<Enquiry> enquiryList) {
		for (Enquiry enquiry : enquiryList) {
			viewEnquiry(enquiry);
		}
	}
}
