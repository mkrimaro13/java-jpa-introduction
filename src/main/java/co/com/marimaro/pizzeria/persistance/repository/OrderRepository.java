package co.com.marimaro.pizzeria.persistance.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import co.com.marimaro.pizzeria.persistance.entity.Order;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {
        List<Order> findAllByDateAfter(LocalDateTime date);
        List<Order> findAllByDateBefore(LocalDateTime date);
        List<Order> findAllByMethodIn(List<String> method);

        @Query(nativeQuery = true, value = "SELECT * FROM pizza_order WHERE id_customer = :id")
        Optional<List<Order>> findCustomerOrders(@Param("id") String customerId);
}
