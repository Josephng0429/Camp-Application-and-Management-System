package commands;

import java.util.ArrayList;

import camp.Camp;
import database.EnquiryDatabase;
import enquiry.Enquiry;
import ui.EnquiryUI;
import user.User;

public class OrganizerEnquiryCommands implements ICommandPackage {
	private static EnquiryUI enquiryUI = EnquiryUI.getInstance();
	private static EnquiryDatabase enquiryDatabase = EnquiryDatabase.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new ViewCampEnquiries());
		commandList.add(new ReplyCampEnquiries());
		return commandList;
	}

	public class ViewCampEnquiries implements ICommand {
		public void printOption() {
			System.out.println("View camp enquires");
		}

		public void execute(User user) {
			Camp committeeCamp = user.getOrganizingCamp();
			ArrayList<Enquiry> enquiryList = enquiryDatabase.getEnquiriesByCamp(committeeCamp);
			enquiryUI.viewEnquiryList(enquiryList);
		}
	}

	public class ReplyCampEnquiries implements ICommand {
		public void printOption() {
			System.out.println("Reply camp enquiries");
		}

		public void execute(User user) {
			Camp committeeCamp = user.getOrganizingCamp();
			ArrayList<Enquiry> committeeEnquiries = enquiryDatabase.getEnquiriesByCamp(committeeCamp);
			Enquiry selectedEnquiry;
			if ((selectedEnquiry = enquiryUI.chooseEnquiry(committeeEnquiries)) == null)
				return;
			enquiryUI.replyEnquiry(selectedEnquiry, user);
		}
	}
}
