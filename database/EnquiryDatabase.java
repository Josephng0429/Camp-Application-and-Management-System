package database;

import java.util.ArrayList;

import camp.Camp;
import camp.CampInfo;
import enquiry.Enquiry;
import user.Student;
import user.User;
import utils.CsvAdapter;

public class EnquiryDatabase {
	private static EnquiryDatabase single_instance = null;
	private static CampDatabase campDatabase = CampDatabase.getInstance();
	private static CsvAdapter csvAdapter = CsvAdapter.getInstance();
	private static UserDatabase userDatabase = UserDatabase.getInstance();
	private static ArrayList<Enquiry> enquiryList = new ArrayList<Enquiry>();

	public static EnquiryDatabase getInstance() {
		if (single_instance == null) {
			single_instance = new EnquiryDatabase();
		}
		return single_instance;
	}

	private EnquiryDatabase() {
		loadDatabase();
	}

	public void loadDatabase() {
		ArrayList<ArrayList<String>> enquiryDetails = csvAdapter.readCSVasArray("./enquiry_list.csv");
		for (ArrayList<String> enquiryDetail : enquiryDetails) {
			Enquiry enquiry;
			String textBody = enquiryDetail.get(0);
			String senderID = enquiryDetail.get(1);
			Student sender = (Student) userDatabase.getUserByID(senderID);
			String replyBody = enquiryDetail.get(2);
			String replierID = enquiryDetail.get(3);
			String campName = enquiryDetail.get(4);
			Camp camp = campDatabase.getCampByName(campName);
			User replier = userDatabase.getUserByID(replierID);
			if (replier == null)
				enquiry = new Enquiry(textBody, sender, camp);
			else
				enquiry = new Enquiry(textBody, sender, replyBody, replier, camp);
			enquiryList.add(enquiry);
			camp.addEnquiry(enquiry);
			sender.addEnquiry(enquiry);
		}
	}

	public void saveDatabase() {
		ArrayList<ArrayList<String>> allEnquiries = new ArrayList<ArrayList<String>>();
		for (Enquiry enquiry : enquiryList) {
			ArrayList<String> enquiryDetails = new ArrayList<String>();
			enquiryDetails.add(enquiry.getTextBody());
			enquiryDetails.add(enquiry.getSender().getUserID());
			if (enquiry.isReplied()) {
				enquiryDetails.add(enquiry.getReplyBody());
				enquiryDetails.add(enquiry.getReplier().getUserID());
			} else {
				enquiryDetails.add("");
				enquiryDetails.add("");
			}
			CampInfo campInfo = enquiry.getCamp().getCampInfo();
			enquiryDetails.add(campInfo.getCampName());
			allEnquiries.add(enquiryDetails);
		}
		csvAdapter.writeCSVfromArray(allEnquiries, "enquiry_list.csv");
	}

	public void addEnquiry(Enquiry enquiry) {
		enquiryList.add(enquiry);
	}

	public void removeEnquiry(Enquiry enquiry) {
		enquiryList.remove(enquiry);
	}
}
