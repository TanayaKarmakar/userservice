package com.app.userservice.models.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class User extends BaseModel {
    private String email;
    private String encryptedPass;
}
