package ui;

import java.util.ArrayList;

import camp.Camp;
import database.SuggestionDatabase;
import suggestion.Suggestion;
import suggestion.Suggestion.Status;
import user.User;
import utils.ModifiedScanner;

public class SuggestionUI {
	private static SuggestionUI suggestionUI = new SuggestionUI();
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();
	private static SuggestionDatabase suggestionDatabase = SuggestionDatabase.getInstance();

	private SuggestionUI() {
	}

	public static SuggestionUI getInstance() {
		return suggestionUI;
	}

	public Suggestion createSuggestion(User user, Camp camp) {
		System.out.println("Enter the suggestion message:");
		String message = scanner.nextLine();
		Suggestion suggestion = new Suggestion(message, user, camp);
		return suggestion;
	}

	public void editSuggestion(Suggestion suggestion) {
		if (suggestion.getStatus() != Status.UNSEEN) {
			System.out.println("Can't edit suggestion since it has already been responded to.");
			return;
		}
		System.out.println("Enter new suggestion message:");
		String message = scanner.nextLine();
		suggestion.setTextBody(message);
	}

	public void viewSuggestion(Suggestion suggestion) {
		System.out.printf("Senders name: %s \n", suggestion.getSender().getName());
		System.out.printf("Senders message: %s \n", suggestion.getTextBody());
		if (suggestion.getStatus() == Status.UNSEEN)
			System.out.println("This has not been replied yet.");
		else {
			System.out.printf("Reviewer's name: %s \n", suggestion.getReplier().getName());
			if (suggestion.getStatus() == Status.APPROVED)
				System.out.println("Suggestion approved!");
			else
				System.out.println("Suggestion rejected.");
		}
	}

	public Suggestion chooseSuggestion(ArrayList<Suggestion> suggestions) {
		int index = 0;
		for (Suggestion suggestion : suggestions) {
			index++;
			System.out.printf("---------\nChoice %d:\n---------\n", index);
			viewSuggestion(suggestion);
		}
		int input = 0;
		while (true) {
			System.out.print("Your choice (0 to exit):  ");
			input = scanner.nextInt();
			if (input == 0)
				return null;
			if (input > suggestions.size() || input < 0) {
				System.out.println("Invalid enquiry number, choose again.");
			} else {
				break;
			}
		}
		Suggestion selectedSuggestion = suggestions.get(input - 1);
		System.out.println("---------\nSuggestion selected:\n---------");
		viewSuggestion(selectedSuggestion);
		return selectedSuggestion;
	}

	public void deleteSuggestion(Suggestion suggestion) {
		if (suggestion.getStatus() != Status.UNSEEN) {
			System.out.println("Can't delete suggestion as it has already been responded to.");
			return;
		}
		suggestionDatabase.removeSuggestion(suggestion);
		System.out.println("Deleted suggestion");
	}

	public void reviewSuggestion(Suggestion suggestion) {
		Status status = suggestion.getStatus();
		if (status != Status.UNSEEN) {
			System.out.println("Suggestion has already been reviewed.");
			return;
		}
		System.out.println("------------------");
		System.out.println("Options: ");
		System.out.println("------------------");
		System.out.println("(0) Leave unseen");
		System.out.println("(1) Approve suggestion");
		System.out.println("(2) Reject suggestion");
		System.out.print("Select an option: ");
		int input = scanner.nextInt();
		while (true) {
			switch (input) {
				case 0:
					return;
				case 1:
					suggestion.setStatus(Status.APPROVED);
					return;
				case 2:
					suggestion.setStatus(Status.REJECTED);
					return;
				default:
					System.out.println("Invalid option.");
					System.out.print("Select an option: ");
					input = scanner.nextInt();
			}
		}
	}

	public void viewSuggestionList(ArrayList<Suggestion> suggestionList) {
		for (Suggestion suggestion : suggestionList) {
			viewSuggestion(suggestion);
		}
	}

}
