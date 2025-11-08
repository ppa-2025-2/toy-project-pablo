package com.example.demo.service;
import com.example.demo.domain.Island;
import com.example.demo.domain.Workstation;
import com.example.demo.repository.IslandRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.WorkstationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

// Exceções simples para o Controller.java (você deve garantir que estas classes existam ou mapear para as do Spring)
    class ResourceNotFoundException extends IllegalArgumentException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    class AllocationConflictException extends IllegalStateException {
        public AllocationConflictException(String message) {
            super(message);
        }
    }

    @Service
    public class IslandService {

        private final IslandRepository islandRepository;
        private final UserRepository userRepository;
        private final WorkstationRepository workstationRepository;

    // Construtor com Injeção de Dependência
    public IslandService(IslandRepository islandRepository, UserRepository userRepository,
        WorkstationRepository workstationRepository) {
        this.islandRepository = islandRepository;
        this.userRepository = userRepository;
        this.workstationRepository = workstationRepository;
    }

        // --- 1. Método para listar (CORREÇÃO DO ERRO DE COMPILAÇÃO) ---

        /**
         * Retorna a lista de todas as entidades Island.
         */
    public List<Island> listarTodas() {
            return islandRepository.findAll();
    }

        // --- 2. Método de Alocação (CORREÇÃO DO ERRO HTTP 500) ---

        /**
         * Implementa a lógica de alocação bidirecional da Workstation para o User.
         * 
         * @param islandId ID da ilha onde buscar a Workstation.
         * @param userId   ID do usuário a ser alocado.
         */
    @Transactional
    public void allocateUser(Long islandId, Long userId) {
        // 1. Carregar entidades
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        Island island = islandRepository.findById(islandId)
            .orElseThrow(() -> new ResourceNotFoundException("Island not found with ID: " + islandId));

            // 2. Pré-verificações
            if (user.getWorkstation() != null) {
                throw new AllocationConflictException(
                        "User already allocated to workstation " + user.getWorkstation().getId());
            }

            // 3. Encontrar Workstation disponível
        Workstation workstation = island.findAvailableWorkstation();

            if (workstation == null) {
                throw new AllocationConflictException("No available workstations on island " + islandId);
            }

            // 4. ESTABELECER RELACIONAMENTO BIDIRECIONAL

            // A) Workstation aponta para User
            workstation.allocate(user);
            workstation.setUpdatedAt(LocalDateTime.now());

            // B) User aponta para Workstation (Crucial para consistência JPA)
            user.setWorkstation(workstation);
            user.setUpdatedAt(LocalDateTime.now());

            // 5. Salvar
            // Salvar Workstation e User garante que a transação persiste o relacionamento
            // bidirecional.
            workstationRepository.save(workstation);
            userRepository.save(user);
        }
    }







