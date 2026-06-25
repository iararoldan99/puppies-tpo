package com.uade.puppies_tpo.presentation.controller;

import com.uade.puppies_tpo.application.service.NotificacionService;
import com.uade.puppies_tpo.domain.alarma.Alarma;

/**
 * Punto de entrada para notificar el disparo de una alarma a los veterinarios.
 * Delega en el service.
 */
public class NotificacionController {

    private final NotificacionService servicio;

    public NotificacionController(NotificacionService servicio) {
        this.servicio = servicio;
    }

    public void notificar(Alarma alarma) {
        servicio.notificarVeterinarios(alarma);
    }
}
