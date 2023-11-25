package user;

import java.util.ArrayList;
import camp.*;
import enquiry.*;

public class Student extends User {
	private ArrayList<Camp> myCamps = new ArrayList<Camp>();
	private ArrayList<Enquiry> myEnquiries = new ArrayList<Enquiry>();
	private Camp committeeCamp;

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
		if (camp == committeeCamp) {
			committeeCamp = null;
		}
	}

	public void addEnquiry(Enquiry enquiry) {
		myEnquiries.add(enquiry);
	}

	public void removeEnquiry(Enquiry enquiry) {
		myEnquiries.remove(enquiry);
	}

	public ArrayList<Enquiry> getEnquiries() {
		return myEnquiries;
	}

	public void removeCommitteeCamp() {
		committeeCamp = null;
	}

	public ArrayList<Camp> getMyCamps() {
		return this.myCamps;
	}
}
