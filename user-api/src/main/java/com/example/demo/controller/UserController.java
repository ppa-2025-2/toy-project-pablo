package com.example.demo.controller;

import com.example.demo.controller.dto.NewUserDTO;
import com.example.demo.repository.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // --- 1. Criação de Usuário (POST /api/v1/users) ---
    /**
     * Cria um novo usuário e seu perfil.
     */
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody NewUserDTO newUserDTO) {
        userService.cadastrarUsuario(newUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // --- 2. Listar Todos os Usuários (GET /api/v1/users) ---
    /**
     * Lista todos os usuários.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.listarTodos();
        return ResponseEntity.ok(users);
    }

    // --- 3. Buscar Usuário por ID (GET /api/v1/users/{id}) ---
    /**
     * Busca um usuário pelo ID. Resolve o HTTP 404 que estávamos enfrentando.
     */
    @GetMapping("/{id}") // Mapeamento correto para capturar o ID na URL
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            // Retorna 200 OK com os dados do usuário
            return ResponseEntity.ok(userOptional.get());
        } else {
            // Retorna 404 Not Found se o usuário não for encontrado
            return ResponseEntity.notFound().build();
        }
    }

    // O método de alocação de Workstation deve estar no IslandController (como
    // definimos inicialmente).
}



