package enquiry;

import camp.Camp;
import user.User;

public class Enquiry {
	private String textBody;
	private User sender;
	private User replier;
	private String replyBody;
	private boolean replied = false;
	private Camp myCamp;

	public Enquiry(String textBody, User sender, Camp myCamp) {
		this.textBody = textBody;
		this.sender = sender;
		this.myCamp = myCamp;
	}

	public Enquiry(String textBody, User sender, String replyBody, User replier, Camp myCamp) {
		this.textBody = textBody;
		this.sender = sender;
		this.replyBody = replyBody;
		this.replier = replier;
		this.myCamp = myCamp;
	}

	public String getTextBody() {
		return textBody;
	}

	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReplier() {
		return replier;
	}

	public Camp getCamp() {
		return myCamp;
	}

	public void setReplier(User replier) {
		this.replier = replier;
	}

	public String getReplyBody() {
		return replyBody;
	}

	public void setReplyBody(String replyBody) {
		this.replyBody = replyBody;
	}

	public boolean isReplied() {
		return replied;
	}

	public void setReplied(boolean replied) {
		this.replied = replied;
	}
}
