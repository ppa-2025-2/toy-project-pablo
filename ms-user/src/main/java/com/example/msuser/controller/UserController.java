/*package com.example.msuser.controller;

import com.example.msuser.domain.UserBusiness;
import com.example.msuser.repository.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST do ms-user.
 * Responsável por receber requisições HTTP relacionadas a usuários.
 */
/*@RestController
@RequestMapping("/users")
public class UserController {

    private final UserBusiness userBusiness;

    public UserController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    /**
     * Cria um novo usuário.
     *
     * Fluxo:
     * 1) Recebe o JSON do usuário no corpo da requisição.
     * 2) Chama o UserBusiness.createUser(user), que:
     * - salva o usuário no banco do ms-user
     * - chama o ms-ticket para criar os dois tickets (onboarding e workstation)
     * 3) Retorna 201 (Created) com o usuário salvo no corpo.
     */
   /* @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userBusiness.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Se quiser, depois podemos adicionar:
    // - GET /users
    // - GET /users/{id}
    // - PUT /users/{id}
    // - DELETE /users/{id}
}*/
package com.example.msuser.controller;

import com.example.msuser.domain.UserBusiness;
import com.example.msuser.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserBusiness userBusiness;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userBusiness.createUser(user);
    }

    @GetMapping
    public List<User> listUsers() {
        return userBusiness.listUsers();
    }
}