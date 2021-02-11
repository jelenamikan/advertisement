package mk.ukim.finki.emt.advertisement.adcatalog.domain.model;

import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {

    private OrderId() {
        super(DomainObjectId.randomId(OrderId.class).getId());
    }

    public OrderId(String id) {
        super(id);
    }
}
