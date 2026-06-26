package com.uade.puppies_tpo.application.dto;

/** DTO de salida para un veterinario. Incluye cuantas alarmas recibio (Observer). */
public record VeterinarioDTO(
        String id,
        String nombre,
        String email,
        String matricula,
        int alarmasNotificadas) {
}
