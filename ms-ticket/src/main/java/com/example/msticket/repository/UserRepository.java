package com.example.msticket.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.example.msticket.repository.entity.User;

public interface UserRepository extends ListCrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByHandle(String handle);

    User findById(int id);

    boolean existsByHandle(String handle);
}
