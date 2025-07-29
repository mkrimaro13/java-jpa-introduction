package co.com.marimaro.pizzeria.persistance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza, Integer>{
    Page<Pizza> findAllByAvailableTrue(Pageable pageable);
}