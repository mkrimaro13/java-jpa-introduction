package co.com.marimaro.pizzeria.persistance.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import co.com.marimaro.pizzeria.persistance.entity.Order;
import co.com.marimaro.pizzeria.persistance.projection.OrderSummary;

import org.springframework.data.repository.query.Param;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {
        List<Order> findAllByDateAfter(LocalDateTime date);

        List<Order> findAllByDateBefore(LocalDateTime date);

        List<Order> findAllByMethodIn(List<String> method);

        @Query(nativeQuery = true, value = "SELECT * FROM pizza_order WHERE id_customer = :id")
        Optional<List<Order>> findCustomerOrders(@Param("id") String customerId);

        @Query(value = """
                        SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames
                        FROM pizza_order po
                                INNER JOIN customer cu ON po.id_customer = cu.id_customer
                                INNER JOIN order_item oi ON po.id_order = oi.id_order
                                INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza
                        WHERE po.id_order = :orderId
                        GROUP BY po.id_order,cu.name,po.date,po.total
                        """, nativeQuery = true)
        OrderSummary findOrderSummary(@Param("orderId") int orderId);
}
