package com.accounts.accountmanagementapp.controller;

import com.accounts.accountmanagementapp.dto.validationError.Field;
import com.accounts.accountmanagementapp.dto.validationError.ValidationErrorResponse;
import com.accounts.accountmanagementapp.dto.validationError.Violation;
import com.accounts.accountmanagementapp.exception.UserHasAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(violation -> new Violation(
                        violation.getMessage(),
                        violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(),
                        violation.getInvalidValue().toString()))
                .collect(Collectors.toList());
        final List<Field> fields = violations.stream()
                .map(Field::new)
                .collect(Collectors.toList());
        return new ValidationErrorResponse(fields);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(
                        error.getDefaultMessage(),
                        error.getField(),
                        Objects.requireNonNull(error.getRejectedValue()).toString()))
                .collect(Collectors.toList());
        final List<Field> fields = violations.stream()
                .map(Field::new)
                .collect(Collectors.toList());
        return new ValidationErrorResponse(fields);
    }
}
