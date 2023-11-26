package user;

public abstract class User implements IOrganizesCamp {
	private String name, email, faculty, userID, password;

	public User(String name, String password, String email, String faculty) {
		this.name = name;
		this.email = email;
		this.faculty = faculty;
		this.password = password;

		// extract all characters before '@' and set userID to it
		if (!this.email.isEmpty()) {
			int atIndex = this.email.indexOf('@');
			this.userID = this.email.substring(0, atIndex);
		}
	}

	public boolean validPassword(String password) {
		if (this.password.equals(password))
			return true;
		else
			return false;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public String getUserID() {
		return this.userID;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

}
