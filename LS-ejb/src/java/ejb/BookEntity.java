package ejb;//testtttttttt

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Calendar;
import javax.persistence.Temporal;

@Entity(name="Book")
public class BookEntity implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String copy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date loan_date;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date due_date;

    @ManyToOne
    private TitleEntity title;
    @ManyToOne
    private MemberEntity member;
    @OneToMany(mappedBy="book")
    private Collection<ReservationEntity> reservations= new ArrayList<ReservationEntity>();

     public BookEntity() {
        // setId(System.nanoTime());
     }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public void create( String  copy) {

        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //this.setCopy(copy);
        //this.setLoan_date();
        //this.setDue_date(loan_date);


        this.setCopy(copy);


    }


    public void create( String  copy, Date loan_date) {


        this.setCopy(copy);
        this.setLoan_date();
        this.setDue_date(loan_date);


      //  this.setCopy(copy);


    }

    public String getCopy() {
        return copy;
    }

    public Date getDue_date() {
        return due_date;
    }

    public Long getId() {
        return id;
    }

    public Date getLoan_date() {
        return loan_date;
    }

    public MemberEntity getMember() {
        return member;
    }

   public Collection<ReservationEntity> getReservations() {
        return reservations;
    }

    public TitleEntity getTitle() {
        return title;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public void setDue_date(Date loan_date) {
        due_date=new Date();
        due_date=addDays(loan_date,14);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLoan_date() {
        loan_date=new Date();
        Calendar c = Calendar.getInstance();
        loan_date=c.getTime();
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public void setReservations(Collection<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public void setTitle(TitleEntity title) {
        this.title = title;
    }
       
}
