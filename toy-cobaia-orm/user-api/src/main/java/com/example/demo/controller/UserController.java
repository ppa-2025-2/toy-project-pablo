package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.NewUserDTO;
import com.example.demo.domain.UserService;
import com.example.demo.application.IslandService;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService; // Renomeado para clareza
    private final UserRepository userRepository;
    private final IslandService islandService; // Tipo e finalidade corrigidos

    // Construtor com Injeção de Dependência (DI)
    public UserController(
            UserService userService,
            UserRepository userRepository,
            IslandService islandService // Adicionado para injeção correta
    ) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.islandService = islandService; // Atribuição corrigida
    }

    // 1. ENDPOINT POST para Criar Novo Usuário
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void newUser(@RequestBody NewUserDTO newUser) {
        // Delega a lógica de negócio para o UserService (antigo userBusiness)
        userService.cadastrarUsuario(newUser);
    }

    // 2. ENDPOINT GET para Listar Usuários
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        // Retorna o status 200 OK e a lista de todos os usuários
        return ResponseEntity.ok(userRepository.findAll());
    }

    // 3. ENDPOINT POST para Alocar Workstation
    @PostMapping(path = "/{id}/allocate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> allocateWorkstation(@PathVariable("id") Integer id) {

        // CORREÇÃO: Usando o IslandService, que provavelmente tem o método
        // para o contexto da ilha, e não o UserService (que é o que estava
        // sendo feito na sua linha original, mas com um cast desnecessário e errado)
        // Se o objetivo é usar o UserService, troque a linha abaixo.

        // Chamada Corrigida (Assumindo que IslandService tem
        // alocarWorkstationDisponivel)
        // Se o método 'alocarWorkstationDisponivel' está em IslandService:
        islandService.alocarWorkstationDisponivel(id);

        /*
         * * Se o método 'alocarWorkstationDisponivel' está em UserService,
         * use esta linha e remova 'IslandService' das dependências:
         * userService.alocarWorkstationDisponivel(id);
         */

        return ResponseEntity.ok("allocated");
    }
}