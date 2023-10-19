package com.app.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignoutRequestDTO {
    private String token;
    private Long id;
}
