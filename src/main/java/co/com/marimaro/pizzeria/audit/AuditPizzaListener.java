package co.com.marimaro.pizzeria.audit;

import co.com.marimaro.pizzeria.persistance.entity.Pizza;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;


public class AuditPizzaListener {

    private Pizza currentValue;

    // Se ejecuta luego de ejecutar un SELECT y cargar la información.
    @PostLoad
    public void onPostLoad(Pizza pizza){
        System.out.println("POST LOAD");
        // No se puede hacer this.currentValue = pizza porque Java sobrecargaría la ubicación en memoria del objeto
        this.currentValue = SerializationUtils.clone(pizza);

        System.out.println(pizza.toString());
    }

    // Este método se ejecuta luego de persistir (crear) o actualizar un registro en la BD.
    @PostPersist
    @PostUpdate
    public void onPostPersist(Pizza pizza){
        System.out.println("POST PERSIST/UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NUEW VALUE: " + pizza.toString());
    }

    // Este método se ejecutará *antes* de eliminar un registro en la BD
    @PreRemove
    public void onPreDeletePersist(Pizza pizza){
        System.out.println(pizza.toString());
    }
}
