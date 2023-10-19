package com.app.userservice.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@MappedSuperclass
public class BaseModel {
//    @Id
//    @UuidGenerator
//    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
//    protected UUID id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}