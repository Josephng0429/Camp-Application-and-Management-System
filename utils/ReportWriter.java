package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import camp.Camp;
import camp.CampInfo;
import user.Student;

public class ReportWriter {
	private static ReportWriter reportWriter = new ReportWriter();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

	private ReportWriter() {
	};

	public static ReportWriter getInstance() {
		return reportWriter;
	}

	public void writeCampInfo(Camp camp, String filename) {
		try {
			CampInfo campInfo = camp.getCampInfo();
			FileWriter fr = new FileWriter(filename, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write("Camp details:");
			br.newLine();
			br.write("---------------------------------------------");
			br.newLine();
			br.write(String.format("Camp Name: %s", campInfo.getCampName()));
			br.newLine();
			br.write(String.format("Camp Start Date: %s", campInfo.getStartDate().format(formatter)));
			br.newLine();
			br.write(String.format("Camp End Date: %s", campInfo.getEndDate().format(formatter)));
			br.newLine();
			br.write(String.format("Registration Closing Date: %s", campInfo.getRegCloseDate().format(formatter)));
			br.newLine();
			br.write(String.format("Camp Faculty: %s", campInfo.getCampFaculty()));
			br.newLine();
			br.write(String.format("Camp Location: %s", campInfo.getLocation()));
			br.newLine();
			br.write(String.format("Attendee Slots: %d/%d", camp.getNumAttendee(), campInfo.getNumAttendeeSlots()));
			br.newLine();
			br.write(String.format("Committee Slots: %d/%d \n", camp.getNumCommittee(),
					campInfo.getNumCommitteeSlots()));
			br.newLine();
			br.write(String.format("Description: %s \n", campInfo.getDescription()));
			br.newLine();
			br.write("---------------------------------------------");
			br.newLine();
			br.close();
			fr.close();
			System.out.println("Performance Report generated successfully.");
		} catch (IOException e) {
			System.out.println("Error generating the report.");
			e.printStackTrace();
		}
	}

	public void writeLine(String message, String filename) {
		try {
			FileWriter fr = new FileWriter(filename, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write(message);
			br.newLine();
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("Error generating the report.");
			e.printStackTrace();
		}
	}

	public void writeStudentInfo(Student student, String filename) {
		try {
			FileWriter fr = new FileWriter(filename, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write("---------------------------------------------");
			br.newLine();
			br.write(String.format("Student Name : %s", student.getName()));
			br.newLine();
			br.write(String.format("Student Faculty : %s", student.getFaculty()));
			br.newLine();
			br.write("---------------------------------------------");
			br.newLine();
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("Error generating the report.");
			e.printStackTrace();
		}
	}

	public void writeCommitteeInfo(Student student, String filename) {
		try {
			FileWriter fr = new FileWriter(filename, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write("---------------------------------------------");
			br.newLine();
			br.write(String.format("Committee Member Name : %s", student.getName()));
			br.newLine();
			br.write(String.format("Committee Member Faculty : %s", student.getFaculty()));
			br.newLine();
			br.write(String.format("Committee Member Points : %d", student.getPoints()));
			br.newLine();
			br.write("---------------------------------------------");
			br.newLine();
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("Error generating the report.");
			e.printStackTrace();
		}
	}
}
