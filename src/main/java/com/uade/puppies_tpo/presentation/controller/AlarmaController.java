package com.uade.puppies_tpo.presentation.controller;

import com.uade.puppies_tpo.application.dto.ActualizarAlarmaDTO;
import com.uade.puppies_tpo.application.dto.AlarmaDTO;
import com.uade.puppies_tpo.application.dto.AtenderAlarmaDTO;
import com.uade.puppies_tpo.application.dto.CrearAlarmaDTO;
import com.uade.puppies_tpo.application.service.AlarmaService;

/** Recibe las peticiones de alarmas y delega en el service. */
public class AlarmaController {

    private final AlarmaService servicio;

    public AlarmaController(AlarmaService servicio) {
        this.servicio = servicio;
    }

    public AlarmaDTO crear(CrearAlarmaDTO dto) {
        return servicio.crearAlarma(dto);
    }

    public AlarmaDTO actualizar(Long id, ActualizarAlarmaDTO dto) {
        return servicio.actualizarAlarma(id, dto);
    }

    public void atender(Long id, AtenderAlarmaDTO dto) {
        servicio.atenderAlarma(id, dto);
    }
}
