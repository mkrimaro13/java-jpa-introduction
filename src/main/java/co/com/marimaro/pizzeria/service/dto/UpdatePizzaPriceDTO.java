package co.com.marimaro.pizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDTO {
    private int id;
    private Double newPrice;
}
