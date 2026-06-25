package com.uade.puppies_tpo.application.dto;

/** DTO de salida de un seguimiento de adopcion configurado. */
public record SeguimientoAdopcionDTO(
        Long id,
        String visitador,
        int cadencia,
        String preferencia) {
}
