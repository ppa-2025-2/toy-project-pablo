  package com.example.demo.repository.seed;

  import java.time.LocalDateTime;
  import java.util.List;
  import java.util.HashSet;

  import org.springframework.boot.ApplicationArguments;
  import org.springframework.boot.ApplicationRunner;
  import org.springframework.stereotype.Component;

  import com.example.demo.domain.Island;
  import com.example.demo.domain.Workstation;
  import com.example.demo.repository.IslandRepository;
  import com.example.demo.repository.seed.Disposition; 

  @Component
  public class SeedRunner implements ApplicationRunner {

      private final IslandRepository repo;

      public SeedRunner(IslandRepository repo) {
          this.repo = repo;
      }

      @Override
      public void run(ApplicationArguments args) throws Exception {

          System.out.println("SEMEANDO ----------- \n\n\n");

          if (repo.count() == 0) {
              inserirIlhaWorkstation();
          }

          // cuidado: este método deve existir no repositório
          // Assumindo que findIslandWithAvailableWorkstations() existe no seu
          // IslandRepository
          List<Island> islands = repo.findIslandWithAvailableWorkstations();

          System.out.println("ISLANDS===========================");
          System.out.println(islands);
      }

      private void inserirIlhaWorkstation() {
          Island i1 = new Island();
          i1.setDescription("Island 1");
          i1.setDisposition(Disposition.SQUARE);
          i1.setCreatedAt(LocalDateTime.now());
          i1.setUpdatedAt(LocalDateTime.now());

          // garantir que a coleção exista
          if (i1.getWorkstations() == null) {
              i1.setWorkstations(new HashSet<>());
          }

          var ws1 = new Workstation();
          // CORREÇÃO: Usar setDescription, pois o campo se chama 'description'
          ws1.setDescription("Specs ws1");
          ws1.setCreatedAt(LocalDateTime.now());
          ws1.setUpdatedAt(LocalDateTime.now());

          var ws2 = new Workstation();
          // CORREÇÃO: Usar setDescription, pois o campo se chama 'description'
          ws2.setDescription("Specs ws2");
          ws2.setCreatedAt(LocalDateTime.now());
          ws2.setUpdatedAt(LocalDateTime.now());

          // estabelecer o relacionamento nas duas pontas corretamente
          ws1.setIsland(i1);
          i1.getWorkstations().add(ws1);

          ws2.setIsland(i1);
          i1.getWorkstations().add(ws2);

          repo.save(i1);
      }
  }
