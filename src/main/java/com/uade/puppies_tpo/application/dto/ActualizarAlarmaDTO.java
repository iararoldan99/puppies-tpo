package com.uade.puppies_tpo.application.dto;

import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;

import java.util.List;

/** DTO de entrada para actualizar la periodicidad y acciones de una alarma. */
public record ActualizarAlarmaDTO(
        PeriodicidadAlarmaEnum periodicidad,
        List<String> acciones) {
}
