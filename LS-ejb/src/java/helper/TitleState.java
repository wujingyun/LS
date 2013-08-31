package helper;

import java.io.Serializable;
import ejb.TitleEntity;
import ejb.BookEntity;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class TitleState implements Serializable{
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
   private Set bookStateSet = new HashSet<BookState>();
 private List books = new ArrayList();

    public TitleState(String isbn,
    String title,
    String author,
    String publisher,
    List books) {
        this.setISBN(ISBN);
        this.setTitle(title);
        this.setAuthor(author);
        this.setPublisher(publisher);
        this.setBooks(books);
    }
    public TitleState(TitleEntity t){
        this.setISBN(t.getISBN());
        this.setTitle(t.getTitle());
        this.setAuthor(t.getAuthor());
        this.setPublisher(t.getPublisher());
        for(BookEntity book: t.getBooks()){
            BookState books=new BookState(book);
            getBookStateSet().add(books);
        }
    }


    public TitleState(TitleEntity t, Object o){
        this.setISBN(t.getISBN());
        this.setTitle(t.getTitle());
        this.setAuthor(t.getAuthor());
        this.setPublisher(t.getPublisher());
    }

public List getBooks() {
        return books;
    }
 public void setBooks(List books) {
        this.books = books;
    }


    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public Set getBookStateSet() {
        return bookStateSet;
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

    public void setBookStateSet(Set bookStateSet) {
        this.bookStateSet = bookStateSet;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}