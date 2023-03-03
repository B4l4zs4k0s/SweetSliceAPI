package com.cake.sweetsliceapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private final String token;
}
