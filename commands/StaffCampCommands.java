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
		commandList.add(new createCamp());
		commandList.add(new viewMyCamps());
		commandList.add(new viewAllCamps());
		commandList.add(new deleteCamp());
		commandList.add(new editCamp());
		commandList.add(new setCampVisibility());
		return commandList;
	}

	public class createCamp implements ICommand {
		public void printOption() {
			System.out.println("Create camp");
		}

		public void execute(User user) {
			Staff currentStaff = (Staff) user;
			Camp newCamp = campUI.createCamp(currentStaff);
			currentStaff.addCamp(newCamp);
			campDatabase.addCamp(newCamp);
			System.out.println("Added new camp");
		}

	}

	public class viewMyCamps implements ICommand {
		public void printOption() {
			System.out.println("View my camps");
		}

		public void execute(User user) {
			Staff currentStaff = (Staff) user;
			ArrayList<Camp> myCamps = currentStaff.getMyCamps();
			campUI.viewCampList(myCamps);
		}
	}

	public class viewAllCamps implements ICommand {
		public void printOption() {
			System.out.println("View all camps");
		}

		public void execute(User user) {
			ArrayList<Camp> allCamps = campDatabase.getAllCamps();
			campUI.viewCampList(allCamps);
		}
	}

	public class editCamp implements ICommand {
		public void printOption() {
			System.out.println("Edit camps");

		}

		public void execute(User user) {
			Staff currentStaff = (Staff) user;
			ArrayList<Camp> myCamps = currentStaff.getMyCamps();
			Camp selectedCamp;
			if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
				return;
			campUI.editCamp(selectedCamp);

		}
	}

	public class deleteCamp implements ICommand {
		public void printOption() {
			System.out.println("Delete camp");

		}

		public void execute(User user) {
			Staff currentStaff = (Staff) user;
			ArrayList<Camp> myCamps = currentStaff.getMyCamps();
			Camp selectedCamp;
			if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
				return;
			campUI.deleteCamp(selectedCamp);

		}
	}

	public class setCampVisibility implements ICommand {
		public void printOption() {
			System.out.println("Set camp visibility");
		}

		public void execute(User user) {
			Staff currentStaff = (Staff) user;
			ArrayList<Camp> myCamps = currentStaff.getMyCamps();
			Camp selectedCamp;
			if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
				return;
			campUI.setCampVisibility(selectedCamp);
		}
	}
}
