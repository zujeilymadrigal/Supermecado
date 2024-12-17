package org.example.supermecado.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.example.supermecado.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {  }