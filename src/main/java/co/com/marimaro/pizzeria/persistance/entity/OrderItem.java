package co.com.marimaro.pizzeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class) // Incluye las dos llaves primarias, es otra forma de tener llaves primarias
                            // compuestas.
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderItem extends AuditableEntity{

    @Id
    @Column(name = "id_Order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;
    @Column(columnDefinition = "Decimal(2,1)", nullable = false)
    private Double quantity;
    @Column(columnDefinition = "Decimal(5,2)", nullable = false)
    private Double price;

    @OneToOne
    @JoinColumn(
        name = "id_pizza", 
        referencedColumnName = "id_pizza", 
        foreignKey = @ForeignKey(name = "FK_OrdenItem_Pizza"), 
        insertable = false, 
        updatable = false)
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(
        name = "id_order", 
        referencedColumnName = "id_order", 
        foreignKey = @ForeignKey(name = "FK_OrdenItem_Orden"), 
        insertable = false, 
        updatable = false)
    @JsonIgnore // Se previene el llamado circular infinito, que Order llame a OrderItem y viceversa. Otra solución es tener un DTO.
    private Order order;

}
