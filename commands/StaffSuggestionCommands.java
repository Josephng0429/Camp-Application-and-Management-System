package commands;

import java.util.ArrayList;

import camp.Camp;
import database.SuggestionDatabase;
import suggestion.Suggestion;
import ui.CampUI;
import ui.SuggestionUI;
import user.Staff;
import user.User;

public class StaffSuggestionCommands implements ICommandPackage {
	private static CampUI campUI = CampUI.getInstance();
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
			Staff currentStaff = (Staff) user;
			ArrayList<Camp> myCamps = currentStaff.getMyCamps();
			Camp selectedCamp;
			if ((selectedCamp = campUI.chooseCamp(myCamps)) == null)
				return;
			ArrayList<Suggestion> campSuggestions = suggestionDatabase.getSuggestionByCamp(selectedCamp);
			Suggestion selectedSuggestion;
			if ((selectedSuggestion = suggestionUI.chooseSuggestion(campSuggestions)) == null)
				return;
			suggestionUI.reviewSuggestion(selectedSuggestion);
		}

	}
}
