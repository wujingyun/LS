package ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import exception.ExistException;
import helper.BookState;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import javax.persistence.Query;

@Stateful
public class LoanManagerBean implements LoanManagerRemote {

    @PersistenceContext()
    EntityManager em;
    BookEntity book;
    MemberEntity member;
    FineEntity fine;

    public LoanManagerBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createLoan(long bookId, long memId) throws ExistException {
        member = em.find(MemberEntity.class, memId);
        if (member == null) {
            throw new ExistException("This member does not exist");
        }
        if(member.getBooks()!=null)
        {
            if(member.getBooks().size()>=6)
            {
                throw new ExistException("cannot borrow more than 6 books");
            }
        }

        book = em.find(BookEntity.class, bookId);
        if (member == null) {
            throw new ExistException("This book does not exist");
        }
        member.addBooks(book);
        book.setMember(member);
        book.setLoan_date();
        book.setDue_date(book.getLoan_date());
        em.persist(book);
        em.persist(member);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void finishLoan(long bookId, long memId) throws ExistException {
        int hasBook = 0;
        long diffDays;
        Date return_date = new Date();
        Calendar c = Calendar.getInstance();
        //set return at 5 days from due
        c.add(Calendar.DATE, 19);
        return_date = c.getTime();
        book = em.find(BookEntity.class, bookId);
        member = em.find(MemberEntity.class, memId);
        if (book == null) {
            throw new ExistException(" book does not exist");
        }

        if (member == null) {
            throw new ExistException(" member does not exist");
        }

        for (BookEntity book1 : member.getBooks()) {
            if (book1.getId() == book.getId()) {
                hasBook = 1;
                break;
            }
        }
        if (hasBook == 0) {
            throw new ExistException("The member does not borrow the book.");
        }

        member.deleteBooks(book);
        book.setMember(null);
//impose fine if return after due date
        if (return_date.after(book.getDue_date())) {
            diffDays = (return_date.getTime() - book.getDue_date().getTime()) / (24 * 60 * 60 * 1000);
            fine = new FineEntity();
            fine.create(diffDays);
            member.setFine(fine);
            System.out.println("$" + fine.getAmtOwing() + " fine is imposed.");
        }
        em.persist(member);
        em.persist(book);

    }

//method in 2b
      public ArrayList<BookState> getLoan(long memId) {
        Query q = em.createQuery("SELECT m FROM Member m");
        ArrayList bookLoans = new ArrayList<BookState>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;

            if (m.getId().equals(memId)) {
                for (Object obj : m.getBooks()) {
                    BookEntity b = (BookEntity) obj;
                    System.out.println("getloan1===========================================================================");
                    BookState bs = new BookState(b.getId(),b.getCopy(),b.getLoan_date(),b.getDue_date());
                     System.out.println("getloan2"+b.getDue_date()+"==========================================================================");

                    bookLoans.add(bs);
                }
                break;
            }
        }
        return bookLoans;
    }



//method in 2b
public boolean renew(String bookId) {
        long id = Long.parseLong(bookId);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, 2);
 System.out.println("renew1===========================================================================");

        BookEntity b = em.find(BookEntity.class, id);
 System.out.println("renew2===========================================================================");

        if (b.getReservations().size() != 0 ||(b.getDue_date().getTime() - b.getLoan_date().getTime())/(1000*60*60*24) > 14) {
     System.out.println("renew3===========================================================================");
       return false;
        }
 System.out.println("renew4===========================================================================");

        Date dueDate = cal.getTime();

        b.setDue_date(dueDate);
         System.out.println(dueDate+"renew5===========================================================================");
        
        em.persist(b);
        
        return true;
    }




}
