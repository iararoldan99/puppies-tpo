package com.uade.puppies_tpo.application.dto;

import java.time.LocalDate;

/** DTO de salida de una ficha de adopcion procesada. */
public record FichaAdopcionDTO(
        Long id,
        ClienteDTO cliente,
        AnimalDTO animal,
        LocalDate fecha) {
}
