package mk.ukim.finki.emt.advertisement.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Money;

@Getter
public class Ad {

    private String title;

    private AdId id;

    private Money price;

    private int quantity;

}
