package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Workstation;

/**
 * Interface Repository para operações de persistência da entidade Workstation.
 * O Spring Data JPA cria a implementação automaticamente.
 */
@Repository
public interface WorkstationRepository extends JpaRepository<Workstation, Long> {
    // Métodos herdados: save(), findAll(), findById(), etc.
}