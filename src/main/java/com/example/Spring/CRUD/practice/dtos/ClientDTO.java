package com.example.Spring.CRUD.practice.dtos;
import com.example.Spring.CRUD.practice.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientDTO {
  private Long id;

  @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
  @NotBlank(message = "Campo requerido")
  private String name;

  @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
  private String cpf;

  @Positive(message = "A renda deve ser um número positivo")
  private Double income;

  @PastOrPresent(message = "A data de nascimento não pode ser uma data futura")
  private LocalDate birthdate;

  private Integer children;

  public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthdate, Integer children) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.income = income;
    this.birthdate = birthdate;
    this.children = children;
  }

  public ClientDTO(Client client) {
    id = client.getId();
    name = client.getName();
    cpf = client.getCpf();
    income = client.getIncome();
    birthdate = client.getBirthdate();
    children = client.getChildren();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }

  public Double getIncome() {
    return income;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public Integer getChildren() {
    return children;
  }
}
