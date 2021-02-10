package mk.ukim.finki.emt.advertisement.ordermanagement.application.form;

import lombok.NonNull;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Currency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderForm implements Serializable {

    @NonNull
    private Currency currency;

    @NonNull
    private RecipientAddressForm billingAddress = new RecipientAddressForm();

    @NonNull
    private List<OrderItemForm> items = new ArrayList<>();

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public RecipientAddressForm getBillingAddress() {
        return billingAddress;
    }

    public List<OrderItemForm> getItems() {
        return items;
    }
}
