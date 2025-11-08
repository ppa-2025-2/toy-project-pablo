/*package com.example.demo.controller;

import com.example.demo.service.IslandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/islands")
public class IslandController {

    private final IslandService islandService;

    public IslandController(IslandService islandService) {
        this.islandService = islandService;
    }

    // Endpoint em português (alocar workstation)
    @PostMapping("/{islandId}/alocar/{userId}")
    public ResponseEntity<?> alocarWorkstation(
            @PathVariable Long islandId,
            @PathVariable Long userId) {
        islandService.alocarWorkstation(islandId, userId);
        return ResponseEntity.ok("Usuário alocado com sucesso na ilha " + islandId + ".");
    }

    // Endpoint alternativo em inglês (allocate user)
    @PostMapping("/{islandId}/allocate/{userId}")
    public ResponseEntity<?> allocateUser(
            @PathVariable Long islandId,
            @PathVariable Long userId) {
        islandService.allocateUser(islandId, userId);
        return ResponseEntity.ok("User successfully allocated to island " + islandId + ".");
    }

    // Novo endpoint GET para listar todas as ilhas
    @GetMapping
    public ResponseEntity<?> listarIslands() {
        return ResponseEntity.ok(islandService.listarTodas());
    }

}*/



package com.example.demo.controller;

import com.example.demo.service.IslandService;
import org.springframework.http.HttpStatus; // Importar HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/islands")
public class IslandController {

    private final IslandService islandService;

    public IslandController(IslandService islandService) {
        this.islandService = islandService;
    }

    // --- Endpoints de Alocação (Parte 3) ---

    // Endpoint em português (alocar workstation)
    // Usa o método Domain-Driven Design (DDD) 'allocateUser'
    @PostMapping("/{islandId}/alocar/{userId}")
    public ResponseEntity<?> alocarWorkstation(
            @PathVariable Long islandId,
            @PathVariable Long userId) {
        try {
            // Chama o Application Service, que orquestra a lógica de domínio
            islandService.allocateUser(islandId, userId);

            // Retorna 200 OK em caso de sucesso
            return ResponseEntity.ok("Usuário alocado com sucesso na ilha " + islandId + ".");

        } catch (IllegalArgumentException e) {
            // Captura erros de 'Não Encontrado' (Island ou User não existem)
            // Lançados pelo IslandService.findById(...).orElseThrow(...)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalStateException e) {
            // Captura erros de 'Regra de Negócio Violada' (e.g., Nenhuma Workstation
            // Disponível)
            // Lançados pela Workstation.allocate() ou
            // Island.assignUserToTheFirstWorkstationAvailable()
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Endpoint alternativo em inglês (allocate user)
    @PostMapping("/{islandId}/allocate/{userId}")
    public ResponseEntity<?> allocateUser(
            @PathVariable Long islandId,
            @PathVariable Long userId) {
        // Simplesmente delega para o endpoint em português, garantindo que a lógica
        // seja única
        return alocarWorkstation(islandId, userId);
    }

    // --- Endpoint GET para listar todas as ilhas ---

    @GetMapping
    public ResponseEntity<?> listarIslands() {
        return ResponseEntity.ok(islandService.listarTodas());
    }
}
