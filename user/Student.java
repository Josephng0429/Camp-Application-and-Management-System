package user;
import camp.*;
import enquiry.*;
public class Student extends User {

    private Camp committeeCamp;

    public Student(String name, String password, String email, String faculty) {
        super(name, password, email, faculty);
        this.committeeCamp = null;
    }

    public void viewCamps(){

    }

    public void viewMyCamps(){

    }

    public void registerCamp(){

    }

    public void withdrawCamp(){

    }

    public Enquiry submitEnquiry(){
        Enquiry enquiry = new Enquiry("test", this);
        return enquiry;
    }

    public Camp getCommitteeCamp() {
        return this.committeeCamp;
    }

    public void viewEnquiry(){

    }

    public void editEnquiry(){

    }

    public void delEnuiry(){

    }

    public void viewCommitteeEnquiries(){

    }

    public void replyEnquiry(Enquiry enquiry){

    }

    public void createSuggestion(){

    }

    public void editSuggestion(){

    }

    public void delSuggestion(){

    }

    public void generateCampReport(){

    }



}
