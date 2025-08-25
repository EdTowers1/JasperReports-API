package com.apiReport.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apiReport.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos de solo lectura ya incluidos por JpaRepository:
    // List<Cliente> findAll();
    // Optional<Cliente> findById(Long id);

}
