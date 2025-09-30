package com.example.demo.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;

import com.example.demo.controller.dto.NewTicketDTO;
import com.example.demo.controller.dto.NewUserDTO;
import com.example.demo.domain.stereotype.Business;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Profile;
import com.example.demo.repository.entity.Role;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.User;

import jakarta.validation.Valid;

// Spring -> possui um container de Injeção de Dependências

// estereótipo
@Business // Domain, DomainService, Service, UseCase
@Validated
public class TicketBusiness {

    private final TicketRepository ticketRepository;
    private final UserRepository UserRepository;


    public TicketBusiness(TicketRepository ticketRepository, UserRepository userRepository) {
        
        this.ticketRepository = ticketRepository;
        this.UserRepository = userRepository;

    }

    
    
    // cadastrar usuário é um use case (é uma feature)
    public void cadastrarTicket(@Valid NewTicketDTO newTicket) {
      
        Ticket ticket = new Ticket();

        ticket.setAcao(newTicket.acao());
        ticket.setDetalhes(null);
        ticket.setLocal(newTicket.local());
        ticket.setStatus(newTicket.status());
        LocalDate currentDate = LocalDate.now();
        ticket.setCreated_at(Date.valueOf(currentDate));
        ticket.setCriador(UserRepository.findById(newTicket.criador()));



        ticketRepository.save(ticket); 
    }

}
