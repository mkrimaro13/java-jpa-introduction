package co.com.marimaro.pizzeria.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class) // Incluye las dos llaves primarias, es otra forma de tener llaves primarias
                            // compuestas.
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

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
    private Order order;

}
