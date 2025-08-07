package co.com.marimaro.pizzeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class AuditableEntity {
    @Column(name = "created_date")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedDate;
}
