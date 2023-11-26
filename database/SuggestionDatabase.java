package database;

import java.util.ArrayList;

import camp.Camp;
import camp.CampInfo;
import suggestion.Suggestion;
import suggestion.Suggestion.Status;
import user.Student;
import user.User;
import utils.CsvAdapter;

public class SuggestionDatabase {
	private static SuggestionDatabase single_instance = null;
	private static CampDatabase campDatabase = CampDatabase.getInstance();
	private static CsvAdapter csvAdapter = CsvAdapter.getInstance();
	private static UserDatabase userDatabase = UserDatabase.getInstance();
	private static ArrayList<Suggestion> suggestionList = new ArrayList<Suggestion>();

	public static SuggestionDatabase getInstance() {
		if (single_instance == null) {
			single_instance = new SuggestionDatabase();
		}
		return single_instance;
	}

	private SuggestionDatabase() {
	}

	public void addSuggestion(Suggestion suggestion) {
		suggestionList.add(suggestion);
	}

	public void removeSuggestion(Suggestion suggestion) {
		suggestionList.remove(suggestion);
	}

	public ArrayList<Suggestion> getSuggestionByCamp(Camp camp) {
		ArrayList<Suggestion> filteredSuggestion = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestionList) {
			if (suggestion.getCamp() == camp)
				filteredSuggestion.add(suggestion);
		}
		return filteredSuggestion;
	}

	public ArrayList<Suggestion> getSuggestionBySender(User sender) {
		ArrayList<Suggestion> filteredSuggestion = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestionList) {
			if (suggestion.getSender() == sender)
				filteredSuggestion.add(suggestion);
		}
		return filteredSuggestion;
	}

	public void loadDatabase() {
		ArrayList<ArrayList<String>> suggestionDetails = csvAdapter.readCSVasArray("./suggestion_list.csv");
		for (ArrayList<String> suggestionDetail : suggestionDetails) {
			Suggestion suggestion;
			String textBody = suggestionDetail.get(0);
			String senderID = suggestionDetail.get(1);
			Student sender = (Student) userDatabase.getUserByID(senderID);
			String statusString = suggestionDetail.get(2);
			Status status;
			if (statusString == "UNSEEN")
				status = Status.UNSEEN;
			else if (statusString == "APPROVED")
				status = Status.APPROVED;
			else
				status = Status.REJECTED;
			String campName = suggestionDetail.get(3);
			Camp camp = campDatabase.getCampByName(campName);
			User replier = camp.getCampInfo().getStaffInCharge();
			if (status == Status.UNSEEN)
				suggestion = new Suggestion(textBody, sender, camp);
			else
				suggestion = new Suggestion(textBody, sender, replier, status, camp);
			suggestionList.add(suggestion);
		}
	}

	public void saveDatabase() {
		ArrayList<ArrayList<String>> allSuggestions = new ArrayList<ArrayList<String>>();
		for (Suggestion suggestion : suggestionList) {
			ArrayList<String> suggestionDetails = new ArrayList<String>();
			suggestionDetails.add(suggestion.getTextBody());
			suggestionDetails.add(suggestion.getSender().getUserID());
			Status status = suggestion.getStatus();
			if (status == Status.UNSEEN)
				suggestionDetails.add("UNSEEN");
			else if (status == Status.APPROVED)
				suggestionDetails.add("APPROVED");
			else
				suggestionDetails.add("REJECTED");

			CampInfo campInfo = suggestion.getCamp().getCampInfo();
			suggestionDetails.add(campInfo.getCampName());
			allSuggestions.add(suggestionDetails);
		}
		csvAdapter.writeCSVfromArray(allSuggestions, "suggestion_list.csv");
	}

}
