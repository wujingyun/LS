package helper;

import java.io.Serializable;
import ejb.ContactEntity;

public class ContactState implements Serializable {
    private long id;
    private String department;
    private String faculty;
    private String phone_no;
    private String email;

    public ContactState(ContactEntity c){
        this.setId(c.getId());
        this.setDepartment(c.getDepartment());
        this.setFaculty(c.getFaculty());
        this.setPhone_no(c.getPhone_no());
        this.setEmail(c.getEmail());
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getFaculty() {
        return faculty;
    }

    public long getId() {
        return id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

}
