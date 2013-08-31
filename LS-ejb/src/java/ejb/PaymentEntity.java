package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity(name="Payment")
public class PaymentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long   id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date_paid;
    private double amount_paid;

    public PaymentEntity() {
      //  setId(System.nanoTime());
    }

    public void create(Date date_paid, double amount_paid) {
        this.setDate_paid(date_paid);
        this.setAmount_paid(amount_paid);
    }
public void create(int amount_paid) {

        this.setAmount_paid(amount_paid);
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public Date getDate_paid() {
        return date_paid;
    }

    public long getId() {
        return id;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public void setDate_paid(Date date_paid) {
        this.date_paid = date_paid;
    }

    public void setId(long id) {
        this.id = id;
    }
   
}
