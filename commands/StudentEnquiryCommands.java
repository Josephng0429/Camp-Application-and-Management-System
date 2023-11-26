package commands;

import java.util.ArrayList;

import camp.Camp;
import database.EnquiryDatabase;
import enquiry.Enquiry;
import ui.CampUI;
import ui.EnquiryUI;
import user.Student;
import user.User;

public class StudentEnquiryCommands implements ICommandPackage {
	private static EnquiryUI enquiryUI = EnquiryUI.getInstance();
	private static EnquiryDatabase enquiryDatabase = EnquiryDatabase.getInstance();
	private static CampUI campUI = CampUI.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new SubmitEnquiry());
		commandList.add(new EditEnquiry());
		commandList.add(new DeleteEnquiry());
		commandList.add(new ViewMyEnquiries());
		return commandList;
	}

	public class SubmitEnquiry implements ICommand {
		public void printOption() {
			System.out.println("Submit an enquiry");
		}

		public void execute(User user) {
			Student currentStudent = (Student) user;
			ArrayList<Camp> myCamps = currentStudent.getMyCamps();
			Camp selectedCamp;
			if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
				return;
			Enquiry enquiry = enquiryUI.createEnquiry(currentStudent, selectedCamp);
			enquiryDatabase.addEnquiry(enquiry);
		}
	}

	public class ViewMyEnquiries implements ICommand {
		public void printOption() {
			System.out.println("View my enquiries");
		}

		public void execute(User user) {
			enquiryUI.viewEnquiryList(enquiryDatabase.getEnquiriesBySender(user));
		}
	}

	public class EditEnquiry implements ICommand {
		public void printOption() {
			System.out.println("Edit an enquiry");
		}

		public void execute(User user) {
			ArrayList<Enquiry> myEnquiries = enquiryDatabase.getEnquiriesBySender(user);
			Enquiry selectedEnquiry;
			if ((selectedEnquiry = enquiryUI.chooseEnquiry(myEnquiries)) == null)
				return;
			enquiryUI.editEnquiry(selectedEnquiry);
		}
	}

	public class DeleteEnquiry implements ICommand {
		public void printOption() {
			System.out.println("Delete an enquiry");
		}

		public void execute(User user) {
			ArrayList<Enquiry> myEnquiries = enquiryDatabase.getEnquiriesBySender(user);
			Enquiry selectedEnquiry;
			if ((selectedEnquiry = enquiryUI.chooseEnquiry(myEnquiries)) == null)
				return;
			enquiryUI.deleteEnquiry(selectedEnquiry);
		}
	}

}
