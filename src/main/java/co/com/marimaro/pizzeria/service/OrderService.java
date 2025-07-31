package co.com.marimaro.pizzeria.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.marimaro.pizzeria.persistance.entity.Order;
import co.com.marimaro.pizzeria.persistance.projection.OrderSummary;
import co.com.marimaro.pizzeria.persistance.repository.OrderRepository;

@Service
public class OrderService {
    private Function<LocalDate, LocalDateTime> dateFormatter = date -> LocalDateTime.of(date, LocalTime.of(0, 0));

    @Autowired
    private OrderRepository repository;

    public List<Order> getAll() {
        return repository.findAll();
    }

    public List<Order> getByDateAfter(LocalDate date) {
        return repository.findAllByDateAfter(dateFormatter.apply(date));
    }

    public List<Order> getByDateBefore(LocalDate date) {
        return repository.findAllByDateBefore(dateFormatter.apply(date));
    }

    public List<Order> getByMethod(List<String> methods){
        return repository.findAllByMethodIn(methods);
    }

    public Optional<List<Order>> getCustomerOrders(String customerId){
        return repository.findCustomerOrders(customerId);
    }

    public OrderSummary getSummary(int orderId){
        return repository.findOrderSummary(orderId);
    }
}