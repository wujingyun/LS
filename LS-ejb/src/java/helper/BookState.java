package helper;

import ejb.BookEntity;
import ejb.ReservationEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

public class BookState implements Serializable{
    private Long id;
    private String copy;
    private static Date loan_date;
    private static Date due_date;
    private TitleState titleState;
    private MemberState memberState;
    private Set reservationStateSet =
            new HashSet<ReservationState>();

    public BookState(BookEntity b){
        this.setId(b.getId());
        this.setCopy(b.getCopy());
        this.setLoan_date(b.getLoan_date());
        this.setDue_date(b.getDue_date());
        this.setTitleState(new TitleState(b.getTitle()));
        this.setMemberState(new MemberState(b.getMember()));
        for(ReservationEntity reservation: b.getReservations()){
            ReservationState reservations=new ReservationState(reservation);
            getReservationStateSet().add(reservations);
        }
    }
 public BookState(long Id, String Copy, Date Loan_date, Date Due_date){
        this.setId(Id);
        this.setCopy(Copy);
        this.setLoan_date(Loan_date);
        this.setDue_date(Due_date);
        //this.setTitleState(titleState);
    }

    public BookState(BookEntity b,Object o){
        this.setId(b.getId());
        this.setCopy(b.getCopy());
        this.setDue_date(b.getDue_date());
    
    }

    public String getCopy() {
        return copy;
    }

    public static Date getDue_date() {
        return due_date;
    }

    public Long getId() {
        return id;
    }

    public static Date getLoan_date() {
        return loan_date;
    }

    public MemberState getMemberState() {
        return memberState;
    }

    public Set getReservationStateSet() {
        return reservationStateSet;
    }

    public TitleState getTitleState() {
        return titleState;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public static void setDue_date(Date due_date) {
        BookState.due_date = due_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static void setLoan_date(Date loan_date) {
        BookState.loan_date = loan_date;
    }

    public void setMemberState(MemberState memberState) {
        this.memberState = memberState;
    }

    public void setReservationStateSet(Set reservationStateSet) {
        this.reservationStateSet = reservationStateSet;
    }

    public void setTitleState(TitleState titleState) {
        this.titleState = titleState;
    }

}
