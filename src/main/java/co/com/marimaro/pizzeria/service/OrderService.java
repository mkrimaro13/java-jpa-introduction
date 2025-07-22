package co.com.marimaro.pizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.marimaro.pizzeria.persistance.entity.Order;
import co.com.marimaro.pizzeria.persistance.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> getAll() {
        return repository.findAll();
    }
}
