package com.example.Spring.CRUD.practice.repositories;

import com.example.Spring.CRUD.practice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
