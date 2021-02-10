package mk.ukim.finki.emt.advertisement.ordermanagement.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.AdId;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class OrderItemAdded implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty
    private final OrderItemId orderItemId;

    @JsonProperty
    private final AdId adId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderItemAdded(OrderId orderId, OrderItemId orderItemId, AdId adId, int quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.adId = adId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }


}
