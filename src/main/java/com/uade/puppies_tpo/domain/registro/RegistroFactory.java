package com.uade.puppies_tpo.domain.registro;

import com.uade.puppies_tpo.domain.accion.IAccionVeterinaria;
import com.uade.puppies_tpo.domain.usuario.Veterinario;

import java.time.LocalDate;

/**
 * Factory Method: centraliza la decision de que subtipo de registro instanciar
 * (control de rutina vs tratamiento medico). El unico {@code if} que distingue
 * los dos casos vive aca, en lugar de repetirse en cada lugar que registra.
 */
public final class RegistroFactory {

    private RegistroFactory() {
    }

    public static RegistroAccion registrar(boolean esTratamiento, IAccionVeterinaria accion,
                                           String comentario, Veterinario veterinario,
                                           LocalDate fecha) {
        if (esTratamiento) {
            return new RegistroTratamientoMedico(accion, comentario, veterinario, fecha);
        }
        return new RegistroControl(accion, comentario, veterinario, fecha);
    }
}
