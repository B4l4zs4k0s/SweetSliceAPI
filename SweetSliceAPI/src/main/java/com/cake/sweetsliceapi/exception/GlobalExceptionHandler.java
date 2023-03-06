package com.cake.sweetsliceapi.exception;

import com.cake.sweetsliceapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
                Objects.requireNonNull(
                        exception.getBindingResult().getFieldError()).getDefaultMessage()
        ));
    }

    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ResponseEntity<?> handleIncorrectCredentialsException(IncorrectCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("incorrect username or password."
        ));
    }

    @ExceptionHandler(value = PasswordsDoesNotMatchException.class)
    public ResponseEntity<?> handlePasswordsDoesNotMatchException(PasswordsDoesNotMatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
                "passwords does not match."
        ));
    }

    @ExceptionHandler(value = UsernameAlreadyInUseException.class)
    public ResponseEntity<?> handleUsernameAlreadyInUseException(UsernameAlreadyInUseException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
                "username already in use."
        ));
    }
}
