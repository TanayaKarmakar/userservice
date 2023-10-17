package com.app.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionDTO {
    private String sessionId;
    private String sessionToken;
    private String userId;
}
