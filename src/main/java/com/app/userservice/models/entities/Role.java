package com.app.userservice.models.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role extends BaseModel {
    private String role;
}
