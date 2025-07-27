package co.com.marimaro.pizzeria.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.marimaro.pizzeria.persistance.entity.Order;
import co.com.marimaro.pizzeria.service.OrderService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("")
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/date-after/{date}")
    public ResponseEntity<List<Order>> getAllByDateAfter(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok().body(service.getByDateAfter(date));
    }

    @GetMapping("/date-before/{date}")
    public ResponseEntity<List<Order>> getAllByDateBefore(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok().body(service.getByDateBefore(date));
    }

    @GetMapping("/by-method")
    public ResponseEntity<List<Order>> getAllByMethod(
            @RequestParam(name = "methods", required = true) List<String> methods) {
        return ResponseEntity.ok().body(service.getByMethod(methods));
    }
}
