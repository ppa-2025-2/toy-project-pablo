package com.example.demo.domain;

import com.example.demo.repository.entity.User;
import com.example.demo.repository.seed.Disposition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // NOVO IMPORT!
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

   /**
    * Entidade para a Island (Ilha ou Cluster de Workstations).
    * Mapeada com @JsonIgnoreProperties para evitar ciclo infinito de serialização
    * Workstation <-> Island.
    */
   @Entity
   @Table(name = "hte_islands")
   // Ignora a lista de 'workstations' ao serializar a Island,
   // o que quebra o ciclo Workstation -> Island -> Workstation.
   @JsonIgnoreProperties({ "workstations" })
   public class Island {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(name = "description")
       private String description;

       @Column(name = "disposition")
       private Disposition disposition;

       @OneToMany(mappedBy = "island", fetch = FetchType.LAZY)
       private Set<Workstation> workstations = new HashSet<>();

       @Column(name = "created_at")
       private LocalDateTime createdAt;

       @Column(name = "updated_at")
       private LocalDateTime updatedAt;

       // --- Construtores ---
       public Island() {
           this.createdAt = LocalDateTime.now();
           this.updatedAt = LocalDateTime.now();
       }

       // --- Lógica de Negócios ---

       // Este método é crucial para a alocação e deve existir
       public Workstation findAvailableWorkstation() {
           return this.workstations.stream()
                   .filter(Workstation::isAvailable)
                   .findFirst()
                   .orElse(null);
       }


       public Long getId() {
           return id;
       }

       public void setId(Long id) {
           this.id = id;
       }

       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description = description;
       }

       public Disposition getDisposition() {
           return disposition;
       }

       public void setDisposition(Disposition square) {
           this.disposition = square;
       }

       public Set<Workstation> getWorkstations() {
           return workstations;
       }

       public void setWorkstations(Set<Workstation> workstations) {
           this.workstations = workstations;
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

       public void assignUserToTheFirstWorkstationAvailable(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignUserToTheFirstWorkstationAvailable'");
       }
   }