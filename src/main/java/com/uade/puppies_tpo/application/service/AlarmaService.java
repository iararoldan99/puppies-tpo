package com.uade.puppies_tpo.application.service;

import com.uade.puppies_tpo.application.dto.ActualizarAlarmaDTO;
import com.uade.puppies_tpo.application.dto.AlarmaDTO;
import com.uade.puppies_tpo.application.dto.AtenderAlarmaDTO;
import com.uade.puppies_tpo.application.dto.CrearAlarmaDTO;
import com.uade.puppies_tpo.application.mapper.DtoMapper;
import com.uade.puppies_tpo.domain.accion.AccionFactory;
import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.enums.EstadoAlarmaEnum;
import com.uade.puppies_tpo.domain.registro.RegistroAccion;
import com.uade.puppies_tpo.domain.registro.RegistroFactory;
import com.uade.puppies_tpo.domain.registro.RegistroTratamientoMedico;
import com.uade.puppies_tpo.domain.usuario.Veterinario;
import com.uade.puppies_tpo.repository.IAlarmaRepository;
import com.uade.puppies_tpo.repository.IAnimalRepository;
import com.uade.puppies_tpo.repository.IVeterinarioRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Orquesta el ciclo de las alarmas: creacion/actualizacion configurables por
 * animal, disparo (que notifica a los veterinarios) y atencion.
 */
public class AlarmaService {

    private final IAlarmaRepository alarmaRepository;
    private final IAnimalRepository animalRepository;
    private final IVeterinarioRepository veterinarioRepository;
    private final NotificacionService notificacionService;

    public AlarmaService(IAlarmaRepository alarmaRepository, IAnimalRepository animalRepository,
                         IVeterinarioRepository veterinarioRepository,
                         NotificacionService notificacionService) {
        this.alarmaRepository = alarmaRepository;
        this.animalRepository = animalRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.notificacionService = notificacionService;
    }

    public List<AlarmaDTO> listarTodas() {
        return alarmaRepository.findAll().stream()
                .map(DtoMapper::toAlarmaDTO)
                .toList();
    }

    public AlarmaDTO obtenerAlarma(Long id) {
        return DtoMapper.toAlarmaDTO(buscar(id));
    }

    public AlarmaDTO crearAlarma(CrearAlarmaDTO dto) {
        Alarma alarma = new Alarma(null, dto.periodicidad());
        alarma.setAnimalId(dto.animalId());
        dto.acciones().forEach(nombre -> alarma.agregarAccion(AccionFactory.porNombre(nombre)));
        alarmaRepository.save(alarma);
        animalRepository.findById(dto.animalId()).ifPresent(animal -> {
            animal.getFichaTecnica().agregarAlarma(alarma);
            animalRepository.save(animal);
        });
        return DtoMapper.toAlarmaDTO(alarma);
    }

    public AlarmaDTO actualizarAlarma(Long alarmaId, ActualizarAlarmaDTO dto) {
        Alarma alarma = buscar(alarmaId);
        alarma.setPeriodicidad(dto.periodicidad());
        alarma.getGrupoAcciones().limpiar();
        dto.acciones().forEach(nombre -> alarma.agregarAccion(AccionFactory.porNombre(nombre)));
        alarmaRepository.save(alarma);
        return DtoMapper.toAlarmaDTO(alarma);
    }

    public void dispararAlarma(Long alarmaId) {
        Alarma alarma = buscar(alarmaId);
        alarma.disparar();
        notificacionService.notificarVeterinarios(alarma);
        alarmaRepository.save(alarma);
    }

    /**
     * Es el veterinario quien atiende la alarma: se busca por id y se delega en
     * el metodo de dominio {@code Veterinario.atenderAlarma(...)} (que completa el
     * grupo de acciones). Ademas, lo realizado queda anotado en el historial
     * clinico del animal: la {@link RegistroFactory} (Simple Factory) decide si
     * crear un {@code RegistroControl} o un {@code RegistroTratamientoMedico} segun
     * {@code dto.esTratamiento()}.
     */
    public void atenderAlarma(Long alarmaId, AtenderAlarmaDTO dto) {
        Alarma alarma = buscar(alarmaId);
        Veterinario veterinario = veterinarioRepository.findById(dto.veterinarioId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Veterinario no encontrado: " + dto.veterinarioId()));
        veterinario.atenderAlarma(alarma, dto.comentario());

        // Anotar en el historial clinico que se hizo (control vs tratamiento).
        RegistroAccion registro = RegistroFactory.registrar(dto.esTratamiento(),
                alarma.getGrupoAcciones(), dto.comentario(), veterinario, LocalDate.now());
        if (registro instanceof RegistroTratamientoMedico tratamiento) {
            tratamiento.setFinalizoTratamiento(true);
        }
        if (alarma.getAnimalId() != null) {
            animalRepository.findById(alarma.getAnimalId()).ifPresent(animal -> {
                animal.getFichaTecnica().agregarRegistro(registro);
                animalRepository.save(animal);
            });
        }

        alarma.setEstado(EstadoAlarmaEnum.INACTIVA);
        alarmaRepository.save(alarma);
    }

    private Alarma buscar(Long id) {
        return alarmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alarma no encontrada: " + id));
    }
}
