package user;

import java.util.ArrayList;
import camp.*;
import enquiry.*;
import suggestion.Suggestion;

public class Student extends User {
	private ArrayList<Camp> myCamps = new ArrayList<Camp>();
	private ArrayList<Enquiry> myEnquiries = new ArrayList<Enquiry>();
	private ArrayList<Suggestion> mySuggestions = new ArrayList<Suggestion>();
	private Camp committeeCamp;
	private int point = 0;

	public Student(String name, String password, String email, String faculty) {
		super(name, password, email, faculty);
		this.committeeCamp = null;
	}

	public void setCommitteeCamp(Camp committeeCamp) {
		this.committeeCamp = committeeCamp;
	}

	public Camp getCommitteeCamp() {
		return this.committeeCamp;
	}

	public void addCamp(Camp camp) {
		myCamps.add(camp);
	}

	public void removeCamp(Camp camp) {
		myCamps.remove(camp);
	}

	public void addEnquiry(Enquiry enquiry) {
		myEnquiries.add(enquiry);
	}

	public void removeEnquiry(Enquiry enquiry) {
		myEnquiries.remove(enquiry);
	}

	public ArrayList<Suggestion> getSuggestions() {
		return mySuggestions;
	}

	public void addSuggestion(Suggestion suggestion) {
		mySuggestions.add(suggestion);
	}

	public void removeSuggestion(Suggestion suggestion) {
		mySuggestions.remove(suggestion);
	}

	public ArrayList<Enquiry> getEnquiries() {
		return myEnquiries;
	}

	public ArrayList<Camp> getMyCamps() {
		return this.myCamps;
	}
}
