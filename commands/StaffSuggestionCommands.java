package commands;

import java.util.ArrayList;

import camp.Camp;
import database.SuggestionDatabase;
import suggestion.Suggestion;
import ui.SuggestionUI;
import user.User;

public class StaffSuggestionCommands implements ICommandPackage {
	private static SuggestionUI suggestionUI = SuggestionUI.getInstance();

	private static SuggestionDatabase suggestionDatabase = SuggestionDatabase.getInstance();

	public ArrayList<ICommand> getCommands() {
		ArrayList<ICommand> commandList = new ArrayList<ICommand>();
		commandList.add(new ProcessSuggestions());
		return commandList;
	}

	public class ProcessSuggestions implements ICommand {
		public void printOption() {
			System.out.println("Process Suggestions");
		}

		public void execute(User user) {
			Camp myCamp = user.getOrganizingCamp();
			if (myCamp == null) {
				System.out.println("You do not have any camps.");
				return;
			}
			ArrayList<Suggestion> campSuggestions = suggestionDatabase.getSuggestionByCamp(myCamp);
			Suggestion selectedSuggestion;
			if ((selectedSuggestion = suggestionUI.chooseSuggestion(campSuggestions)) == null)
				return;
			suggestionUI.reviewSuggestion(selectedSuggestion);
		}

	}
}
