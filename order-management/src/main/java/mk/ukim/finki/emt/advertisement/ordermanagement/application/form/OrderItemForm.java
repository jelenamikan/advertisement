package mk.ukim.finki.emt.advertisement.ordermanagement.application.form;


import lombok.NonNull;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.Ad;

import java.io.Serializable;

public class OrderItemForm implements Serializable {

    @NonNull
    private Ad ad;

    private int quantity = 1;

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
