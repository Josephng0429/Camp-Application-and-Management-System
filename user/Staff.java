package user;

import camp.*;

public class Staff extends User {
	private Camp myCamp;

	public Staff(String name, String password, String email, String faculty) {
		super(name, password, email, faculty);
	}

	public Camp getOrganizingCamp() {
		return myCamp;
	}

	public void setOrganizingCamp(Camp camp) {
		myCamp = camp;
	}

	public void removeOrganizingCamp() {
		myCamp = null;
	}
}
