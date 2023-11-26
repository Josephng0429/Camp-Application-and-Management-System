package ui;

import utils.ModifiedScanner;
import java.util.ArrayList;
import user.Staff;
import user.Student;
import user.User;
import commands.*;

public class MenuUI {
	private static MenuUI staffUI = new MenuUI();
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();

	private MenuUI() {
	}

	public static MenuUI getInstance() {
		return staffUI;
	}

	public void menu(User user) {
		boolean cont = true;
		int input;
		UserCommands userCommands = new UserCommands();
		StaffCampCommands staffCampCommands = new StaffCampCommands();
		StaffSuggestionCommands staffSuggestionCommands = new StaffSuggestionCommands();
		StaffReportCommands staffReportCommands = new StaffReportCommands();
		StudentCampCommands studentCampCommands = new StudentCampCommands();
		StudentEnquiryCommands studentEnquiryCommands = new StudentEnquiryCommands();
		CommitteeSuggestionCommands committeeSuggestionCommands = new CommitteeSuggestionCommands();
		OrganizerEnquiryCommands organizerEnquiryCommands = new OrganizerEnquiryCommands();
		OrganizerReportCommands organizerReportCommands = new OrganizerReportCommands();
		while (cont) {
			ArrayList<ICommand> commandList = new ArrayList<ICommand>();
			commandList.addAll(userCommands.getCommands());
			if (user instanceof Staff) {
				commandList.addAll(staffCampCommands.getCommands());
				commandList.addAll(staffSuggestionCommands.getCommands());
				commandList.addAll(organizerEnquiryCommands.getCommands());
				commandList.addAll(organizerReportCommands.getCommands());
				commandList.addAll(staffReportCommands.getCommands());
			}
			if (user instanceof Student) {
				commandList.addAll(studentCampCommands.getCommands());
				commandList.addAll(studentEnquiryCommands.getCommands());
				if (user.getOrganizingCamp() != null) {
					commandList.addAll(committeeSuggestionCommands.getCommands());
					commandList.addAll(organizerEnquiryCommands.getCommands());
				}
			}
			int index = 0;
			System.out.println("~~~~~~~~~~~~~~~~");
			System.out.println("Options:        ");
			System.out.println("~~~~~~~~~~~~~~~~");
			for (ICommand command : commandList) {
				index++;
				System.out.printf("(%d) ", index);
				command.printOption();
			}
			System.out.printf("(%d) Logout\n", index + 1);
			System.out.print("Select an option: ");
			input = scanner.nextInt();
			if (input > 0 && commandList.size() + 1 >= input) {
				if (input == commandList.size() + 1) {
					break;
				}
				ICommand command = commandList.get(input - 1);
				command.execute(user);
			}
		}
	}

}
