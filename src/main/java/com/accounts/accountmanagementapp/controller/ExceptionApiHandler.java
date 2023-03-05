package com.accounts.accountmanagementapp.controller;

import com.accounts.accountmanagementapp.exception.UserHasAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleException(UsernameNotFoundException notFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(notFoundException.getMessage());
    }

    @ExceptionHandler(UserHasAlreadyExistException.class)
    public ResponseEntity<String> handleException(UserHasAlreadyExistException userHasAlreadyExistException) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(userHasAlreadyExistException.getMessage());
    }
}
