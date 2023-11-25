package user;

import camp.*;
import enquiry.*;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Staff extends User {
	private ArrayList<Camp> myCamps;

	public Staff(String name, String password, String email, String faculty) {
		super(name, password, email, faculty);
		this.myCamps = new ArrayList<>();
	}

	public ArrayList<Camp> getMyCamps() {
		return myCamps;
	}

	public void addCamp(Camp camp) {
		myCamps.add(camp);
	}

	public void removeCamp(Camp camp) {
		myCamps.remove(camp);
	}

	public void generatePerformanceReport(ArrayList<Camp> myCamps) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Generate Performance Report");
		System.out.println("1. Attendees");
		System.out.println("2. Camp Committee Members");
		System.out.print("Select an option: ");
		int option = scanner.nextInt();

		switch (option) {
			case 1:
				generateReportForRole(myCamps, "attendee");
				break;
			case 2:
				generateReportForRole(myCamps, "camp committee");
				break;
			default:
				System.out.println("Invalid option.");
				break;
		}
	}

	private void generateReportForRole(ArrayList<Camp> myCamps, String role) {
		System.out.println("Generating Performance Report for " + role + "s");

		try {
			FileWriter writer = new FileWriter("performance_report_" + role + ".csv");
			writer.append("Camp Name,Start Date,End Date,Location,Total Slots,Num Committee Slots,Role\n");

			for (Camp camp : myCamps) {
				for (Student participant : getParticipantsForRole(camp, role)) {
					writeParticipantInfo(writer, camp, participant, role);
				}
			}

			writer.flush();
			writer.close();
			System.out.println("Performance Report generated successfully.");
		} catch (IOException e) {
			System.out.println("Error generating the report.");
			e.printStackTrace();
		}
	}

	private ArrayList<Student> getParticipantsForRole(Camp camp, String role) {
		ArrayList<Student> participants = new ArrayList<>();
		if (role.equalsIgnoreCase("attendee")) {
			participants.addAll(camp.getAttendeeList());
		} else if (role.equalsIgnoreCase("camp committee")) {
			participants.addAll(camp.getCommitteeList());
		}
		return participants;
	}

	private void writeParticipantInfo(FileWriter writer, Camp camp, Student participant, String role)
			throws IOException {
		writer.append(String.format("%s,%s,%s,%s,%d,%d,%s\n",
				camp.getCampInfo().getCampName(),
				camp.getCampInfo().getStartDate(),
				camp.getCampInfo().getEndDate(),
				camp.getCampInfo().getLocation(),
				camp.getCampInfo().getTotalSlots(),
				camp.getCampInfo().getNumCommitteeSlots(),
				role));
	}

}
