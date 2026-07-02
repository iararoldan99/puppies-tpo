package com.uade.puppies_tpo.config;

import com.uade.puppies_tpo.application.service.AdopcionService;
import com.uade.puppies_tpo.application.service.AlarmaService;
import com.uade.puppies_tpo.application.service.AnimalService;
import com.uade.puppies_tpo.application.service.NotificacionService;
import com.uade.puppies_tpo.application.service.VeterinarioService;
import com.uade.puppies_tpo.application.service.VisitaService;
import com.uade.puppies_tpo.domain.usuario.Veterinario;
import com.uade.puppies_tpo.domain.usuario.Visitador;
import com.uade.puppies_tpo.repository.IAlarmaRepository;
import com.uade.puppies_tpo.repository.IAnimalRepository;
import com.uade.puppies_tpo.repository.IClienteRepository;
import com.uade.puppies_tpo.repository.IFichaAdopcionRepository;
import com.uade.puppies_tpo.repository.ISeguimientoAdopcionRepository;
import com.uade.puppies_tpo.repository.IVeterinarioRepository;
import com.uade.puppies_tpo.repository.IVisitaRepository;
import com.uade.puppies_tpo.repository.IVisitadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion Spring: declara los servicios como beans. Los repositorios son
 * provistos por Spring Data JPA (implementaciones generadas automaticamente a
 * partir de las interfaces en repository/jpa/).
 *
 * Los servicios y el dominio NO tienen anotaciones Spring: se construyen por
 * constructor (DIP: dependen de interfaces, no de implementaciones concretas).
 */
@Configuration
public class AppConfig {

    // ── Servicios ─────────────────────────────────────────────────────────────

    @Bean
    public NotificacionService notificacionService(IVeterinarioRepository vetRepo) {
        return new NotificacionService(vetRepo);
    }

    @Bean
    public AnimalService animalService(IAnimalRepository animalRepo) {
        return new AnimalService(animalRepo);
    }

    @Bean
    public AlarmaService alarmaService(IAlarmaRepository alarmaRepo,
                                       IAnimalRepository animalRepo,
                                       IVeterinarioRepository veterinarioRepo,
                                       NotificacionService notificacionService) {
        return new AlarmaService(alarmaRepo, animalRepo, veterinarioRepo, notificacionService);
    }

    @Bean
    public AdopcionService adopcionService(IClienteRepository clienteRepo,
                                           IAnimalRepository animalRepo,
                                           IFichaAdopcionRepository fichaRepo,
                                           ISeguimientoAdopcionRepository seguimientoRepo,
                                           IVisitadorRepository visitadorRepo) {
        return new AdopcionService(clienteRepo, animalRepo, fichaRepo, seguimientoRepo, visitadorRepo);
    }

    @Bean
    public VisitaService visitaService(IVisitaRepository visitaRepo,
                                       ISeguimientoAdopcionRepository seguimientoRepo) {
        return new VisitaService(visitaRepo, seguimientoRepo);
    }

    @Bean
    public VeterinarioService veterinarioService(IVeterinarioRepository vetRepo) {
        return new VeterinarioService(vetRepo);
    }

    // ── Datos de demo ─────────────────────────────────────────────────────────

    @Bean
    public CommandLineRunner initDemoData(IVeterinarioRepository vetRepo,
                                          IVisitadorRepository visRepo) {
        return args -> {
            vetRepo.save(new Veterinario("v1", "Dr. Garcia", "garcia@gudboy.com", "111", "MP-001"));
            vetRepo.save(new Veterinario("v2", "Dra. Perez", "perez@gudboy.com", "222", "MP-002"));
            visRepo.save(new Visitador("vis1", "Juan Lopez", "lopez@gudboy.com", "333", "Palermo"));
        };
    }
}
