package mk.ukim.finki.emt.advertisement.ordermanagement.domain.model;

import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId extends DomainObjectId {

    private String id;

    private OrderItemId() {
        super("");
    }

    public OrderItemId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}

