package co.com.marimaro.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;
import co.com.marimaro.pizzeria.persistance.repository.PizzaPagSortRepository;
import co.com.marimaro.pizzeria.persistance.repository.PizzaRepository;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository repository;

    @Autowired
    private PizzaPagSortRepository pagSortRepository;
    
    public List<Pizza> getAll() {
        return repository.findAll();
    }

    public List<Pizza> getAvailableByName(String name) {
        return repository.findAllByAvailableTrueAndNameContainingIgnoreCase(name);
    }

    public List<Pizza> getAvailableWithIngredients(String ingredient) {
        return repository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getAvailableWithoutIngredients(String ingredient) {
        return repository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getNoAvailable() {
        return repository.findAllByAvailable(0);
    }

    public List<Pizza> getAvailable() {
        return repository.findAllByAvailableTrueOrderByPrice();
    }

    public Optional<Pizza> getById(Integer id) {
        return repository.findById(id);
    }

    public Pizza create(Pizza pizza) {
        return repository.save(pizza);
    }

    public Boolean exists(Integer id) {
        return repository.existsById(id);
    }

    public Boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Pizza> getFirstByName(String name){
        return repository.findFirstByAvailableTrueAndNameContainingIgnoreCase(name);
    }

    public List<Pizza> get3FirstsLowerPrice(double price){
        return repository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public Page<Pizza> getAllPaginatedSorted(int page, int pageSize){
        Pageable pageRequested = PageRequest.of(page, pageSize);
        return pagSortRepository.findAll(pageRequested);
    }

    public Page<Pizza> getAllAvailableSorted(int page, int pageSize, String sortBy, String order){
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageRequested = PageRequest.of(page, pageSize, sort);
        return pagSortRepository.findAllByAvailableTrue(pageRequested);
    }
}
