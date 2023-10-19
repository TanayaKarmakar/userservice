package com.app.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignupRequestDTO {
    private String email;
    private String password;
}
