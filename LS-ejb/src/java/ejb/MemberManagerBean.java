package ejb;

import exception.ExistException;
import exception.MemberException;
import helper.BookState;
import helper.MemberState;
import helper.ReservationState;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Date;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class MemberManagerBean implements MemberManagerRemote {
    @PersistenceContext()
    EntityManager em;
    MemberEntity member;
      MemberEntity newmember;
    ContactEntity contact;
    ReservationEntity reservation;

   private Collection<ReservationEntity> reservations;


    public MemberManagerBean(){}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void register(String name, String type, String pw, String department, String faculty, String phone_no, String email){
        member = new MemberEntity();//initiate new member
        contact = new ContactEntity();
        member.create(name, type, pw);//set member attribute
        contact.create(department, faculty, phone_no, email);
        member.setContact(contact);
        em.flush();//
        em.persist(member);//persist
      
    }

  
       @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void terminate(long memId) throws ExistException, MemberException{
        member = em.find(MemberEntity.class, memId);
        long contactID;
        if(member==null)
            System.out.println("Member does not exist");
        else if(member.getBooks().size()!=0)
            System.out.println("Member has outstanding loan");
        else{
            contactID = member.getContact().getId();
            contact = em.find(ContactEntity.class, contactID);
            em.remove(member);
            em.remove(contact);
        }
    }

      

    public MemberState getMember() {
        return new MemberState(member);
    }



    @Remove
    public void remove()
    {
        System.out.println("MemberManagerBean:remove()");
    }








 public boolean verifyPassword(long memId, String password){
        Query q = em.createQuery("SELECT m from Member m");

        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
                        System.out.println("========================================================1password");
            if(m.getId().equals(memId)){
                System.out.println("========================================================2password");
                if(m.getPw().equals(password))
                {     System.out.println("========================================================3password");
                    return true;}
                break;
                 
            }
        }System.out.println("========================================================4password");
        return false;
    }


    public MemberState getMember(long memId) {
        Query q = em.createQuery("SELECT m FROM Member m");
        MemberState mState = null;
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
System.out.println("========================================================5password");
            if (m.getId().equals(memId)) {
                System.out.println("========================================================6password");
                      mState = new MemberState(m);
System.out.println("========================================================7password");
            }
        }
        return mState;
    }






 public void newReservation(Date date) {
        reservation = new ReservationEntity();
      reservation.create(date);
    }



 public void newMember(long memId, String name, String type, String password) {
        member = new MemberEntity();
        member.createInJsp(memId, name, type, password);
        reservations = new ArrayList<ReservationEntity>();
    }


 public String reserve(long memId, long bookId) {
     //ReservationEntity r=new ReservationEntity();
     String msg="";boolean valid=false;
     Query query = em.createQuery("SELECT r from Reservation r ");
 
System.out.println(query.getResultList().size()+"seeeeeeeee=========================================================================");

        for (Object o : query.getResultList()) {
            ReservationEntity r = (ReservationEntity) o;
if((r.getMember().getId().equals(memId))&&r.getBook().getId().equals(bookId))
          { valid=true;	
		 msg="You have reserved it";
                 System.out.println("thisthisthisthis");
   
    break;

	}
        }
if(valid==false){

		  System.out.println("gogogogogogogogoggogogo");
		

   
     Calendar cal = Calendar.getInstance();
      System.out.println("test0=========================================================================");
    //  Query query = em.createQuery("SELECT b from Book b");

      
       BookEntity  b = new BookEntity(); 
       System.out.println("tes1t=========================================================================");
       b = em.find(BookEntity.class, bookId);
       
     
      System.out.println(b.getId()+"====================================================================");
    //not borrowed by others,not need reserve
      if(b.getMember() == null)
     { msg="Don't need to reserve, go and borrow it!quick!";
      }

      //borrowed by others already , need to reserve
      else {
                   MemberEntity  m = new MemberEntity();
       System.out.println("test2=========================================================================");
       m = em.find(MemberEntity.class, memId);

             if(b.getMember().getId()!=memId){
      System.out.println("test3=========================================================================");
System.out.println(m.getId()+"2hahah====================================================================");
System.out.println(cal.getTime());
reservation=new ReservationEntity();
reservation.create(cal.getTime());
//newReservation(cal.getTime());
   System.out.println("test4=========================================================================");
                        //newMember(m.getId(), m.getName(), m.getType(), m.getPw());
                         System.out.println("test41=========================================================================");

                         //member.setContact(m.getContact());
                         System.out.println("test42=========================================================================");
                        //member.setFine(m.getFine());
                         System.out.println("test43=========================================================================");
                        reservations = m.getReservations();
                         System.out.println("test44=========================================================================");
                        reservations.add(reservation);
                         System.out.println("test45=========================================================================");
                        reservation.setMember(m);
                         System.out.println("test46=========================================================================");
                        reservation.setBook(b);
                         System.out.println("test47=========================================================================");
                        m.setReservations(reservations);
                         System.out.println("test48=========================================================================");
                       // member.setBooks(m.getBooks());
                         System.out.println("test49=========================================================================");
                        em.merge(m);

 System.out.println("test441=========================================================================");

                       // BookEntity book = new BookEntity();
                       // book.create( b.getCopy(), b.getDue_date());

                       // TitleEntity tit = new TitleEntity();
                       // tit.create(b.getTitle().getISBN(), b.getTitle().getTitle(), b.getTitle().getAuthor(), b.getTitle().getPublisher());
 System.out.println("test5=========================================================================");

                        //book.setMember(b.getMember());
                        //book.setTitle(tit);
                       // reservations = b.getReservations();
                         System.out.println("test5=========================================================================");

                       // reservations.add(reservation);
 System.out.println("test51=========================================================================");
                        //reservation.setBook(b);
                       // b.setReservations(reservations);
 System.out.println("test52=========================================================================");

                       // em.merge(b);
 System.out.println("test53=========================================================================");
                       

                    
               return msg="successfully reserved";}
             else{msg="you have borrowed it";
             }
      }
      
                
}
        return msg;

    }

public void deleteReserve(String ReservationId) {

       long rid = Long.parseLong(ReservationId);
       ReservationEntity resevation = em.find(ReservationEntity.class, rid);

        MemberEntity m = resevation.getMember();
        Collection<ReservationEntity> reservs = m.getReservations();
        reservs.remove(resevation);
        m.setReservations(reservs);
        em.merge(m);

        BookEntity b = resevation.getBook();
        reservs = b.getReservations();
        reservs.remove(resevation);
        b.setReservations(reservs);
        em.merge(b);

        em.remove(resevation);
    }



  public ArrayList<ReservationState> getReservations(long memId) {
        MemberEntity mem = em.find(MemberEntity.class, memId);
        ArrayList<ReservationState> reservs = new ArrayList<ReservationState>();
        for (Object o : mem.getReservations()) {
            ReservationEntity r = (ReservationEntity) o;
            ReservationState rs = new ReservationState(r.getId(), r.getRes_date());
            reservs.add(rs);
        }
        return reservs;

    }


  public BookState getBook(long reservId){
       ReservationEntity r = em.find(ReservationEntity.class, reservId);

       BookEntity b = r.getBook();
       BookState bs = new BookState(b.getId(), b.getCopy(),b.getLoan_date(),b.getDue_date());
       return bs;
   }


  public String getTitle(long bookId){
        BookEntity b = em.find(BookEntity.class, bookId);
        TitleEntity t = b.getTitle();
        return t.getTitle();
    }
}
