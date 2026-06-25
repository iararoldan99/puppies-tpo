package com.uade.puppies_tpo.application.service;

import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.repository.IVeterinarioRepository;

/**
 * Notificaciones del sistema: alerta a los veterinarios cuando se dispara una
 * alarma y dispara los recordatorios del seguimiento por el canal preferido.
 */
public class NotificacionService {

    private final IVeterinarioRepository veterinarioRepository;

    public NotificacionService(IVeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    /** Notifica a todos los veterinarios; cualquiera puede atender la alarma. */
    public void notificarVeterinarios(Alarma alarma) {
        veterinarioRepository.findAll().forEach(veterinario -> veterinario.onAlarmaDisparada(alarma));
    }

    /** Envia un recordatorio por el canal preferido configurado en el seguimiento. */
    public void enviarRecordatorio(SeguimientoAdopcion seguimiento) {
        seguimiento.enviarRecordatorio("Recordatorio: proxima visita del seguimiento.");
    }
}
