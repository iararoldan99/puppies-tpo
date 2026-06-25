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
import com.uade.puppies_tpo.repository.IAlarmaRepository;
import com.uade.puppies_tpo.repository.IAnimalRepository;

import java.util.List;

/**
 * Orquesta el ciclo de las alarmas: creacion/actualizacion configurables por
 * animal, disparo (que notifica a los veterinarios) y atencion.
 */
public class AlarmaService {

    private final IAlarmaRepository alarmaRepository;
    private final IAnimalRepository animalRepository;
    private final NotificacionService notificacionService;

    public AlarmaService(IAlarmaRepository alarmaRepository, IAnimalRepository animalRepository,
                         NotificacionService notificacionService) {
        this.alarmaRepository = alarmaRepository;
        this.animalRepository = animalRepository;
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

    public void atenderAlarma(Long alarmaId, AtenderAlarmaDTO dto) {
        Alarma alarma = buscar(alarmaId);
        alarma.getGrupoAcciones().completar(dto.comentario());
        alarma.setEstado(EstadoAlarmaEnum.INACTIVA);
        alarmaRepository.save(alarma);
    }

    private Alarma buscar(Long id) {
        return alarmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alarma no encontrada: " + id));
    }
}
