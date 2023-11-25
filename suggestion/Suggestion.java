package suggestion;

import camp.Camp;
import user.*;

public class Suggestion {
	private String textBody;
	private User sender;
	private User replier;
	private Status status = Status.UNSEEN;
	private Camp camp;

	public enum Status {
		UNSEEN,
		APPROVED,
		REJECTED,
	}

	public Suggestion(String textBody, User sender, Camp camp) {
		this.textBody = textBody;
		this.sender = sender;
		this.camp = camp;
	}

	public Suggestion(String textBody, User sender, User replier, Status status, Camp camp) {
		this.textBody = textBody;
		this.sender = sender;
		this.replier = replier;
		this.status = status;
		this.camp = camp;
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

	public void setReplier(User replier) {
		this.replier = replier;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Camp getCamp() {
		return this.camp;
	}
}
