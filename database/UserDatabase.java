package database;

import user.*;
import java.util.ArrayList;
import utils.CsvAdapter;

public class UserDatabase {
	private static CsvAdapter csvAdapter = CsvAdapter.getInstance();
	private static ArrayList<User> userList = new ArrayList<>(); // ArrayList of Users
	private static UserDatabase single_instance = null;

	public static UserDatabase getInstance() {
		if (single_instance == null) {
			single_instance = new UserDatabase();
		}
		return single_instance;
	}

	private UserDatabase() {
		ArrayList<ArrayList<String>> studentList = csvAdapter.readCSVasArray("./student_list.csv");
		ArrayList<ArrayList<String>> staffList = csvAdapter.readCSVasArray("./staff_list.csv");
		for (ArrayList<String> user : studentList) {
			User new_user = new Student(user.get(0), user.get(1), user.get(2), user.get(3));
			userList.add(new_user);
		}
		for (ArrayList<String> user : staffList) {
			User new_user = new Staff(user.get(0), user.get(1), user.get(2), user.get(3));
			userList.add(new_user);
		}
	}

	public void saveDatabase() {
		ArrayList<ArrayList<String>> studentList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> staffList = new ArrayList<ArrayList<String>>();
		for (User user : userList) {
			ArrayList<String> userDetails = new ArrayList<String>();
			userDetails.add(user.getName());
			userDetails.add(user.getPassword());
			userDetails.add(user.getEmail());
			userDetails.add(user.getFaculty());
			if (user instanceof Student)
				studentList.add(userDetails);
			else
				staffList.add(userDetails);
		}
		csvAdapter.writeCSVfromArray(studentList, "student_list.csv");
		csvAdapter.writeCSVfromArray(staffList, "staff_list.csv");
	}

	public User getUserByID(String id) {
		for (User user : userList) {
			if (user.getUserID().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public ArrayList<User> getStudents() {
		return userList;
	}
}
