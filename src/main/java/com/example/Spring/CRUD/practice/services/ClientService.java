package com.example.Spring.CRUD.practice.services;

import com.example.Spring.CRUD.practice.dtos.ClientDTO;
import com.example.Spring.CRUD.practice.entities.Client;
import com.example.Spring.CRUD.practice.repositories.ClientRepository;
import com.example.Spring.CRUD.practice.services.exceptions.DatabaseException;
import com.example.Spring.CRUD.practice.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

  @Transactional
  public ClientDTO create(ClientDTO dto) {
    Client client = new Client();
    client.setName(dto.getName());
    client.setCpf(dto.getCpf());
    client.setIncome(dto.getIncome());
    client.setBirthdate(dto.getBirthdate());
    client.setChildren(dto.getChildren());

    client = repository.save(client);
    return new ClientDTO(client);
  }

  @Transactional
  public ClientDTO update(Long id, ClientDTO dto) {
    Client client;

    try {
      client = repository.getReferenceById(id);

      client.setName(dto.getName());
      client.setCpf(dto.getCpf());
      client.setIncome(dto.getIncome());
      client.setBirthdate(dto.getBirthdate());
      client.setChildren(dto.getChildren());

      client = repository.save(client);
    }
    catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Cliente não encontrado");
    }

    return new ClientDTO(client);
  }

  @Transactional
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Cliente não encontrado na base de dados");
    }
    try {
      repository.deleteById(id);
    }
    catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Falha de integridade referencial");
    }
  }
}
