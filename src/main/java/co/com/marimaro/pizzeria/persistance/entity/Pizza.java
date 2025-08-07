package co.com.marimaro.pizzeria.persistance.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class) // Esta entidad será auditada, se agregarán campos de fecha de creación y fecha de actualización
// En este caso se usan getter y setter porque no usará modelo de dominio.
@Getter
@Setter
@NoArgsConstructor
public class Pizza extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer id;
    @Column(nullable = false, length = 30, unique = true)
    private String name;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;
    @Column(nullable = false)
    private Integer available;
}
