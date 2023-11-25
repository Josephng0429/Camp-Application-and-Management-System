package ui;

import utils.ModifiedScanner;
import user.Staff;
import camp.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.CampDatabase;

public class CampUI {
	private static CampUI campUI = new CampUI();
	private static CampDatabase campDatabase = CampDatabase.getInstance();
	private static ModifiedScanner scanner = ModifiedScanner.getInstance();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

	private CampUI() {
	}

	public static CampUI getInstance() {
		return campUI;
	}

	public void viewCamp(Camp camp) {
		CampInfo campInfo = camp.getCampInfo();
		System.out.printf("Camp name: %s \n", campInfo.getCampName());
		System.out.printf("Start date: %s \n", campInfo.getStartDate().format(formatter));
		System.out.printf("End date: %s \n", campInfo.getEndDate().format(formatter));
		System.out.printf("Registration closing date: %s \n", campInfo.getRegCloseDate().format(formatter));
		System.out.printf("Faculty: %s \n", campInfo.getCampFaculty());
		System.out.printf("Location: %s \n", campInfo.getLocation());
		System.out.printf("Attendee Slots: %d/%d \n", camp.getNumAttendee(), campInfo.getNumAttendeeSlots());
		System.out.printf("Committee Slots: %d/%d \n", camp.getNumCommittee(), campInfo.getNumCommitteeSlots());
		System.out.printf("Description: %s \n", campInfo.getDescription());
	}

	public Camp createCamp(Staff currentStaff) {
		LocalDate campStartDate, campEndDate, campClosingDate;
		String campName;
		int campTotalSlots;
		int campCommitteeSlots;
		System.out.println("Creating a camp");
		while (true) {
			System.out.println("Enter camp name:");
			campName = scanner.nextLine();
			if (campDatabase.getCampByName(campName) != null)
				System.out.println("Camp name has been taken");
			else
				break;
		}
		System.out.println("For the camp start date");
		campStartDate = scanner.nextLocalDate();
		while (true) {
			System.out.println("For the camp end date");
			campEndDate = scanner.nextLocalDate();
			if (campEndDate.isBefore(campStartDate))
				System.out.println("Camp end date must be before the camp start date");
			else
				break;
		}
		while (true) {
			System.out.println("For the camp registration closing date");
			campClosingDate = scanner.nextLocalDate();
			if (campClosingDate.isAfter(campStartDate))
				System.out.println("Camp registration closing date must be before the camp start date");
			else
				break;
		}
		System.out.println("Enter camp faculty (type all if no preference)");
		String campFaculty = scanner.nextLine();
		System.out.println("Enter camp location");
		String campLocation = scanner.nextLine();
		while (true) {
			System.out.println("Enter camp total slots");
			campTotalSlots = scanner.nextInt();
			if (campTotalSlots < 0)
				System.out.println("Invalid camp slots");
			else
				break;
		}
		while (true) {
			System.out.println("Enter camp committee slots");
			campCommitteeSlots = scanner.nextInt();
			if (campCommitteeSlots >= campTotalSlots)
				System.out.println("Committee slots should be lesser than total slots");
			else if (campCommitteeSlots > 10)
				System.out.println("There cannot be more than 10 committee slots");
			else
				break;
		}
		System.out.println("Enter camp description");
		String campDescription = scanner.nextLine();
		Staff campStaffInCharge = currentStaff;
		CampInfo newCampInfo = new CampInfo(
				campName,
				campStartDate,
				campEndDate,
				campClosingDate,
				campFaculty,
				campLocation,
				campTotalSlots,
				campCommitteeSlots,
				campDescription,
				campStaffInCharge);
		return new Camp(newCampInfo);
	}

	public void viewCampList(ArrayList<Camp> campList) {
		for (Camp camp : campList) {
			viewCamp(camp);
		}
	}

	public Camp chooseCamp(ArrayList<Camp> camps) {
		int index = 0;
		for (Camp camp : camps) {
			index++;
			System.out.printf("---------\nChoice %d:\n---------\n", index);
			viewCamp(camp);
		}
		int input = 0;
		while (true) {
			System.out.print("Your choice (0 to exit):  ");
			input = scanner.nextInt();
			if (input == 0)
				return null;
			if (input > camps.size() || input < 0) {
				System.out.println("Invalid camp number, choose again.");
			} else {
				break;
			}
		}
		Camp selectedCamp = camps.get(input - 1);
		System.out.println("---------\nCamp selected:\n---------");
		campUI.viewCamp(selectedCamp);
		return selectedCamp;

	}
}
