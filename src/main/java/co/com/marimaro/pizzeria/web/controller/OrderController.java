package co.com.marimaro.pizzeria.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.marimaro.pizzeria.persistance.entity.Order;
import co.com.marimaro.pizzeria.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("")
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
    
}
