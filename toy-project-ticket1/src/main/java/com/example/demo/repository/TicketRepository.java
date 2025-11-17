package com.example.demo.repository;

//import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.repository.entity.Ticket;


public interface TicketRepository extends ListCrudRepository<Ticket, Integer> {

    //Optional<Ticket> findByEmail();

   //Optional<Ticket> findByHandle(String handle);

    //boolean existsByHandle(String handle);
}
