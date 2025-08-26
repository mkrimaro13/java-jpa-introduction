package co.com.marimaro.pizzeria.service.dto.login;

import lombok.Data;

import java.util.Date;

@Data
public class LoginResponseDto {
    private String token;
    private String token_type;
    private Date expiration;
}
