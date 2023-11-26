package user;

import java.util.ArrayList;
import camp.*;

public class Student extends User {
	private ArrayList<Camp> myCamps = new ArrayList<Camp>();
	private Camp committeeCamp;
	private int points = 0;

	public Student(String name, String password, String email, String faculty) {
		super(name, password, email, faculty);
		this.committeeCamp = null;
	}

	public void setOrganizingCamp(Camp committeeCamp) {
		this.committeeCamp = committeeCamp;
	}

	public Camp getOrganizingCamp() {
		return this.committeeCamp;
	}

	public void addCamp(Camp camp) {
		myCamps.add(camp);
	}

	public void removeCamp(Camp camp) {
		myCamps.remove(camp);
	}

	public int getPoints() {
		return points;
	}

	public ArrayList<Camp> getMyCamps() {
		return this.myCamps;
	}
}
