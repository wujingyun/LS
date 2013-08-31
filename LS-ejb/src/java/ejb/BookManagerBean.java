package ejb;

import exception.ExistException;
import helper.BookState;
import helper.TitleState;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class BookManagerBean implements BookManagerRemote {
           
    @PersistenceContext()
    EntityManager em;
    BookEntity book;
    TitleEntity title;

    public BookManagerBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addTitle(String ISBN,String bookTitle, String author, String publisher) {
        title = new TitleEntity();
        title.create(ISBN,bookTitle, author, publisher);
       // em.flush();
        em.persist(title);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)

    public void addBook(String ISBN,String copy){
        book = new BookEntity();

        title = em.find(TitleEntity.class, ISBN);
        if (title == null) {
           
        System.out.println("Cannot find the title in system!");
        } else {
            book.create(copy);
            book.setTitle(title);
        }

        title.addBook(book);
        em.persist(book);
        em.persist(title);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteBook(long bookId) throws ExistException {
        book = em.find(BookEntity.class, bookId);
        if (book == null) {
            throw new ExistException("This book does not exist");
        } else {
            title=book.getTitle();
            title.deleteBook(book);
            em.remove(book);
        }
        em.persist(title);

    }


  public TitleState getTitle() {
        return new TitleState(title);
    }


    /* public List<TitleState> getTitle() {
        Query q = em.createQuery("SELECT t FROM Title t");
        List stateList = new ArrayList();
        for (Object o : q.getResultList()) {
            TitleEntity t = (TitleEntity) o;
            List bookList = new ArrayList();
            if (t.getBooks() != null) {
                for (Object obj : t.getBooks()) {
                    BookEntity b = (BookEntity) obj;
                    BookState bs = new BookState(b.getId(), b.getCopy(), b.getLoan_date(), b.getDue_date());
                    bookList.add(bs);
                }
                TitleState tbState = new TitleState(
                        t.getISBN(), t.getTitle(), t.getAuthor(), t.getPublisher(),
                        bookList);

                stateList.add(tbState);
            }
        }
        return stateList;
    }
*/



//method in 2b
public ArrayList<TitleState> getBookByTitleorAuthor(String title, String author){
        Query q = em.createQuery("SELECT t FROM Title t");
        ArrayList stateList = new ArrayList<TitleState>();
        for (Object o : q.getResultList()) {
            TitleEntity t = (TitleEntity) o;
           // System.out.println(title.trim()+t.getTitle().trim()+"====================================");
            if((t.getAuthor().trim().equals(author)) || (t.getTitle().trim().equals(title))){
                System.out.println(author+title+"====================================");
            ArrayList bookList = new ArrayList<BookState>();
            if (t.getBooks() != null) {
                for (Object obj : t.getBooks()) {
                    BookEntity b = (BookEntity) obj;
                    BookState bs = new BookState(b.getId(), b.getCopy(), b.getLoan_date(), b.getDue_date());
                    bookList.add(bs);
               //       System.out.println("getbook1==========================================================");
                }
                TitleState tbState = new TitleState(
                        t.getISBN(), t.getTitle(), t.getAuthor(), t.getPublisher(),
                        bookList);
  // System.out.println("getbook2==========================================================");

                stateList.add(tbState);
            }
            }
        }
        return stateList;
    }











}
