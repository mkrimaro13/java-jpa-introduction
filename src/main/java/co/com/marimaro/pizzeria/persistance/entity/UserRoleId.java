package co.com.marimaro.pizzeria.persistance.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRoleId implements Serializable {
    private String username;
    private String role;
}
