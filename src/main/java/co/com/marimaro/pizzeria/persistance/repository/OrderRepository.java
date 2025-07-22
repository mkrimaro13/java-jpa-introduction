package co.com.marimaro.pizzeria.persistance.repository;

import org.springframework.data.repository.ListCrudRepository;

import co.com.marimaro.pizzeria.persistance.entity.Order;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {

}
