package mk.ukim.finki.emt.advertisement.ordermanagement.application;

import mk.ukim.finki.emt.advertisement.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.advertisement.ordermanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.RecipientAddress;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.event.OrderCreated;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.event.OrderItemAdded;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class OrderCatalog {

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    private AdCatalog adCatalog;

    public OrderCatalog(OrderRepository orderRepository,
                        AdCatalog adCatalog,
                        Validator validator,
                        ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
        this.adCatalog = adCatalog;
    }

    public OrderId createOrder(@NonNull OrderForm order) {
        Objects.requireNonNull(order,"order must not be null");
        Set<ConstraintViolation<OrderForm>> constraintViolations = validator.validate(order);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<OrderForm> constraintViolation : constraintViolations) {
                System.out.println(constraintViolation.getMessage());
            }
        }

        Order newOrder = orderRepository.saveAndFlush(toDomainModel(order));
        applicationEventPublisher.publishEvent(new OrderCreated(newOrder.id(),newOrder.getOrderedOn()));
        newOrder.getItems().forEach(orderItem -> applicationEventPublisher.publishEvent(new OrderItemAdded(newOrder.id(),
                orderItem.id(),orderItem.getAdId(),orderItem.getQuantity(), Instant.now())));
        return newOrder.id();
    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        return orderRepository.findById(orderId);
    }

    @NonNull
    private Order toDomainModel(@NonNull OrderForm orderForm) {
        Order order = new Order(Instant.now(), orderForm.getCurrency(),
                toDomainModel(orderForm.getBillingAddress()));
        orderForm.getItems().forEach(item -> order.addItem(item.getAd(), item.getQuantity()));
        return order;
    }

    @NonNull
    private RecipientAddress toDomainModel(@NonNull RecipientAddressForm form) {
        return new RecipientAddress(form.getName(), form.getAddress(),form.getCity(), form.getCountry());
    }



}
