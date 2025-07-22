package co.com.marimaro.pizzeria.persistance.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer id;
    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;
    @Column(nullable = false, columnDefinition = "Decimal(6,2)")
    private Double total;
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;
    @Column(name = "additional_notes", length = 200)
    private String notes;

    @OneToOne(fetch = FetchType.LAZY) // Indica que no debe cargar la relación hasta que no sea necesaria (en este
                                      // caso con la etiqueta JsonIgnore, no la trae)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", foreignKey = @ForeignKey(name = "FK_Orden_Cliente"), insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER) // Eager hace lo contrario, prioriza la recuperación de
                                                            // estos registros.
    private List<OrderItem> orderItems;
}
