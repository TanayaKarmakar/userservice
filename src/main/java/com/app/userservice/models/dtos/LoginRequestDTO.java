package com.app.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDTO {
    private String email;
    private String password;
}
