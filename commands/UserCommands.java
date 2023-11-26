package commands;

import java.util.ArrayList;

import user.User;
import utils.ModifiedScanner;

public class UserCommands implements ICommandPackage {
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new ChangePassword());
		return commandList;
	}

	public class ChangePassword implements ICommand {
		public void printOption() {
			System.out.println("Change password");
		}

		public void execute(User user) {
			System.out.print("Enter new password: ");
			String newPassword = scanner.nextLine();
			while (true) {
				if (!(newPassword.equals(null) || newPassword.length() == 0)) {
					user.setPassword(newPassword);
					break;
				} else {
					System.out.print("New password cannot be empty! Try again: ");
					newPassword = scanner.nextLine();
				}
			}
			System.out.println("Password changed succesfully!");

		}

	}
}
