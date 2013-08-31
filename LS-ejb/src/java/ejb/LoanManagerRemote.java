package ejb;

import exception.ExistException;
import helper.BookState;
import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface LoanManagerRemote {
    public void createLoan(long bookId,long memId)throws ExistException;
    public void finishLoan(long bookId,long memId)throws ExistException;
    public ArrayList<BookState> getLoan(long memId);
    public boolean renew(String bookId);
   // public void createLoan(String bookId, String memId);
}
