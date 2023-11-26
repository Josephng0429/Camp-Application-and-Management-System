package commands;

import java.util.ArrayList;

import camp.Camp;
import user.Student;
import user.User;
import utils.ModifiedScanner;
import utils.ReportWriter;

public class OrganizerReportCommands implements ICommandPackage {
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();
	private static ReportWriter reportWriter = ReportWriter.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new OrganizerReport());
		return commandList;
	}

	public class OrganizerReport implements ICommand {
		public void printOption() {
			System.out.println("Generate committee members report");
		}

		public void execute(User currentUser) {
			Camp camp = currentUser.getOrganizingCamp();
			String fileName = "report.txt";
			if (camp == null) {
				System.out.println("You do not have a camp.");
				return;
			}
			System.out.println("------------------");
			System.out.println(" Report Options:  ");
			System.out.println("------------------");
			System.out.println("(1)  Generate full report");
			System.out.println("(2)  Generate report on attendee");
			System.out.println("(3)  Generate report on commitee");
			System.out.println("(0)  Exit without generating report");
			int input = scanner.nextInt();
			switch (input) {
				case 0:
					return;
				case 1:
					System.out.println("Generating full report");
					reportWriter.writeCampInfo(camp, fileName);
					reportWriter.writeLine("Attendee List", fileName);
					for (Student student : camp.getAttendeeList()) {
						reportWriter.writeStudentInfo(student, fileName);
					}
					reportWriter.writeLine("Committee List", fileName);
					for (Student student : camp.getCommitteeList()) {
						reportWriter.writeStudentInfo(student, fileName);
					}
					break;
				case 2:
					System.out.println("Generating attendee report");
					reportWriter.writeCampInfo(camp, fileName);
					reportWriter.writeLine("Attendee List", fileName);
					for (Student student : camp.getAttendeeList()) {
						reportWriter.writeStudentInfo(student, fileName);
					}
					break;
				case 3:
					System.out.println("Generating committee report");
					reportWriter.writeCampInfo(camp, fileName);
					reportWriter.writeLine("Committee List", fileName);
					for (Student student : camp.getCommitteeList()) {
						reportWriter.writeStudentInfo(student, fileName);
					}
					break;
				default:
					System.out.println("Invalid option.");
					return;
			}
			System.out.printf("Successfully generated report at %s\n", fileName);

		}

	}
}
