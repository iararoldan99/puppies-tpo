package com.uade.puppies_tpo.application.dto;

import java.time.LocalDate;

/** DTO de un item del historial clinico. */
public record RegistroAccionDTO(
        LocalDate fecha,
        String comentario,
        String tipoRegistro,
        boolean finalizoTratamiento) {
}
