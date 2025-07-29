package co.com.marimaro.pizzeria.persistance.repository;

import co.com.marimaro.pizzeria.persistance.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends ListCrudRepository<Customer, String> {
    @Query(nativeQuery = false, value = "SELECT c FROM Customer c WHERE c.phoneNumber = :phone")
    Optional<Customer> findByPhone(@Param("phone") String phone);
}