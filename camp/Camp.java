package camp;

import user.*;
import enquiry.Enquiry;
import java.util.*;

public class Camp {
	private CampInfo campInfo;
	private ArrayList<Student> attendeeList;
	private ArrayList<Student> committeeList;
	private int numAttendee = 0;
	private int numCommittee = 0;
	private boolean visible = true;
	private ArrayList<Enquiry> enquiryList;

	public Camp(CampInfo campInfo) {
		this.campInfo = campInfo;
		this.attendeeList = new ArrayList<>();
		this.committeeList = new ArrayList<>();
		this.enquiryList = new ArrayList<>();
	}

	public Camp(CampInfo campInfo, int numAttendee, int numCommittee, int visible) {
		this.campInfo = campInfo;
		this.attendeeList = new ArrayList<>();
		this.committeeList = new ArrayList<>();
		this.enquiryList = new ArrayList<>();
		this.numAttendee = numAttendee;
		this.numCommittee = numCommittee;
	}

	public CampInfo getCampInfo() {
		return campInfo;
	}

	public ArrayList<Student> getAttendeeList() {
		return attendeeList;
	}

	public boolean addAttendee(Student student) {
		if (numAttendee == this.campInfo.getNumAttendeeSlots()) {
			System.out.println("Attendee slots are full.");
			return false;
		}
		System.out.println("Successfully registered to camp");
		numAttendee += 1;
		attendeeList.add(student);
		student.addCamp(this);
		return true;
	}

	public boolean withdrawAttendee(Student student) {
		if (attendeeList.remove(student)) {
			System.out.println("Successfully removed from camp");
			numAttendee -= 1;
			student.removeCamp(this);
			return true;
		}
		System.out.println("Student is not inside this camp");
		return false;
	}

	public boolean addCommittee(Student student) {
		if (numCommittee == this.campInfo.getNumCommitteeSlots()) {
			System.out.println("Committee slots are full.");
			return false;
		}
		System.out.println("Successfully registered as committee");
		numCommittee += 1;
		committeeList.add(student);
		student.setCommitteeCamp(this);
		return true;
	}

	public boolean removeCommittee(Student student) {
		if (committeeList.remove(student)) {
			System.out.println("Successfully removed from camp");
			numCommittee -= 1;
			student.removeCamp(this);
			return true;
		}
		return false;
	}

	public void addEnquiry(Enquiry enquiry) {
		enquiryList.add(enquiry);
	}

	public void removeEnquiry(Enquiry enquiry) {
		enquiryList.remove(enquiry);
	}

	public ArrayList<Student> getCommitteeList() {
		return committeeList;
	}

	public int getNumCommittee() {
		return numCommittee;
	}

	public int getNumAttendee() {
		return numAttendee;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public ArrayList<Enquiry> getEnquiryList() {
		return enquiryList;
	}
}
