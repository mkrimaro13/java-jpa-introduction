package co.com.marimaro.pizzeria.service;

import co.com.marimaro.pizzeria.persistance.entity.Customer;
import co.com.marimaro.pizzeria.persistance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAll(){
        return repository.findAll();
    }
    public Optional<Customer> getByPhone(String phone){
        return repository.findByPhone(phone);
    }
}
