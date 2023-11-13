package suggestion;
import user.*;
public class Suggestion {
	private String textBody;
	private User sender;
	private User replier;
	private boolean approved;
	
	public Suggestion(String textBody, User sender, User replier, boolean approved) {
		this.textBody = textBody;
		this.sender = sender;
		this.replier = replier;
		this.approved = approved;
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
	
	public boolean getApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
