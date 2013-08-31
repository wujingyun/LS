package helper;

import ejb.ReservationEntity;
import java.io.Serializable;
import java.util.Date;

public class ReservationState implements Serializable{

    private Long id;
    private static Date res_date;

    private BookState bookState ;
    private MemberState memberState;

    public ReservationState(ReservationEntity r){
        this.setId(r.getId());
        this.setRes_date(r.getRes_date());
        this.setBookState(new BookState(r.getBook()));
        this.setMemberState(new MemberState(r.getMember()));

    }

     public ReservationState(long id, Date date) {
        this.setId(id);
        this.setRes_date(date);
    }


    public BookState getBookState() {
        return bookState;
    }

    public Long getId() {
        return id;
    }

    public MemberState getMemberState() {
        return memberState;
    }

    public static Date getRes_date() {
        return res_date;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMemberState(MemberState memberState) {
        this.memberState = memberState;
    }

    public static void setRes_date(Date res_date) {
        ReservationState.res_date = res_date;
    }

}
