package mk.ukim.finki.emt.advertisement.ordermanagement.domain.repository;

import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

    List<Order> findAllByUserId(String id);

}
