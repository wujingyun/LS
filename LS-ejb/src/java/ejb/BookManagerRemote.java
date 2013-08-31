package ejb;

import exception.ExistException;
import helper.TitleState;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface BookManagerRemote {
    
    public void addTitle(String ISBN,String bookTitle, String author, String publisher);
    public void addBook(String ISBN,String copy);
    public void deleteBook(long bookId) throws ExistException;
   // public List<TitleState> getTitle();
   public TitleState getTitle();
    public ArrayList<TitleState> getBookByTitleorAuthor(String title, String author);

 //   public String reserve(String memId, long bookId);
  
}
