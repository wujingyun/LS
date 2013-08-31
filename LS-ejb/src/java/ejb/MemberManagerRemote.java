package ejb;

import exception.ExistException;
import exception.MemberException;
import helper.BookState;
import helper.MemberState;
import helper.ReservationState;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Remote;

@Remote
public interface MemberManagerRemote {

    public void register(String name, String type, String pw,String department,String faculty,String phone_no,String email);

    public void terminate(long memId) throws ExistException,MemberException;
    public MemberState getMember() ;
    public MemberState getMember(long memId);

    public boolean verifyPassword(long memId, String password);
    

public String reserve(long memId, long bookId) ;
 public void newMember(long memId, String name, String type, String password);
public void newReservation(Date date) ;
 public ArrayList<ReservationState> getReservations(long memId) ;
public void deleteReserve(String ReverationId) ;
public BookState getBook(long reservId);
 public String getTitle(long bookId);
}