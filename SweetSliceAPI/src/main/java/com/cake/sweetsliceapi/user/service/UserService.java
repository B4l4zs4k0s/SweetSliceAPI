package com.cake.sweetsliceapi.user.service;

import com.cake.sweetsliceapi.user.dto.LoginRequest;
import com.cake.sweetsliceapi.user.dto.LoginResponse;
import com.cake.sweetsliceapi.user.dto.RegisterRequest;
import com.cake.sweetsliceapi.user.dto.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
