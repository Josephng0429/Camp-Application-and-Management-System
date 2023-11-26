package commands;

import java.util.ArrayList;

import camp.Camp;
import database.SuggestionDatabase;
import suggestion.Suggestion;
import ui.SuggestionUI;
import user.Student;
import user.User;
import utils.ModifiedScanner;

public class CommitteeSuggestionCommands implements ICommandPackage {
	private static SuggestionUI suggestionUI = SuggestionUI.getInstance();
	private static SuggestionDatabase suggestionDatabase = SuggestionDatabase.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new SubmitSuggestion());
		commandList.add(new EditSuggestion());
		commandList.add(new DeleteSuggestion());
		commandList.add(new ViewSuggestions());
		return commandList;
	}

	public class SubmitSuggestion implements ICommand {
		public void printOption() {
			System.out.println("Submit a suggestion");
		}

		public void execute(User user) {
			Camp camp = user.getOrganizingCamp();
			Suggestion suggestion = suggestionUI.createSuggestion(user, camp);
			System.out.print(suggestion);
			suggestionDatabase.addSuggestion(suggestion);

		}

	}

	public class EditSuggestion implements ICommand {
		public void printOption() {
			System.out.println("Edit a suggestion");
		}

		public void execute(User user) {
			ArrayList<Suggestion> mySuggestions = suggestionDatabase.getSuggestionBySender(user);

			Suggestion selectedSuggestion;
			if ((selectedSuggestion = suggestionUI.chooseSuggestion(mySuggestions)) == null)
				return;
			suggestionUI.editSuggestion(selectedSuggestion);

		}

	}

	public class DeleteSuggestion implements ICommand {
		public void printOption() {
			System.out.println("Delete a suggestion");
		}

		public void execute(User user) {
			ArrayList<Suggestion> mySuggestions = suggestionDatabase.getSuggestionBySender(user);

			Suggestion selectedSuggestion;
			if ((selectedSuggestion = suggestionUI.chooseSuggestion(mySuggestions)) == null)
				return;
			suggestionUI.deleteSuggestion(selectedSuggestion);

		}

	}

	public class ViewSuggestions implements ICommand {
		public void printOption() {
			System.out.println("View my suggestions");
		}

		public void execute(User user) {
			ArrayList<Suggestion> mySuggestions = suggestionDatabase.getSuggestionBySender(user);
			suggestionUI.viewSuggestionList(mySuggestions);

		}

	}
}
