package com.uade.puppies_tpo.presentation.controller;

import com.uade.puppies_tpo.application.dto.ClienteDTO;
import com.uade.puppies_tpo.application.dto.CrearClienteDTO;
import com.uade.puppies_tpo.application.dto.CrearFichaAdopcionDTO;
import com.uade.puppies_tpo.application.dto.CrearSeguimientoDTO;
import com.uade.puppies_tpo.application.dto.FichaAdopcionDTO;
import com.uade.puppies_tpo.application.dto.SeguimientoAdopcionDTO;
import com.uade.puppies_tpo.application.service.AdopcionService;

/** Recibe las peticiones de adopciones y delega en el service. */
public class AdopcionController {

    private final AdopcionService servicio;

    public AdopcionController(AdopcionService servicio) {
        this.servicio = servicio;
    }

    public ClienteDTO registrarCliente(CrearClienteDTO dto) {
        return servicio.registrarCliente(dto);
    }

    public FichaAdopcionDTO adoptar(CrearFichaAdopcionDTO dto) {
        return servicio.procesarAdopcion(dto);
    }

    public SeguimientoAdopcionDTO configurarSeguimiento(CrearSeguimientoDTO dto) {
        return servicio.configurarSeguimiento(dto);
    }
}
