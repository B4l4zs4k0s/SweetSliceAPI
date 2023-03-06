package com.cake.sweetsliceapi.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class LoginRequest {
        @NotBlank(message = "username may not be blank.")
        private String username;
        @NotBlank(message = "password may not be blank.")
        private String password;
}
