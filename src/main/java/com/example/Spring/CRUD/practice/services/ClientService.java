package com.example.Spring.CRUD.practice.services;

import com.example.Spring.CRUD.practice.dtos.ClientDTO;
import com.example.Spring.CRUD.practice.entities.Client;
import com.example.Spring.CRUD.practice.repositories.ClientRepository;
import com.example.Spring.CRUD.practice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

  @Autowired
  ClientRepository repository;

  @Transactional(readOnly = true)
  public ClientDTO findById(Long id) {
    ClientDTO dto = new ClientDTO(repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado")));
    return dto;
  }

  @Transactional(readOnly = true)
  public Page<ClientDTO> findAll(Pageable pageable) {
    Page<Client> clients = repository.findAll(pageable);
    return clients.map(client -> new ClientDTO(client));
  }

}
