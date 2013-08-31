package ejb;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity(name="Reservation")
public class ReservationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date res_date;

    @ManyToOne
    private BookEntity book ;
    @ManyToOne
    private MemberEntity member;

    public ReservationEntity(){
       // setId(System.nanoTime());
    }

    public void create(Date date){
     this.setRes_date();
    }

    public BookEntity getBook() {
        return book;
    }

    public Long getId() {
        return id;
    }

    public MemberEntity getMember() {
        return member;
    }

    public Date getRes_date() {
        return res_date;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public void setRes_date() {
        res_date=new Date();
        Calendar c = Calendar.getInstance();
        res_date=c.getTime();
    }

}
