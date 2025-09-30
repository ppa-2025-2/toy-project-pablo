package com.example.demo.controller.dto;

import java.sql.Date;
import java.util.HashSet;

/*
 * -- C -> Mother of All Languages
 * -- Turing Complete Language
 * -- LINGUAGEM DECLARATIVA (linguagem de pedidos)
 * CREATE TABLE users (
 *      name VARCHAR(20) NOT NULL CHECK LEN(name) >= 3 
 * )
 */

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.example.demo.repository.entity.Profile;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.entity.Profile.AccountType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
