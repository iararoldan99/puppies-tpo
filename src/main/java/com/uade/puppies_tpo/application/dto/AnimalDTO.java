package com.uade.puppies_tpo.application.dto;

/**
 * DTO de salida con la vista de un animal. Transporta datos entre capas sin
 * exponer la entidad de dominio.
 */
public record AnimalDTO(
        Long id,
        String nombre,
        String especie,
        String condicionMedica,
        boolean puedeSerAdoptado,
        String estadoCiclo) {
}
