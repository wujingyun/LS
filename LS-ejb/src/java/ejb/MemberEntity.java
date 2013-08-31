package ejb;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name="Member")
public class MemberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String pw;

    @OneToOne(cascade = {CascadeType.ALL})
    private ContactEntity contact;
    @OneToOne (cascade = {CascadeType.ALL})
    private FineEntity fine=null;
    @OneToMany(mappedBy = "member")
    private Collection<ReservationEntity> reservations = new ArrayList<ReservationEntity>();
    @OneToMany(mappedBy = "member")
    private Collection<BookEntity> books = new ArrayList<BookEntity>();

    public MemberEntity() {
       // setId(System.nanoTime());
    }

    public void create(String name, String type, String pw) {
        this.setName(name);
        this.setType(type);
        this.setPw(pw);
        this.setFine(null);
    }
      public void createInJsp(long memId, String name,String type, String password){
          this.id=memId;
            this.setName(name);
        this.setType(type);
        this.setPw(pw);
      }
    public ContactEntity getContact() {
        return contact;
    }

    public FineEntity getFine() {
        return fine;
    }

    public Collection<BookEntity> getBooks() {
        return books;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public Collection<ReservationEntity> getReservations() {
        return reservations;
    }

    public String getType() {
        return type;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public void setFine(FineEntity fine) {
        this.fine = fine;
    }

    public void setBooks(Collection<BookEntity> books) {
        this.books = books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setReservations(Collection<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addBooks(BookEntity b) {
        this.books.add(b);
    }

    public void deleteBooks(BookEntity b) {
        this.books.remove(b);
    }
}

   