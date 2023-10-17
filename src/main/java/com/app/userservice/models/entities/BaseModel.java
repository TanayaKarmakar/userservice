package com.app.userservice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    protected UUID id;
}