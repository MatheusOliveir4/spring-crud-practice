package com.example.Spring.CRUD.practice.dtos;

import com.devsuperior.dscommerce.dto.CustomError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {
  List<FieldMessage> errors = new ArrayList<>();

  public ValidationError(Instant timestamp, Integer status, String error, String path) {
    super(timestamp, status, error, path);
  }

  public List<FieldMessage> getErrors() {
    return errors;
  }

  public void addError(String fieldName, String errorMessage) {
    errors.add(new FieldMessage(fieldName, errorMessage));
  }
}
