package co.com.marimaro.pizzeria.persistance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;
import co.com.marimaro.pizzeria.service.dto.UpdatePizzaPriceDTO;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
    List<Pizza> findAllByAvailable(Integer available);

    List<Pizza> findAllByAvailableTrueOrderByPrice();

    List<Pizza> findAllByAvailableTrueAndNameContainingIgnoreCase(String name); // Para ignore entre mayúsculas y
                                                                                // minúsculas

    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    Optional<Pizza> findFirstByAvailableTrueAndNameContainingIgnoreCase(String name);

    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    // Spring Expression Language, es la forma de inyectarle los atributos de un
    // objeto a las variables del query nativo.
    @Query(nativeQuery = true, value = "UPDATE pizza SET price = :#{#newPizzaPrice.newPrice} WHERE id_pizza = :#{#newPizzaPrice.id}")
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDTO newPizzaPrice);

}
