package co.com.marimaro.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;
import co.com.marimaro.pizzeria.persistance.repository.PizzaRepository;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository repository;

    public List<Pizza> getAll() {
        return repository.findAll();
    }

    public List<Pizza> getNoAvailable() {
        return repository.findAllByAvailable(0);
    }

    public Optional<Pizza> getById(Integer id){
        return repository.findById(id);
    }
}
