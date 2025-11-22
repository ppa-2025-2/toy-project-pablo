package com.example.msticket.controller;

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

import com.example.msticket.controller.dto.NewUserDTO;
import com.example.msticket.domain.UserBusiness;
import com.example.msticket.repository.UserRepository;
import com.example.msticket.repository.entity.User;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserBusiness userBusiness;
    private UserRepository userRepository;

    public UserController(
            UserBusiness userBusiness,
            UserRepository userRepository
        ) {
        this.userBusiness = userBusiness;
        this.userRepository = userRepository;
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void newUser(@RequestBody NewUserDTO newUser) {
        
        userBusiness.cadastrarUsuario(newUser);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
