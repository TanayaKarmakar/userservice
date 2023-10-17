package com.app.userservice.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Setter
@Getter
@ToString
public class Session extends BaseModel {
    private String token;

    @ManyToOne
    private User user;
}
