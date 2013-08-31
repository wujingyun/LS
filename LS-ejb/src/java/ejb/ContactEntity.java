package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Contact")
public class ContactEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //generate unique entity ID
    private long id;
    private String department;
    private String faculty;
    private String phone_no;
    private String email;

    /** Creates a new instance of CustomerEntity */
    public ContactEntity() {
        //setId(System.nanoTime());
    }

    public void create(String department,String faculty,String phone_no,String email) {
        this.setDepartment(department);
        this.setFaculty(faculty);
        this.setPhone_no(phone_no);
        this.setEmail(email);
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
