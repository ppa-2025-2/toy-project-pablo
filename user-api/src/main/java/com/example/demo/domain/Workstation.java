package com.example.demo.domain;

import com.example.demo.repository.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hte_workstations")
public class Workstation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Alterado para FetchType.EAGER para evitar o Hibernate Proxy
    // (que causava o HTTP 500)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "island_id", nullable = false)
    private Island island;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //Quebra o ciclo Workstation -> User
    @JsonBackReference
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Construtor Padrão (Obrigatório)
    public Workstation() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Método para checar disponibilidade (necessário em Island.java)
    public boolean isAvailable() {
        return this.user == null;
    }

    // Método de Negócio para Alocação
    public void allocate(User user) {
        this.user = user;
    }

    // Método de Negócio para Desalocação
    public void deallocate() {
        this.user = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}