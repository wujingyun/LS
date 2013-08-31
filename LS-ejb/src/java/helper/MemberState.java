package helper;

import ejb.BookEntity;
import ejb.MemberEntity;
import ejb.MemberEntity;
import ejb.ReservationEntity;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class MemberState implements Serializable {
    private Long id;
    private String name;
    private String type;
    private String pw;
    
    private ContactState contactState;
    private FineState fineState;
    private Set <ReservationState> reservationStateSet= new HashSet<ReservationState>();
    private Set <BookState> bookStateSet= new HashSet<BookState>();
    
   /* public MemberState (MemberEntity m){
        this.setId(m.getId());
        this.setName(m.getName());
        this.setType(m.getType());
        this.setPw(m.getPw());
        
        this.setContactState(new ContactState(m.getContact()));
        this.setFineState(new FineState(m.getFine()));
        
        for(ReservationEntity reservation: m.getReservations()){
            ReservationState reservations=new ReservationState(reservation);
            getReservationStateSet().add(reservations);
        }    
        for(BookEntity book: m.getBooks()){
            BookState books=new BookState(book);
            getBookStateSet().add(books);
        }
    }     */
    
    public MemberState(MemberEntity m){
        this.setId(m.getId());
        this.setName(m.getName());
        this.setType(m.getType());
        this.setPw(m.getPw());
       // this.bookStateSet(m.getBooks());
    }



    public Set<BookState> getBookStateSet() {
        return bookStateSet;
    }

    public ContactState getContactState() {
        return contactState;
    }

    public FineState getFineState() {
        return fineState;
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

    public Set<ReservationState> getReservationStateSet() {
        return reservationStateSet;
    }

    public String getType() {
        return type;
    }

    public void setBookStateSet(Set<BookState> bookStateSet) {
        this.bookStateSet = bookStateSet;
    }

    public void setContactState(ContactState contactState) {
        this.contactState = contactState;
    }

    public void setFineState(FineState fineState) {
        this.fineState = fineState;
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

    public void setReservationStateSet(Set<ReservationState> reservationStateSet) {
        this.reservationStateSet = reservationStateSet;
    }

    public void setType(String type) {
        this.type = type;
    }

}