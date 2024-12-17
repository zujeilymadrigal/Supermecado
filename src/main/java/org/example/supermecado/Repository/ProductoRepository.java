package org.example.supermecado.Repository;

import org.example.supermecado.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {  }
