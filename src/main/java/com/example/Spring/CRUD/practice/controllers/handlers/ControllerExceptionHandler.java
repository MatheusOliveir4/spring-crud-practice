package com.example.Spring.CRUD.practice.controllers.handlers;

import com.devsuperior.dscommerce.dto.CustomError;
import com.example.Spring.CRUD.practice.dtos.FieldMessage;
import com.example.Spring.CRUD.practice.dtos.ValidationError;
import com.example.Spring.CRUD.practice.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError error = new ValidationError(Instant.now(), status.value(), "Erro de validação", request.getRequestURI());

    for (FieldError f : e.getBindingResult().getFieldErrors()) {
      error.addError(f.getField(), f.getDefaultMessage());
    }

    return ResponseEntity.status(status).body(error);
  }
}
