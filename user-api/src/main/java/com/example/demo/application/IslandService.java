package com.example.demo.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.IslandRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Island;
//import com.example.demo.repository.entity.User;

@Service
@Transactional
public class IslandService {
    private final IslandRepository islandRepository;
    private final UserRepository userRepository;

    public IslandService(IslandRepository islandRepository, UserRepository userRepository) {
        this.islandRepository = islandRepository;
        this.userRepository = userRepository;
    }

    /**
     * Versão 3: Domain-Driven. O Application Service apenas orquestra:

     * 1) carrega a entidade Island e User
     * 2) chama um método de comportamento na entidade (island.assignUserToTheFirstWorkstationAvailable)
     * 3) persiste a entidade
     */
    public void alocarWorkstationDisponivel(Integer userId) {
        final var user = userRepository.findById(userId).orElseThrow();
        // aqui poderia escolher qual ilha (por id) ou tentar todas
        // vamos aplicar na primeira ilha com workstation livre
        Island freeIsland = null;
        for (Island island : islandRepository.findAll()) {
            boolean has = island.firstAvailableWorkstation().isPresent();
            if (has) {
                freeIsland = island;
                break;
            }
        }
        if (freeIsland == null) {
            throw new RuntimeException("No free workstation found");
        }

        // chama comportamento da entidade
        freeIsland.assignUserToTheFirstWorkstationAvailable(user);

        islandRepository.save(freeIsland);
    }
}
