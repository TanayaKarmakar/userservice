package com.app.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenValidationRequestDTO {
    private String token;
    private Long userId;
}
