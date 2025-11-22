package com.example.msticket.controller;

import com.example.msticket.repository.TicketRepository;
import com.example.msticket.repository.entity.Ticket;
import com.example.msticket.repository.entity.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public List<Ticket> listTickets() {
        return ticketRepository.findAll();
    }

    @PostMapping("/onboarding")
    public ResponseEntity<String> createOnboardingTickets(@RequestParam Long userId, 
                                                        @RequestParam String userName) {
        try {
            // Cria ticket de ONBOARDING
            Ticket onboardingTicket = new Ticket();
            onboardingTicket.setUserId(userId);
            onboardingTicket.setType(TicketType.ONBOARDING);
            onboardingTicket.setDescription("Realizar processo de onboarding do usuário: " + userName);
            onboardingTicket.setStatus("OPEN");
            onboardingTicket.setCreatedAt(LocalDateTime.now());
            ticketRepository.save(onboardingTicket);

            // Cria ticket de WORKSTATION
            Ticket allocationTicket = new Ticket();
            allocationTicket.setUserId(userId);
            allocationTicket.setType(TicketType.WORKSTATION);
            allocationTicket.setDescription("Alocar estação de trabalho para o usuário: " + userName);
            allocationTicket.setStatus("OPEN");
            allocationTicket.setCreatedAt(LocalDateTime.now());
            ticketRepository.save(allocationTicket);

            return ResponseEntity.ok("Tickets de onboarding criados com sucesso para: " + userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar tickets: " + e.getMessage());
        }
    }
}