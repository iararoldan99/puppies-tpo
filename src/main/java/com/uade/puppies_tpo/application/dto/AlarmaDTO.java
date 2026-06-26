package com.uade.puppies_tpo.application.dto;

import com.uade.puppies_tpo.domain.enums.EstadoAlarmaEnum;
import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;

import java.util.List;

/** DTO de salida de una alarma; las acciones se transportan como nombres. */
public record AlarmaDTO(
        Long id,
        PeriodicidadAlarmaEnum periodicidad,
        EstadoAlarmaEnum estado,
        List<String> acciones) {
}
