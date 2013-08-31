package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="Title")
public class TitleEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ISBN;
    private String title;
    private String author;
    private String publisher;

    @OneToMany(mappedBy="title")
    private Collection<BookEntity> books = new ArrayList<BookEntity>();

    public TitleEntity() {
     //   setISBN(System.nanoTime());
    }

    public void create(String ISBN, String title,String author,String publisher) {
         this.setISBN(ISBN);
        this.setTitle(title);
        this.setAuthor(author);
        this.setPublisher(publisher);
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public Collection<BookEntity> getBooks() {
        return books;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBooks(Collection<BookEntity> books) {
        this.books = books;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addBook(BookEntity bookEntity){
        this.books.add(bookEntity);
    }

    public int copies(){
        return this.books.size();
    }

     public void deleteBook(BookEntity bookEntity){
        this.books.remove(bookEntity);
     }
}
