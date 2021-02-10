package mk.ukim.finki.emt.advertisement.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class OrderItemId extends DomainObjectId {

    private OrderItemId() {
        super("");
    }

    public OrderItemId(String uuid) {
        super(uuid);
    }
}

