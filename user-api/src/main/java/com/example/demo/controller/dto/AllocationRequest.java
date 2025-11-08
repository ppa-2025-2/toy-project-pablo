package com.example.demo.controller.dto;

/**
 * DTO (Data Transfer Object) simples para receber o ID da Island no corpo
 * da requisição POST /users/{userId}/allocate.
 */
public class AllocationRequest {
    private Long islandId;

    public AllocationRequest() {
        // Construtor padrão para deserialização do Jackson
    }

    public Long getIslandId() {
        return islandId;
    }

    public void setIslandId(Long islandId) {
        this.islandId = islandId;
    }
}
