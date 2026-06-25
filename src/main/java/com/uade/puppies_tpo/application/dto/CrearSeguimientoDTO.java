package com.uade.puppies_tpo.application.dto;

/** DTO de entrada para configurar el seguimiento posterior a una adopcion. */
public record CrearSeguimientoDTO(
        Long fichaAdopcionId,
        Long visitadorId,
        int cadencia,
        String rangoHorario,
        String preferencia,
        int diasAnticipacion) {
}
