package com.uade.puppies_tpo.application.dto;

import java.util.List;

/** DTO de salida de un cliente. */
public record ClienteDTO(
        Long id,
        String nombre,
        String email,
        List<Long> animalesAdoptados) {
}
