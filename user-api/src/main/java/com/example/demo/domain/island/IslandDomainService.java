package com.example.demo.domain.island;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Island;
import com.example.demo.domain.Workstation;

/**
 * Domain service: contém apenas a regra de negócio que seleciona
 * a workstation livre dentro da ilha.
 */
@Service
public class IslandDomainService {

    /**
     * Retorna a primeira Workstation disponível na ilha, ou lança
     * IllegalStateException se não houver.
     */
    public Workstation alocarWorkstationDisponivel(Island island) {
        if (island == null) {
            throw new IllegalArgumentException("Island is null");
        }

        return island.getWorkstations()
                .stream()
                .filter(w -> w.getUser() == null) // disponível se user == null
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Workstations not available"));
    }
}
