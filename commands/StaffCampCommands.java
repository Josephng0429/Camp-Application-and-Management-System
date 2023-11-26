package commands;

import java.util.ArrayList;

import camp.Camp;
import database.CampDatabase;
import ui.CampUI;
import user.Staff;
import user.User;

public class StaffCampCommands implements ICommandPackage {
	private static CampUI campUI = CampUI.getInstance();

	private static CampDatabase campDatabase = CampDatabase.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new CreateCamp());
		commandList.add(new ViewMyCamp());
		commandList.add(new ViewAllCamps());
		commandList.add(new DeleteCamp());
		commandList.add(new EditCamp());
		commandList.add(new SetCampVisibility());
		return commandList;
	}

	public class CreateCamp implements ICommand {
		public void printOption() {
			System.out.println("Create camp");
		}

		public void execute(User user) {
			if (user.getOrganizingCamp() != null) {
				System.out.println("You can only create one camp.");
				return;
			}
			Staff currentStaff = (Staff) user;
			Camp newCamp = campUI.createCamp(currentStaff);
			currentStaff.setOrganizingCamp(newCamp);
			campDatabase.addCamp(newCamp);
			System.out.println("Added new camp");
		}

	}

	public class ViewMyCamp implements ICommand {
		public void printOption() {
			System.out.println("View my camp");
		}

		public void execute(User user) {
			if (user.getOrganizingCamp() == null) {
				System.out.println("You do not have any camps.");
				return;
			}
			Camp myCamp = user.getOrganizingCamp();
			campUI.viewCamp(myCamp);
		}
	}

	public class ViewAllCamps implements ICommand {
		public void printOption() {
			System.out.println("View all camps");
		}

		public void execute(User user) {
			ArrayList<Camp> allCamps = campDatabase.getAllCamps();
			campUI.viewCampList(allCamps);
		}
	}

	public class EditCamp implements ICommand {
		public void printOption() {
			System.out.println("Edit camps");

		}

		public void execute(User user) {
			if (user.getOrganizingCamp() == null) {
				System.out.println("You do not have any camps.");
				return;
			}
			Staff currentStaff = (Staff) user;
			Camp camp = currentStaff.getOrganizingCamp();
			campUI.editCamp(camp);

		}
	}

	public class DeleteCamp implements ICommand {
		public void printOption() {
			System.out.println("Delete camp");

		}

		public void execute(User user) {
			if (user.getOrganizingCamp() == null) {
				System.out.println("You do not have any camps.");
				return;
			}
			Staff staff = (Staff) user;
			Camp camp = user.getOrganizingCamp();
			if (camp.getNumAttendee() > 0 || camp.getNumCommittee() > 0) {
				System.out.println("Can't delete camp as people have joined.");
				return;
			}
			staff.removeOrganizingCamp();
			campDatabase.removeCamp(camp);
			System.out.println("Deleting camp.");

		}
	}

	public class SetCampVisibility implements ICommand {
		public void printOption() {
			System.out.println("Set camp visibility");
		}

		public void execute(User user) {
			if (user.getOrganizingCamp() == null) {
				System.out.println("You do not have any camps.");
				return;
			}
			Camp myCamp = user.getOrganizingCamp();
			campUI.setCampVisibility(myCamp);
		}
	}
}
