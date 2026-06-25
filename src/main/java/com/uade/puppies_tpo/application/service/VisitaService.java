package com.uade.puppies_tpo.application.service;

import com.uade.puppies_tpo.application.dto.EncuestaDTO;
import com.uade.puppies_tpo.application.dto.VisitaDTO;
import com.uade.puppies_tpo.application.mapper.DtoMapper;
import com.uade.puppies_tpo.domain.adopcion.Encuesta;
import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.domain.enums.CalificacionEnum;
import com.uade.puppies_tpo.repository.ISeguimientoAdopcionRepository;
import com.uade.puppies_tpo.repository.IVisitaRepository;

import java.time.LocalDate;

/**
 * Orquesta el registro de visitas, la carga de la encuesta y la finalizacion de
 * la visita dentro de un seguimiento.
 */
public class VisitaService {

    private final IVisitaRepository visitaRepository;
    private final ISeguimientoAdopcionRepository seguimientoRepository;

    public VisitaService(IVisitaRepository visitaRepository,
                         ISeguimientoAdopcionRepository seguimientoRepository) {
        this.visitaRepository = visitaRepository;
        this.seguimientoRepository = seguimientoRepository;
    }

    public VisitaDTO registrarVisita(Long seguimientoId) {
        SeguimientoAdopcion seguimiento = seguimientoRepository.findById(seguimientoId)
                .orElseThrow(() -> new IllegalArgumentException("Seguimiento no encontrado: " + seguimientoId));
        Visita visita = new Visita(null, LocalDate.now(), "A convenir");
        seguimiento.agregarVisita(visita);
        visitaRepository.save(visita);
        seguimientoRepository.save(seguimiento);
        return DtoMapper.toVisitaDTO(visita);
    }

    public void responderEncuesta(Long visitaId, EncuestaDTO dto) {
        Visita visita = buscar(visitaId);
        Encuesta encuesta = new Encuesta(LocalDate.now(),
                parseCalificacion(dto.estadoAnimal()),
                parseCalificacion(dto.limpieza()),
                parseCalificacion(dto.ambiente()),
                true);
        visita.setEncuesta(encuesta);
        visitaRepository.save(visita);
    }

    public void finalizarVisita(Long visitaId, boolean continuar) {
        Visita visita = buscar(visitaId);
        visita.finalizar();
        visitaRepository.save(visita);
    }

    private Visita buscar(Long id) {
        return visitaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visita no encontrada: " + id));
    }

    private CalificacionEnum parseCalificacion(String valor) {
        return CalificacionEnum.valueOf(valor.trim().toUpperCase());
    }
}
