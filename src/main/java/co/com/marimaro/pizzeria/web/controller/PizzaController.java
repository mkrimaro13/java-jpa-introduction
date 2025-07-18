package co.com.marimaro.pizzeria.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;
import co.com.marimaro.pizzeria.service.PizzaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService service;

    @GetMapping("")
    public ResponseEntity<List<Pizza>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/no-available")
    public ResponseEntity<List<Pizza>> getNoAvailable() {
        return ResponseEntity.ok(service.getNoAvailable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getById(@PathVariable("id") Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
