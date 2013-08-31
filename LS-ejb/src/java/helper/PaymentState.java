package helper;

import ejb.PaymentEntity;
import java.io.Serializable;
import java.util.Date;

public class PaymentState implements Serializable {
    private long   id;
    private static Date date_paid;
    private double amount_paid;

    public PaymentState(PaymentEntity p){
        this.setId(p.getId());
        this.setDate_paid(p.getDate_paid());
        this.setAmount_paid(p.getAmount_paid());
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public static Date getDate_paid() {
        return date_paid;
    }

    public long getId() {
        return id;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public static void setDate_paid(Date date_paid) {
        PaymentState.date_paid = date_paid;
    }

    public void setId(long id) {
        this.id = id;
    }

}
