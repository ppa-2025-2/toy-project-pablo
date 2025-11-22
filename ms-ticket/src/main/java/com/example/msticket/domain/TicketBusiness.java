


package com.example.msticket.domain;

import java.time.LocalDateTime;

import com.example.msticket.controller.dto.UserCreatedEventDTO;
import com.example.msticket.domain.stereotype.Business;
import com.example.msticket.repository.TicketRepository;
import com.example.msticket.repository.entity.Ticket;
import com.example.msticket.repository.entity.TicketType;

@Business
public class TicketBusiness {

    private final TicketRepository ticketRepository;

    public TicketBusiness(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

   
    public void createOnboardingAndWorkstationTickets(UserCreatedEventDTO user) {
        // Ticket de onboarding
        Ticket onboarding = createBaseTicket(user);
        onboarding.setType(TicketType.ONBOARDING);
        onboarding.setDescription("Onboarding do usuário " + user.getName());

        // Ticket de estação de trabalho
        Ticket workstation = createBaseTicket(user);
        workstation.setType(TicketType.WORKSTATION);
        workstation.setDescription("Alocação de estação de trabalho para " + user.getName());

        ticketRepository.save(onboarding);
        ticketRepository.save(workstation);
    }

    /**
     * Cria um ticket base com os campos comuns.
     */
    private Ticket createBaseTicket(UserCreatedEventDTO user) {
        Ticket ticket = new Ticket();
        ticket.setUserId(user.getId());
        ticket.setStatus("OPEN"); // se na entidade for String
        ticket.setCreatedAt(LocalDateTime.now());
        return ticket;
    }
}
