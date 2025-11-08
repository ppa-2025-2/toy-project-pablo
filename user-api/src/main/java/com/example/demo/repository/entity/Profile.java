package com.example.demo.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "hte_user_profiles") // Nome da tabela ajustado para seguir o padrão hte_
public class Profile {

    public enum AccountType {
        FREE,
        PROFESSIONAL,
        ENTERPRISE
    }

    // O ID é mapeado a partir da entidade User
    @Id
    @Column(name = "user_id") // Nome da coluna no DB
    private Long id;

    @MapsId
    @OneToOne
    //Usa o nome correto da chave estrangeira.
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String name;
    private String company;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    // --- Construtor Padrão (Obrigatório) ---
    public Profile() {
        this.type = AccountType.FREE; // Define um default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}