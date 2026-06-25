package com.uade.puppies_tpo.application.dto;

import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;

/** DTO de entrada para registrar un animal. */
public record CrearAnimalDTO(
        String especie,
        double altura,
        double peso,
        int edadAprox,
        TipoDeAnimal tipoDeAnimal) {
}
