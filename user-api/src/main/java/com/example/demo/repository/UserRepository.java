package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.repository.entity.User;

// CORRIGIDO: Agora estende ListCrudRepository com a Entidade User e a PK de tipo Long.
public interface UserRepository extends ListCrudRepository<User, Long> {

    // O método findById(Long id) é herdado e funcionará corretamente agora.

    // Método personalizado de busca por ID (opcional, findById é preferível)
    @Query("""
            SELECT u FROM User u
            WHERE u.id = :id
            """)
    Optional<User> loadById(Long id); // Assinatura  para aceitar Long e retornar Optional<User>

    // Métodos de busca por campos
    Optional<User> findByEmail(String email);

    Optional<User> findByHandle(String handle);

    boolean existsByHandle(String handle);

    
}
