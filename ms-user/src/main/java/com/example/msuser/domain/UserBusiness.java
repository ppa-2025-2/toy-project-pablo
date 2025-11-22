/*package com.example.msuser.domain;

import com.example.msuser.client.TicketClient;
import com.example.msuser.client.dto.UserCreatedEventDTO;
import com.example.msuser.repository.UserRepository;
import com.example.msuser.repository.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBusiness {

    private final UserRepository userRepository;
    private final TicketClient ticketClient;

    public UserBusiness(UserRepository userRepository, TicketClient ticketClient) {
        this.userRepository = userRepository;
        this.ticketClient = ticketClient;
    }

    @Transactional
    public User createUser(User user) {
        // salva o usuário no banco do ms-user
        User saved = userRepository.save(user);

        // monta o DTO para enviar ao ms-ticket
        UserCreatedEventDTO event = new UserCreatedEventDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail());

        // chama o ms-ticket
        ticketClient.notifyUserCreated(event);

        return saved;
    }
}
*/
package com.example.msuser.domain;

import com.example.msuser.client.TicketClient;
import com.example.msuser.repository.UserRepository;
import com.example.msuser.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusiness {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketClient ticketClient;

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        
        // ⭐ CHAMA O CLIENTE PARA CRIAR TICKETS ⭐
        ticketClient.createOnboardingTickets(savedUser.getId(), savedUser.getName());
        
        return savedUser;
    }

    // ⭐ ADICIONE ESTE MÉTODO ⭐
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
