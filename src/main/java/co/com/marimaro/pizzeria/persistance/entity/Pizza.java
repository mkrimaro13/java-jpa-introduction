package co.com.marimaro.pizzeria.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @Column(name = "id_pizza")
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer vegetarian;
    private Integer vegan;
    private Integer available;
}
