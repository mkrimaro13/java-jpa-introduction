package co.com.marimaro.pizzeria.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;
import co.com.marimaro.pizzeria.service.PizzaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/pizzas")
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

    @PostMapping("")
    public ResponseEntity<?> createEntity(@RequestBody Pizza pizza) {
        if (pizza.getId() == null || !service.exists(pizza.getId())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.create(pizza));
        } else {
            return ResponseEntity.status(400)
                    .body(Map.of("Error", "La pizza con esa información ya existe", "Pizza", pizza));
        }

    }

    @PutMapping("/modify")
    public ResponseEntity<?> updateEntity(@RequestBody Pizza pizza) {
        if (pizza.getId() != null && service.exists(pizza.getId())) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.create(pizza));
        } else {
            return ResponseEntity.status(400).body(Map.of("Error", "La pizza que intentas actualizar no existe",
                    "Detalle", "Se requiere un ID válido para actualizar la pizza"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable("id") int id){
        if(service.delete(id)){
            return ResponseEntity.ok().body(Map.of("Detalle", "Pizza eliminada"));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
