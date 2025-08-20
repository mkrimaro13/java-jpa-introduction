package co.com.marimaro.pizzeria.persistance.repository;

import org.springframework.data.repository.CrudRepository;

import co.com.marimaro.pizzeria.persistance.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,String>{

}
