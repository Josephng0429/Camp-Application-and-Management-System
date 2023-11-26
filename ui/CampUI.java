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
		if (camp == null) {
			System.out.println("No camp exists.");
			return;
		}
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

	public void setCampVisibility(Camp camp) {
		if (camp.getNumAttendee() > 0 || camp.getNumCommittee() > 0) {
			System.out.println("Can't modify camp visiblity as people have joined.");
			return;
		}
		System.out.println("Set camp visibility");
		System.out.println("(1)  Visible");
		System.out.println("(2)  Hidden");
		int input = scanner.nextInt();
		switch (input) {
			case 1:
				camp.setVisible(true);
				break;
			case 2:
				camp.setVisible(false);
				break;
			default:
				System.out.println("Invalid option.");
				break;
		}
	}

	public Camp createCamp(Staff currentStaff) {
		LocalDate campStartDate, campEndDate, campClosingDate;
		String campName;
		int campTotalSlots;
		int campCommitteeSlots;
		System.out.println("Creating a camp");
		while (true) {
			System.out.print("Enter camp name: ");
			campName = scanner.nextLine();
			if (campDatabase.getCampByName(campName) != null)
				System.out.println("Camp name has been taken");
			else
				break;
		}
		System.out.println("For the camp start date:");
		campStartDate = scanner.nextLocalDate();
		while (true) {
			System.out.println("For the camp end date:");
			campEndDate = scanner.nextLocalDate();
			if (campEndDate.isBefore(campStartDate))
				System.out.println("Camp end date must be after the camp start date");
			else
				break;
		}
		while (true) {
			System.out.println("For the camp registration closing date:");
			campClosingDate = scanner.nextLocalDate();
			if (campClosingDate.isAfter(campStartDate))
				System.out.println("Camp registration closing date must be before the camp start date");
			else
				break;
		}
		System.out.print("Enter camp faculty (type all if no preference): ");
		String campFaculty = scanner.nextLine();
		System.out.print("Enter camp location: ");
		String campLocation = scanner.nextLine();
		while (true) {
			System.out.print("Enter camp total slots: ");
			campTotalSlots = scanner.nextInt();
			if (campTotalSlots < 0)
				System.out.println("Invalid camp slots");
			else
				break;
		}
		while (true) {
			System.out.print("Enter camp committee slots: ");
			campCommitteeSlots = scanner.nextInt();
			if (campCommitteeSlots >= campTotalSlots)
				System.out.println("Committee slots should be lesser than total slots");
			else if (campCommitteeSlots > 10)
				System.out.println("There cannot be more than 10 committee slots");
			else
				break;
		}
		System.out.print("Enter camp description: ");
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

	public void editCamp(Camp camp) {
		if (camp.getNumAttendee() > 0 || camp.getNumCommittee() > 0) {
			System.out.println("Can't modify camp as people have joined.");
			return;
		}
		System.out.println("------------------");
		System.out.println(" Edit Options: ");
		System.out.println("------------------");
		System.out.println("(1)  Change camp name");
		System.out.println("(2)  Change camp start date");
		System.out.println("(3)  Change camp end date");
		System.out.println("(4)  Change camp closing date");
		System.out.println("(5)  Change camp faculty");
		System.out.println("(6)  Change camp location");
		System.out.println("(7)  Change camp total slots");
		System.out.println("(8)  Change camp committee slots");
		System.out.println("(9)  Change camp description");
		System.out.println("(0) Exit without making changes");
		int input = scanner.nextInt();
		CampInfo campInfo = camp.getCampInfo();
		switch (input) {
			case 1:
				System.out.print("Input new camp name: ");
				String campName = scanner.nextLine();
				campInfo.setCampName(campName);
				break;
			case 2:
				System.out.print("Input new start date: ");
				LocalDate startDate = scanner.nextLocalDate();
				campInfo.setStartDate(startDate);
				break;
			case 3:
				System.out.print("Input new end date: ");
				LocalDate endDate = scanner.nextLocalDate();
				campInfo.setEndDate(endDate);
				break;
			case 4:
				System.out.print("Input new registration closing date: ");
				LocalDate closingDate = scanner.nextLocalDate();
				campInfo.setRegCloseDate(closingDate);
				break;
			case 5:
				System.out.print("Input new faculty: ");
				String faculty = scanner.nextLine();
				campInfo.setCampFaculty(faculty);
				break;
			case 6:
				System.out.print("Input new location: ");
				String location = scanner.nextLine();
				campInfo.setLocation(location);
				break;
			case 7:
				System.out.print("Input new total slots: ");
				int newTotalSlots = scanner.nextInt();
				int committeeSlots = campInfo.getNumCommitteeSlots();
				if (committeeSlots >= newTotalSlots)
					System.out.println("Committee slots should be lesser than total slots");
				else {
					campInfo.setNumAttendeeSlots(newTotalSlots - committeeSlots);
				}
				break;
			case 8:
				System.out.print("Input new committee slots: ");
				int newCommitteeSlots = scanner.nextInt();
				int totalSlots = campInfo.getNumCommitteeSlots() + campInfo.getNumAttendeeSlots();
				if (newCommitteeSlots >= totalSlots)
					System.out.println("Committee slots should be lesser than total slots");
				else {
					campInfo.setNumAttendeeSlots(totalSlots - newCommitteeSlots);
				}
				break;
			case 9:
				System.out.print("Input new description: ");
				String description = scanner.nextLine();
				campInfo.setDescription(description);
				break;
			case 0:
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}

	public void viewCampList(ArrayList<Camp> campList) {
		if (campList.size() == 0) {
			System.out.println("No camps to see");
			return;
		}
		int index = 0;
		for (Camp camp : campList) {
			index++;
			System.out.printf("--------------\nCamp number %d:\n--------------\n", index);
			viewCamp(camp);
		}
	}

	public Camp chooseCamp(ArrayList<Camp> camps) {
		if (camps.size() == 0) {
			System.out.println("No camps to choose from");
			return null;
		}
		int index = 0;
		for (Camp camp : camps) {
			index++;
			System.out.printf("--------------\nChoice %d:\n--------------\n", index);
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
