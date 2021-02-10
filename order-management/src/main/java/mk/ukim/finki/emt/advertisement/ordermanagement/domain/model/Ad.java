package mk.ukim.finki.emt.advertisement.ordermanagement.domain.model;

import lombok.*;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Money;
import org.springframework.lang.NonNullApi;

import javax.persistence.EmbeddedId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ad extends AbstractEntity<AdId> {

    private String title;

    private Money price;

    private int quantity;

}
