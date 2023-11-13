package enquiry;
import user.User;
public class Enquiry {
	private String textBody;
	private User sender;
	private User replier;
	private String replyBody;
	private boolean replied;
	
	public Enquiry(String textBody, User sender) {
		this.textBody = textBody;
		this.sender = sender;
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
	
	public String getReplyBody() {
		return replyBody;
	}
	
	public void setReplyBody(String replyBody) {
		this.replyBody = replyBody;
	}
	
	public boolean getReplied() {
		return replied;
	}
	
	public void setReplied(boolean replied) {
		this.replied = replied;
	}
}
