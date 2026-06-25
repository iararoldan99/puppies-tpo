package com.uade.puppies_tpo.application.dto;

/**
 * DTO de entrada cuando un veterinario atiende una alarma. {@code esTratamiento}
 * distingue los dos casos del historial: control de rutina (false) o tratamiento
 * medico (true) — es el dato que usa la {@code RegistroFactory} para decidir que
 * tipo de registro crear.
 */
public record AtenderAlarmaDTO(
        String comentario,
        boolean esTratamiento,
        String veterinarioId) {
}
