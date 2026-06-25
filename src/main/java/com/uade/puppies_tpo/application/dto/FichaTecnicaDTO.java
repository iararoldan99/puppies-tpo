package com.uade.puppies_tpo.application.dto;

import java.util.List;

/** DTO de la ficha tecnica, con el historial clinico aplanado a texto. */
public record FichaTecnicaDTO(
        String especie,
        double altura,
        double peso,
        int edadAprox,
        List<String> historialClinico) {
}
