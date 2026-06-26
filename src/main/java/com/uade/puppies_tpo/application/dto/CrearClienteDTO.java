package com.uade.puppies_tpo.application.dto;

import java.util.List;

/** DTO de entrada para registrar un cliente interesado en adoptar. */
public record CrearClienteDTO(
        String nombre,
        String estadoCivil,
        String email,
        String telefono,
        String ocupacion,
        boolean otrasMascotas,
        String motivoAdopcion,
        List<Long> animalesInteresados) {
}
