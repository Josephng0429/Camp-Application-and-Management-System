package commands;

import java.util.ArrayList;

import camp.Camp;
import user.Student;
import user.User;
import utils.ReportWriter;

public class StaffReportCommands implements ICommandPackage {
	private static ReportWriter reportWriter = ReportWriter.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new StaffReport());
		return commandList;
	}

	public class StaffReport implements ICommand {
		public void printOption() {
			System.out.println("Generate detailed report of camp commitee");
		}

		public void execute(User currentUser) {
			Camp camp = currentUser.getOrganizingCamp();
			String fileName = "committee_report.txt";
			if (camp == null) {
				System.out.println("You do not have a camp.");
				return;
			}
			System.out.println("Generating a detailed report");
			reportWriter.writeCampInfo(camp, fileName);
			reportWriter.writeLine("Committee List", fileName);
			reportWriter.writeLine("-----------------", fileName);
			if (camp.getCommitteeList().size() == 0) {
				reportWriter.writeLine("No commitee members.", fileName);

			}
			for (Student student : camp.getCommitteeList()) {
				reportWriter.writeCommitteeInfo(student, fileName);
			}
			System.out.printf("Successfully generated report at %s\n", fileName);
		}

	}
}
