package com.example.msticket.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.example.msticket.controller.dto.NewUserDTO;
import com.example.msticket.controller.dto.UserCreatedEventDTO;
import com.example.msticket.domain.stereotype.Business;
import com.example.msticket.repository.RoleRepository;
import com.example.msticket.repository.UserRepository;
import com.example.msticket.repository.entity.Profile;
import com.example.msticket.repository.entity.Role;
import com.example.msticket.repository.entity.User;

import jakarta.validation.Valid;

@Business
@Validated
public class UserBusiness {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Set<String> defaultRoles;
    private final RestTemplate restTemplate;

    // URL do ms-ticket (pode vir do application.properties)
    @Value("${app.ms-ticket.url:http://localhost:8081}")
    private String msTicketBaseUrl;

    public UserBusiness(
            UserRepository userRepository,
            RoleRepository roleRepository,
            @Value("${app.user.default.roles}") Set<String> defaultRoles,
            RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.defaultRoles = defaultRoles;
        this.restTemplate = restTemplate;
    }

    // cadastrar usuário é um use case (é uma feature)
    public void cadastrarUsuario(@Valid NewUserDTO newUser) {

        // validação da senha
        if (!newUser.password().matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")) {
            throw new IllegalArgumentException(
                    "A senha deve ter pelo menos 8 caracteres e conter pelo menos uma letra e um número");
        }

        // email único
        userRepository.findByEmail(newUser.email())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Usuário com o email " + newUser.email() + " já existe");
                });

        // handle único
        userRepository.findByHandle(newUser.handle())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Usuário com o nome " + newUser.handle() + " já existe");
                });

        User user = new User();

        user.setEmail(newUser.email());
        user.setHandle(
                newUser.handle() != null
                        ? newUser.handle()
                        : generateHandle(newUser.email()));
        user.setPassword(passwordEncoder.encode(newUser.password()));

        // ===== ROLES =====
        Set<Role> roles = new HashSet<>();

        // perfis padrão vindos de app.user.default.roles
        roles.addAll(roleRepository.findByNameIn(defaultRoles));

        // perfis adicionais vindos do DTO (pode ser null)
        Set<String> requestedRoles = newUser.roles() != null
                ? new HashSet<>(newUser.roles())
                : Collections.emptySet();

        if (!requestedRoles.isEmpty()) {
            Set<Role> additionalRoles = roleRepository.findByNameIn(requestedRoles);
            if (additionalRoles.size() != requestedRoles.size()) {
                throw new IllegalArgumentException("Alguns papéis informados não existem");
            }
            roles.addAll(additionalRoles);
        }

        if (roles.isEmpty()) {
            throw new IllegalArgumentException("O usuário deve ter pelo menos um papel");
        }

        user.setRoles(roles);

        // ===== PROFILE =====
        Profile profile = new Profile();

        profile.setName(newUser.name());
        profile.setCompany(newUser.company());
        profile.setType(
                newUser.type() != null
                        ? newUser.type()
                        : Profile.AccountType.FREE);

        profile.setUser(user);
        user.setProfile(profile);

        // 1) salva usuário
        userRepository.save(user);

        // 2) após salvar, chama o ms-ticket para criar os dois tickets
        UserCreatedEventDTO event = new UserCreatedEventDTO();
        event.setId(user.getId());
        event.setName(profile.getName());
        event.setEmail(user.getEmail());

        // POST para o ms-ticket
        String url = msTicketBaseUrl + "/tickets/user-created";
        restTemplate.postForEntity(url, event, Void.class);
    }

    private String generateHandle(String email) {
        String[] parts = email.split("@");
        String handle = parts[0];
        int i = 1;
        while (userRepository.existsByHandle(handle)) {
            handle = parts[0] + i++;
        }
        return handle;
    }
}
