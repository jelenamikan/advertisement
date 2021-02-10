package mk.ukim.finki.emt.advertisement.ordermanagement.port.rest;

import mk.ukim.finki.emt.advertisement.ordermanagement.application.OrderCatalog;
import mk.ukim.finki.emt.advertisement.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.OrderId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderCatalog orderCatalog;

    public OrderController(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrdersByUserId(@PathVariable String userId){
        return orderCatalog.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable OrderId id){
        return orderCatalog.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody OrderForm form){
        return orderCatalog.createOrder(form);
    }
}
