package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.NewTicketDTO;
//import com.example.demo.controller.dto.NewUserDTO;
import com.example.demo.domain.TicketBusiness;
import com.example.demo.repository.TicketRepository;
//import com.example.demo.domain.UserBusiness;
//import com.example.demo.repository.TicketRepository;
//import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Ticket;
//import com.example.demo.repository.entity.User;


@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private TicketBusiness ticketBusiness;
    private TicketRepository ticketRepository;

    public TicketController(
            TicketBusiness ticketBusiness,
            TicketRepository ticketRepository
        ) {
        this.ticketBusiness = ticketBusiness;
        this.ticketRepository = ticketRepository;
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void newTicket(@RequestBody NewTicketDTO newTicket) {
        
        ticketBusiness.cadastrarTicket(newTicket);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> getTickets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }
}

