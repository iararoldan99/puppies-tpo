package com.uade.puppies_tpo.application.dto;

/** DTO de entrada para registrar un veterinario. */
public record CrearVeterinarioDTO(
        String id,
        String nombre,
        String email,
        String telefono,
        String matricula) {
}
