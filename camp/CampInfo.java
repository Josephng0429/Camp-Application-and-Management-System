package camp;

import user.*;
import java.time.LocalDate;

import database.CampDatabase;

public class CampInfo {
	private static CampDatabase campDatabase = CampDatabase.getInstance();
	private String campName;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate regCloseDate;
	private String campFaculty;
	private String location;
	private int numAttendeeSlots;
	private int numCommitteeSlots;
	private String description;
	private Staff staffInCharge;

	public CampInfo(String campName, LocalDate startDate, LocalDate endDate, LocalDate regCloseDate, String campFaculty,
			String location, int totalSlots, int numCommitteeSlots, String description, Staff staffInCharge) {
		this.campName = campName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regCloseDate = regCloseDate;
		this.campFaculty = campFaculty;
		this.location = location;
		this.numAttendeeSlots = totalSlots - numCommitteeSlots;
		this.numCommitteeSlots = numCommitteeSlots;
		this.description = description;
		this.staffInCharge = staffInCharge;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		if (campDatabase.getCampByName(campName) != null)
			System.out.println("Camp name has been taken");
		else
			this.campName = campName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		if (startDate.isAfter(this.endDate))
			System.out.println("Camp start date must be before end date.");
		else
			this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		if (endDate.isBefore(this.startDate))
			System.out.println("Camp end date must be after start date.");
		else
			this.endDate = endDate;
	}

	public LocalDate getRegCloseDate() {
		return regCloseDate;
	}

	public void setRegCloseDate(LocalDate regCloseDate) {
		if (regCloseDate.isAfter(this.startDate))
			System.out.println("Camp registration end date must be before start date.");
		else
			this.regCloseDate = regCloseDate;
	}

	public String getCampFaculty() {
		return campFaculty;
	}

	public void setCampFaculty(String campFaculty) {
		this.campFaculty = campFaculty;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumAttendeeSlots() {
		return numAttendeeSlots;
	}

	public void setNumAttendeeSlots(int attendeeSlots) {
		this.numAttendeeSlots = attendeeSlots;
	}

	public int getNumCommitteeSlots() {
		return numCommitteeSlots;
	}

	public void setNumCommitteeSlots(int numCommitteeSlots) {
		if (numCommitteeSlots > 10) {
			System.out.println("There cannot be more than 10 committee slots");
		}
		this.numCommitteeSlots = numCommitteeSlots;
	}

	public String getDescription() {
		return description;
	}

	public int getTotalSlots() {
		return numCommitteeSlots + numAttendeeSlots;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Staff getStaffInCharge() {
		return staffInCharge;
	}

	public void setStaffInCharge(Staff staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

}
