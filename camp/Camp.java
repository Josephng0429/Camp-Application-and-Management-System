package camp;

import user.*;

import java.util.*;

public class Camp {
	private CampInfo campInfo;
	private ArrayList<Student> attendeeList = new ArrayList<Student>();
	private ArrayList<Student> committeeList = new ArrayList<Student>();
	private int numAttendee = 0;
	private int numCommittee = 0;
	private boolean visible = true;

	public Camp(CampInfo campInfo) {
		this.campInfo = campInfo;
	}

	public Camp(CampInfo campInfo, int numAttendee, int numCommittee, int visible) {
		this.campInfo = campInfo;
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
		numAttendee += 1;
		attendeeList.add(student);
		student.addCamp(this);
		return true;
	}

	public boolean withdrawAttendee(Student student) {
		if (attendeeList.remove(student)) {
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
		numCommittee += 1;
		committeeList.add(student);
		student.setOrganizingCamp(this);
		return true;
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
}
