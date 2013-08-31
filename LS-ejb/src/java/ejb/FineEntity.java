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

@Entity(name="Fine")
public class FineEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double amtOwing;

    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<PaymentEntity> payments = new ArrayList<PaymentEntity>();

    public FineEntity() {
      //  setId(System.nanoTime());
    }

    public void create(double amtOwing) {
        this.setAmtOwing(amtOwing);
    }

    public Collection<PaymentEntity> getPayments() {
        return payments;
    }

    public double getAmtOwing() {
        return amtOwing;
    }

    public long getId() {
        return id;
    }

    public void setAmtOwing(double amtOwing) {
        this.amtOwing = amtOwing;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPayments(Collection<PaymentEntity> payments) {
        this.payments = payments;
    }
public void addPayments(PaymentEntity pay)
    {
        payments.add(pay);
        this.amtOwing=this.amtOwing-(double)pay.getAmount_paid();
    }

}
