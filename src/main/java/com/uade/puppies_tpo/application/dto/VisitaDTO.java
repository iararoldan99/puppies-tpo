package com.uade.puppies_tpo.application.dto;

import java.time.LocalDate;

/** DTO de salida de una visita del seguimiento. */
public record VisitaDTO(
        Long id,
        LocalDate fecha,
        EncuestaDTO encuesta,
        boolean continuarVisitas) {
}
