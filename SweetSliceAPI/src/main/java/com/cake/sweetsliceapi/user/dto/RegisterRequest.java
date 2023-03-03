package com.cake.sweetsliceapi.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "username may not be blank.")
    private String username;
    @NotBlank(message = "password may not be blank.")
    private String password;
    @NotBlank(message = "password confirmation may not be blank")
    private String passwordConfirmation;
}
