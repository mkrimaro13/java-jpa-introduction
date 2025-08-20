package co.com.marimaro.pizzeria.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(nullable =  false, columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(nullable =  false, columnDefinition = "TINYINT")
    private Boolean disabled;
}
