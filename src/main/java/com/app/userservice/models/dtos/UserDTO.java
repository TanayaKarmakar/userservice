package com.app.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private String id;
    private String email;
    private String encryptedPass;
}
