package co.com.marimaro.pizzeria.persistance.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import co.com.marimaro.pizzeria.persistance.entity.Order;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {
        List<Order> findAllByDateAfter(LocalDateTime date);
        List<Order> findAllByDateBefore(LocalDateTime date);
        List<Order> findAllByMethodIn(List<String> method);
}
