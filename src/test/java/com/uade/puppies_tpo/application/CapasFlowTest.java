package com.uade.puppies_tpo.application;

import com.uade.puppies_tpo.application.dto.AnimalDTO;
import com.uade.puppies_tpo.application.dto.AtenderAlarmaDTO;
import com.uade.puppies_tpo.application.dto.ClienteDTO;
import com.uade.puppies_tpo.application.dto.CrearAlarmaDTO;
import com.uade.puppies_tpo.application.dto.CrearAnimalDTO;
import com.uade.puppies_tpo.application.dto.CrearClienteDTO;
import com.uade.puppies_tpo.application.dto.CrearFichaAdopcionDTO;
import com.uade.puppies_tpo.application.dto.FichaAdopcionDTO;
import com.uade.puppies_tpo.application.service.AdopcionService;
import com.uade.puppies_tpo.application.service.AlarmaService;
import com.uade.puppies_tpo.application.service.AnimalService;
import com.uade.puppies_tpo.application.service.NotificacionService;
import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import com.uade.puppies_tpo.domain.usuario.Veterinario;
import com.uade.puppies_tpo.presentation.controller.AdopcionController;
import com.uade.puppies_tpo.presentation.controller.AnimalController;
import com.uade.puppies_tpo.repository.IAlarmaRepository;
import com.uade.puppies_tpo.repository.IAnimalRepository;
import com.uade.puppies_tpo.repository.IClienteRepository;
import com.uade.puppies_tpo.repository.IFichaAdopcionRepository;
import com.uade.puppies_tpo.repository.ISeguimientoAdopcionRepository;
import com.uade.puppies_tpo.repository.IVeterinarioRepository;
import com.uade.puppies_tpo.repository.IVisitadorRepository;
import com.uade.puppies_tpo.repository.inmemory.AlarmaRepositoryInMemory;
import com.uade.puppies_tpo.repository.inmemory.AnimalRepositoryInMemory;
import com.uade.puppies_tpo.repository.inmemory.ClienteRepositoryInMemory;
import com.uade.puppies_tpo.repository.inmemory.FichaAdopcionRepositoryInMemory;
import com.uade.puppies_tpo.repository.inmemory.SeguimientoRepositoryInMemory;
import com.uade.puppies_tpo.repository.inmemory.VeterinarioRepositoryInMemory;
import com.uade.puppies_tpo.repository.inmemory.VisitadorRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifica el flujo completo Controller -> Service -> Repository (in-memory) con
 * DTOs, sin base de datos, para las capas que completan la vista del UML.
 */
class CapasFlowTest {

    @Test
    void flujoDeAnimalCrearObtenerYTratamiento() {
        IAnimalRepository repo = new AnimalRepositoryInMemory();
        AnimalController controller = new AnimalController(new AnimalService(repo));

        AnimalDTO creado = controller.crear(
                new CrearAnimalDTO("Perro", 0.4, 8.0, 3, TipoDeAnimal.DOMESTICO));

        assertNotNull(creado.id());
        assertTrue(creado.puedeSerAdoptado());

        controller.exportarFicha(creado.id(), "PDF");

        controller.crear(new CrearAnimalDTO("Gato", 0.3, 4.0, 2, TipoDeAnimal.DOMESTICO));
        // Tras iniciar tratamiento, el animal deja de ser adoptable.
        new AnimalService(repo).iniciarTratamiento(creado.id());
        assertFalse(controller.obtener(creado.id()).puedeSerAdoptado());
    }

    @Test
    void flujoDeAdopcionEndToEnd() {
        IClienteRepository clienteRepo = new ClienteRepositoryInMemory();
        IAnimalRepository animalRepo = new AnimalRepositoryInMemory();
        IFichaAdopcionRepository fichaRepo = new FichaAdopcionRepositoryInMemory();
        ISeguimientoAdopcionRepository seguimientoRepo = new SeguimientoRepositoryInMemory();
        IVisitadorRepository visitadorRepo = new VisitadorRepositoryInMemory();

        AdopcionService service = new AdopcionService(
                clienteRepo, animalRepo, fichaRepo, seguimientoRepo, visitadorRepo);
        AdopcionController controller = new AdopcionController(service);

        // Alta de cliente.
        ClienteDTO cliente = controller.registrarCliente(new CrearClienteDTO(
                "Ana Gomez", "Soltera", "ana@mail.com", "111", "EMPLEADO", false, "Compania", List.of()));

        // Alta de un animal domestico disponible.
        AnimalDTO animal = new AnimalService(animalRepo).registrarAnimal(
                new CrearAnimalDTO("Perro", 0.4, 8.0, 3, TipoDeAnimal.DOMESTICO));

        FichaAdopcionDTO ficha = controller.adoptar(
                new CrearFichaAdopcionDTO(cliente.id(), animal.id()));

        assertNotNull(ficha.id());
        assertEquals(cliente.id(), ficha.cliente().id());
        // El animal quedo adoptado y vinculado al cliente.
        assertFalse(new AnimalService(animalRepo).obtenerAnimal(animal.id()).puedeSerAdoptado());
        assertEquals(1, ficha.cliente().animalesAdoptados().size());
    }

    @Test
    void flujoDeAlarmaDisparoNotificaVeterinarios() {
        IAlarmaRepository alarmaRepo = new AlarmaRepositoryInMemory();
        IAnimalRepository animalRepo = new AnimalRepositoryInMemory();
        IVeterinarioRepository vetRepo = new VeterinarioRepositoryInMemory();
        Veterinario vet = new Veterinario("v1", "Dra. Paez", "paez@ref.com", "111", "MP-100");
        vetRepo.save(vet);

        NotificacionService notificacion = new NotificacionService(vetRepo);
        AlarmaService alarmaService = new AlarmaService(alarmaRepo, animalRepo, vetRepo, notificacion);

        var alarma = alarmaService.crearAlarma(new CrearAlarmaDTO(
                999L, PeriodicidadAlarmaEnum.SEMANAL, List.of("VACUNA", "PESO")));
        assertEquals(2, alarma.acciones().size());

        alarmaService.dispararAlarma(alarma.id());
        assertEquals(1, vet.getAlarmasNotificadas().size());

        alarmaService.atenderAlarma(alarma.id(),
                new AtenderAlarmaDTO("Todo en orden", false, "v1"));
    }
}
