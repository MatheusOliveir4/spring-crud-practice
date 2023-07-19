package com.example.Spring.CRUD.practice.dtos;

public class FieldMessage {
  private String fieldName;
  private String errorMessage;

  public FieldMessage(String fieldName, String errorMessage) {
    this.fieldName = fieldName;
    this.errorMessage = errorMessage;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
