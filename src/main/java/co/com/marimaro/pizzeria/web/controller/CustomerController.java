package co.com.marimaro.pizzeria.web.controller;

import co.com.marimaro.pizzeria.persistance.entity.Customer;
import co.com.marimaro.pizzeria.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/by-phone/{phone}")
    public ResponseEntity<Customer> getByPhone(@PathVariable("phone") String phone) {
        return service.getByPhone(phone)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
