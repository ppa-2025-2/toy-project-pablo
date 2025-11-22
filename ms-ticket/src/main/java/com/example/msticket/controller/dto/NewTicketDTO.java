package com.example.msticket.controller.dto;

import java.sql.Date;
import java.util.HashSet;



import org.hibernate.validator.constraints.Length;

import com.example.msticket.repository.entity.User;


import jakarta.validation.constraints.NotNull;


public record NewTicketDTO(
        @NotNull(message = "detalhes é obrigatório")
        @Length(min = 10, message = "O nome de ter no mínimo 10 caracteres")
        @NotNull(message = "O local é obrigatório")
        String local,
        @NotNull(message = "")
        User responsavel,
        @NotNull(message = "O criador é obrigatório")
        int criador,
        @NotNull(message = " O destinatário é obrigatório")
        User destinatario,
        Date createdAt,
        Date updadtedAt,
        String acao,
        String status,
        @NotNull(message = "O objeto é obrigatória")
        String objeto,
        String detalhes,
        HashSet<User> observadores,
        Date created_at

)  {

}
