package database;

import camp.*;
import user.*;
import utils.CsvAdapter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CampDatabase {
	private static CampDatabase single_instance = null;
	private static ArrayList<Camp> campList = new ArrayList<>();
	private static CsvAdapter csvAdapter = CsvAdapter.getInstance();
	private static UserDatabase userDatabase = UserDatabase.getInstance();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

	public static CampDatabase getInstance() {
		if (single_instance == null) {
			single_instance = new CampDatabase();
		}
		return single_instance;
	}

	private CampDatabase() {
		// Initialize Camps
		loadDatabase();
	}

	public void loadDatabase() {
		ArrayList<ArrayList<String>> campDetails = csvAdapter.readCSVasArray("./camp_list.csv");
		for (ArrayList<String> campDetail : campDetails) {
			String name = campDetail.get(0);
			LocalDate startDate = LocalDate.parse(campDetail.get(1), formatter);
			LocalDate endDate = LocalDate.parse(campDetail.get(2), formatter);
			LocalDate closeDate = LocalDate.parse(campDetail.get(3), formatter);
			String faculty = campDetail.get(4);
			String location = campDetail.get(5);
			int totalSlots = Integer.parseInt(campDetail.get(6));
			int committeeSlots = Integer.parseInt(campDetail.get(7));
			String description = campDetail.get(8);
			Staff staffInCharge = (Staff) userDatabase.getUserByID(campDetail.get(9));
			CampInfo newCampInfo = new CampInfo(
					name,
					startDate,
					endDate,
					closeDate,
					faculty,
					location,
					totalSlots,
					committeeSlots,
					description,
					staffInCharge);
			Camp newCamp = new Camp(newCampInfo);
			campList.add(newCamp);
			staffInCharge.setOrganizingCamp(newCamp);
		}
		ArrayList<ArrayList<String>> allCampMembers = csvAdapter.readCSVasArray("./member_list.csv");
		for (ArrayList<String> memberDetails : allCampMembers) {
			String memberID = memberDetails.get(0);
			Student student = (Student) userDatabase.getUserByID(memberID);
			String campName = memberDetails.get(1);
			Camp camp = getCampByName(campName);
			String memberRole = memberDetails.get(2);
			if (memberRole.equals("attendee"))
				camp.addAttendee(student);
			else
				camp.addCommittee(student);

		}
	}

	public void saveDatabase() {
		ArrayList<ArrayList<String>> allCampDetails = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> allCampMembers = new ArrayList<ArrayList<String>>();
		for (Camp camp : campList) {
			// To store camp details
			ArrayList<String> campDetails = new ArrayList<String>();
			CampInfo campInfo = camp.getCampInfo();
			campDetails.add(campInfo.getCampName());
			campDetails.add(campInfo.getStartDate().format(formatter));
			campDetails.add(campInfo.getEndDate().format(formatter));
			campDetails.add(campInfo.getRegCloseDate().format(formatter));
			campDetails.add(campInfo.getCampFaculty());
			campDetails.add(campInfo.getLocation());
			campDetails.add(Integer.toString(campInfo.getTotalSlots()));
			campDetails.add(Integer.toString(campInfo.getNumCommitteeSlots()));
			campDetails.add(campInfo.getDescription());
			campDetails.add(campInfo.getStaffInCharge().getUserID());
			allCampDetails.add(campDetails);
			// To store member details
			for (User attendee : camp.getAttendeeList()) {
				ArrayList<String> memberDetails = new ArrayList<String>();
				memberDetails.add(attendee.getUserID());
				memberDetails.add(campInfo.getCampName());
				memberDetails.add("attendee");
				allCampMembers.add(memberDetails);
			}
			for (User attendee : camp.getCommitteeList()) {
				ArrayList<String> memberDetails = new ArrayList<String>();
				memberDetails.add(attendee.getUserID());
				memberDetails.add(campInfo.getCampName());
				memberDetails.add("committee");
				allCampMembers.add(memberDetails);
			}

		}
		csvAdapter.writeCSVfromArray(allCampDetails, "camp_list.csv");
		csvAdapter.writeCSVfromArray(allCampMembers, "member_list.csv");

	}

	public void addCamp(Camp camp) {
		campList.add(camp);
	}

	public void removeCamp(Camp camp) {
		campList.remove(camp);
	}

	public ArrayList<Camp> getAllCamps() {
		return campList;
	}

	public ArrayList<Camp> getVisibleCamps(String faculty) {
		ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
		for (Camp camp : campList) {
			if (camp.isVisible()) {
				CampInfo campInfo = camp.getCampInfo();
				String campFaculty = campInfo.getCampFaculty();
				if (campFaculty.equals(faculty) || campFaculty.equals("all"))
					filteredCamps.add(camp);
			}
		}
		return filteredCamps;
	}

	public Camp getCampByName(String campName) {
		for (Camp camp : campList) {
			if (camp.getCampInfo().getCampName().equals(campName))
				return camp;
		}
		return null;
	}

}
