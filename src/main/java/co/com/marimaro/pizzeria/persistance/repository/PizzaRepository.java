package co.com.marimaro.pizzeria.persistance.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza,Integer>{
    List<Pizza> findAllByAvailable(Integer available);
    List<Pizza> findAllByAvailableTrueOrderByPrice();
    List<Pizza> findAllByAvailableTrueAndNameContainingIgnoreCase(String name);  //Para ignore entre mayúsculas y minúsculas
    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
}
