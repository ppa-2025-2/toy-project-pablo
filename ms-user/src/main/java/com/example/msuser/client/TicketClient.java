/*package com.example.msuser.client;

import com.example.msuser.client.dto.UserCreatedEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TicketClient {

    private static final Logger logger = LoggerFactory.getLogger(TicketClient.class);

    private final RestTemplate restTemplate;
    private final String msTicketBaseUrl;

    public TicketClient(RestTemplate restTemplate,
            @Value("${app.ms-ticket.url}") String msTicketBaseUrl) {
        this.restTemplate = restTemplate;
        this.msTicketBaseUrl = msTicketBaseUrl;
    }

    /**
     * Chama o ms-ticket para criar os tickets de onboarding e workstation
     * quando um novo usuário é criado.
     */
    /*public void notifyUserCreated(UserCreatedEventDTO userCreatedEventDTO) {
        String url = msTicketBaseUrl + "/tickets/user-created";

        logger.info("Enviando evento de usuário criado para ms-ticket: url={}, body={}",
                url, userCreatedEventDTO);

        ResponseEntity<Void> response = restTemplate.postForEntity(
                url,
                userCreatedEventDTO,
                Void.class);

        logger.info("Resposta do ms-ticket: status={}", response.getStatusCode());
    }
}
 */
package com.example.msuser.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TicketClient {

    @Value("${app.ms-ticket.url:http://localhost:8081}")
    private String msTicketUrl;

    @Autowired
    private RestTemplate restTemplate;

    public void createOnboardingTickets(Long userId, String userName) {
        try {
            String url = msTicketUrl + "/tickets/onboarding?userId=" + userId + "&userName=" + userName;
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
            
            if (!response.getStatusCode().is2xxSuccessful()) {
                System.err.println("Erro ao criar tickets de onboarding: " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Falha na comunicação com ms-ticket: " + e.getMessage());
        }
    }
}