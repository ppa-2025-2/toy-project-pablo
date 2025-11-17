package com.example.demo.repository.entity;

import java.sql.Date;
import java.util.HashSet;
//import java.util.Set;

//import com.fasterxml.jackson.annotation.JsonBackReference;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
// Banco de Dados -> Entidade
// POO: Programação Orientada a Objetos (POO)
// Entidade: objeto que tem IDENTIDADE (muda com o tempo
// O/R M: Object/Relational Mapping
// Mapeamento Objeto/Relacional
// Problema da Diferença de Representação
// Impedance Mismatch (diferença de impedância)
// Entity: DDD (Domain-Driven Design)

// <<entidade>> -> stereotype -> estereótipo
// Entity -> Metadata
@Entity // anotação/annotation
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "criador")
    private User criador;

    @ManyToOne
    @JoinColumn(name = "destinatario")
    private User destinatario;
    
    @ManyToOne
    @JoinColumn(name = "responsavel")
    private User responsavel;

    @ManyToMany
    @JoinTable(
        name = "ticket_user",
        joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID")
    )
    private HashSet<User> observadores;

    @Column(nullable = false, length = 255)
    private String objeto;
    
    @Column(nullable = false, length = 255)
    private String acao;

    @Column(nullable = false, length = 255)
    private String detalhes;

    @Column(nullable = false, length = 255)
    private String local;

    @Column(nullable = false, length = 255)
    private Date created_at;

    @Column(nullable = false, length = 255)
    private Date updated_at;

    @Column(nullable = true, length = 255)
    private String status;

    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // private Profile profile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public User getDestinatario() {
        return destinatario;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public String getLocal() {
        return local;
    }

    public String getObjeto() {
        return objeto;
    }

    public User getResponsavel() {
        return responsavel;
    }

    public String getStatus() {
        return status;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setDestinatario(User destinatario) {
        this.destinatario = destinatario;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public void setResponsavel(User responsavel) {
        this.responsavel = responsavel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    public void setCriador(User criador) {
        this.criador = criador;
    }
    public void setObservadores(HashSet<User> observadores) {
        this.observadores = observadores;
    }
    public User getCriador() {
        return criador;
    }
    public HashSet<User> getObservadores() {
        return observadores;
    }

    


}
