package camp;
import user.*;
import enquiry.Enquiry;
import java.util.*;

public class Camp {
	private CampInfo campInfo;
	private ArrayList<Student> attendeeList;
	private ArrayList<Student> committeeList;
	private int numStudents = 0;
	private int numCommittee = 0;
	private boolean visible = true;
	private ArrayList<Enquiry> enquiryList;

	public Camp(CampInfo campInfo) {
		this.campInfo = campInfo;
		this.attendeeList = new ArrayList<>();
		this.committeeList = new ArrayList<>();
		this.enquiryList = new ArrayList<>();
	}
	
	public Camp(CampInfo campInfo, int numStudents, int numCommittee, int visible) {
		this.campInfo = campInfo;
		this.attendeeList = new ArrayList<>();
		this.committeeList = new ArrayList<>();
		this.enquiryList = new ArrayList<>();
		this.numStudents = numStudents;
		this.numCommittee = numCommittee;
	}

	
	public CampInfo getCampInfo() {
		return campInfo;
	}

	public ArrayList<Student> getAttendeeList() {
		return attendeeList;
	}

	public boolean addAttendee(Student student){
		if(numCommittee == this.campInfo.getNumAttendeeSlots()){
			System.out.println("Attendee slots are full.");
			return false;
		}
		numCommittee += 1;
		attendeeList.add(student);
		return true;
	}

	public boolean addCommittee(Student student){
		if(numStudents == this.campInfo.getNumCommitteeSlots())
		{
			System.out.println("Committee slots are full.");
			return false;
		}
		numStudents += 1;
		committeeList.add(student);
		return true;
	}
	
	public ArrayList<Student> getCommitteeList() {
		return committeeList;
	}
	
	public int getNumStudents() {
		return numStudents;
	}
	
	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}
	
	public int getNumCommittee() {
		return numCommittee;
	}
	
	public void setNumCommittee(int numCommittee) {
		this.numCommittee = numCommittee;
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
