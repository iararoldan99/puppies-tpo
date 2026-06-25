package com.uade.puppies_tpo.application.dto;

/** DTO de entrada cuando un veterinario atiende una alarma. */
public record AtenderAlarmaDTO(
        String comentario,
        boolean finalizoTratamiento,
        String veterinarioId) {
}
