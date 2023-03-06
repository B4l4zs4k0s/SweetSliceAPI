package com.cake.sweetsliceapi.user.service;

import com.cake.sweetsliceapi.exception.IncorrectCredentialsException;
import com.cake.sweetsliceapi.exception.PasswordsDoesNotMatchException;
import com.cake.sweetsliceapi.exception.UsernameAlreadyInUseException;
import com.cake.sweetsliceapi.security.JwtUtil;
import com.cake.sweetsliceapi.security.UserDetailsServiceImpl;
import com.cake.sweetsliceapi.user.dto.LoginRequest;
import com.cake.sweetsliceapi.user.dto.LoginResponse;
import com.cake.sweetsliceapi.user.dto.RegisterRequest;
import com.cake.sweetsliceapi.user.dto.RegisterResponse;
import com.cake.sweetsliceapi.user.model.User;
import com.cake.sweetsliceapi.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                           UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyInUseException();
        }
        if (!request.getPassword().equals(request.getPasswordConfirmation())){
            throw new PasswordsDoesNotMatchException();
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return new RegisterResponse(getTokenFromUsername(user.getUsername()));
    }

    public LoginResponse login(LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new IncorrectCredentialsException();
        }
        return new LoginResponse(getTokenFromUsername(loginRequest.getUsername()));
    }


    private String getTokenFromUsername(String username){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }
}
