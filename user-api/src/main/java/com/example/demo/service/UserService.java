package com.example.demo.service;
import com.example.demo.controller.dto.NewUserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Construtor 
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. Método: Cadastrar Usuário
    @Transactional
    public void cadastrarUsuario(NewUserDTO newUserDTO) {
        User user = new User();
        user.setEmail(newUserDTO.email());
        user.setHandle(newUserDTO.handle());
        user.setPassword(newUserDTO.password());

        // Graças à correção em User.java, Profile não é nulo
        user.getProfile().setName(newUserDTO.name());
        user.getProfile().setCompany(newUserDTO.company());

        // Adiciona as roles
        user.getRoles().addAll(newUserDTO.roles());

        userRepository.save(user);
    }

    // 2. Método: Listar Todos
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}