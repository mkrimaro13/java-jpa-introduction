package co.com.marimaro.pizzeria.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "id_customer",nullable = false)
    private String id;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String address;
    @Column(length = 50,nullable = false)
    private String email;
    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;
}
