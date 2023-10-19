package com.app.userservice.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Data
@Setter
@Getter
public class User extends BaseModel {
    private String email;
    private String encryptedPass;
    @OneToMany
    private Set<Role> roles;
}
