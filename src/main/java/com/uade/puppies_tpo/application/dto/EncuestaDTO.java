package com.uade.puppies_tpo.application.dto;

/** DTO de entrada con las respuestas de la encuesta (escala malo/regular/bueno). */
public record EncuestaDTO(
        String estadoAnimal,
        String limpieza,
        String ambiente) {
}
