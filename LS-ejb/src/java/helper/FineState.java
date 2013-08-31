package helper;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import ejb.FineEntity;
import ejb.PaymentEntity;

public class FineState implements Serializable{
    private long id;
    private double amt_owned;

    private Set <PaymentState> paymentStateSet =
            new HashSet <PaymentState>();

    public FineState (FineEntity f){
        this.setId(f.getId());
        this.setAmt_owned(f.getAmtOwing());
        for(PaymentEntity payment: f.getPayments()){
            PaymentState payments=new PaymentState(payment);
            getPaymentStateSet().add(payments);
        }
    }

    public double getAmt_owned() {
        return amt_owned;
    }

    public long getId() {
        return id;
    }

    public Set<PaymentState> getPaymentStateSet() {
        return paymentStateSet;
    }

    public void setAmt_owned(double amt_owned) {
        this.amt_owned = amt_owned;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPaymentStateSet(Set<PaymentState> paymentStateSet) {
        this.paymentStateSet = paymentStateSet;
    }

}
