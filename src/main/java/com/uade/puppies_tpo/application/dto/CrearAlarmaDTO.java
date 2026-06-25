package com.uade.puppies_tpo.application.dto;

import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;

import java.util.List;

/** DTO de entrada para crear una alarma sobre un animal. */
public record CrearAlarmaDTO(
        Long animalId,
        PeriodicidadAlarmaEnum periodicidad,
        List<String> acciones) {
}
