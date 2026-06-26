package com.uade.puppies_tpo.presentation.controller;

import com.uade.puppies_tpo.application.dto.EncuestaDTO;
import com.uade.puppies_tpo.application.dto.VisitaDTO;
import com.uade.puppies_tpo.application.service.VisitaService;

/** Recibe las peticiones de visitas y delega en el service. */
public class VisitaController {

    private final VisitaService servicio;

    public VisitaController(VisitaService servicio) {
        this.servicio = servicio;
    }

    public VisitaDTO registrar(Long seguimientoId) {
        return servicio.registrarVisita(seguimientoId);
    }

    public void responderEncuesta(Long id, EncuestaDTO dto) {
        servicio.responderEncuesta(id, dto);
    }

    public void finalizar(Long id, boolean continuar) {
        servicio.finalizarVisita(id, continuar);
    }
}
