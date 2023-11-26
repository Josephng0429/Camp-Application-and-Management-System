package commands;

import java.util.ArrayList;

import camp.Camp;
import database.CampDatabase;
import ui.CampUI;
import user.Student;
import user.User;
import utils.ModifiedScanner;

public class StudentCampCommands implements ICommandPackage {
	private static CampUI campUI = CampUI.getInstance();
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();
	private static CampDatabase campDatabase = CampDatabase.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new ViewAvailableCamps());
		commandList.add(new ViewRegisteredCamps());
		commandList.add(new RegisterForCamp());
		commandList.add(new WithdrawFromCamp());
		return commandList;
	}

	public class ViewAvailableCamps implements ICommand {
		public void printOption() {
			System.out.println("View available camps");
		}

		public void execute(User user) {
			Student currentStudent = (Student) user;
			System.out.println("---------------------------");
			System.out.println("Showing all available camps");
			System.out.println("---------------------------");
			String studentFaculty = currentStudent.getFaculty();
			ArrayList<Camp> visibleCamps = campDatabase.getVisibleCamps(studentFaculty);
			campUI.viewCampList(visibleCamps);
		}

	}

	public class ViewRegisteredCamps implements ICommand {
		public void printOption() {
			System.out.println("View registered camps");
		}

		public void execute(User user) {
			Student currentStudent = (Student) user;
			System.out.println("----------------------------");
			System.out.println("Showing all registered camps");
			System.out.println("----------------------------");
			campUI.viewCampList(currentStudent.getMyCamps());
		}
	}

	public class RegisterForCamp implements ICommand {
		public void printOption() {
			System.out.println("Register for camp");

		}

		public void execute(User user) {
			Student currentStudent = (Student) user;
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
						if (currentStudent.getOrganizingCamp() != null) {
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
	}

	public class WithdrawFromCamp implements ICommand {
		public void printOption() {
			System.out.println("Withdraw from camp");
		}

		public void execute(User user) {
			Student currentStudent = (Student) user;
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
	}
}
