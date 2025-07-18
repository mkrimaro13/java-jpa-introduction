package co.com.marimaro.pizzeria.persistance.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza,Integer>{
    List<Pizza> findAllByAvailable(Integer available);

}
