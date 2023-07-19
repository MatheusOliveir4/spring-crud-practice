package com.example.Spring.CRUD.practice.controllers;

import com.example.Spring.CRUD.practice.dtos.ClientDTO;
import com.example.Spring.CRUD.practice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

  @Autowired
  ClientService service;

  @GetMapping("/{id}")
  public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
    ClientDTO dto = service.findById(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
    Page<ClientDTO> clients = service.findAll(pageable);
    return ResponseEntity.ok(clients);
  }

  @PostMapping
  public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO client) {
    ClientDTO dto = service.create(client);
    return ResponseEntity.status(HttpStatus.CREATED.value()).body(dto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO client) {
    ClientDTO dto = service.update(id, client);
    return ResponseEntity.status(HttpStatus.CREATED.value()).body(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
