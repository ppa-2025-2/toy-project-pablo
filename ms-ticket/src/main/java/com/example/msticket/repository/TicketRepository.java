package com.example.msticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.example.msticket.repository.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
   
}
