package com.app.userservice.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@Setter
@Getter
@ToString
public class Session extends BaseModel {
    private String token;

    private Date expiringAt;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private SessionStatus status;
}
