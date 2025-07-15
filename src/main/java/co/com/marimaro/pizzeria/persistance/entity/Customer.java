package co.com.marimaro.pizzeria.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id_customer")
    private String id;
    private String name;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
}
