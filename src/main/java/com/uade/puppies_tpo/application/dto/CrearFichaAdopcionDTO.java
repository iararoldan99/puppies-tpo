package com.uade.puppies_tpo.application.dto;

/** DTO de entrada para procesar una adopcion (cliente + animal). */
public record CrearFichaAdopcionDTO(
        Long clienteId,
        Long animalId) {
}
