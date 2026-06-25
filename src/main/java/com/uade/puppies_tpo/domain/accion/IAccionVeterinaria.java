package com.uade.puppies_tpo.domain.accion;

import com.uade.puppies_tpo.domain.enums.EstadoAccion;

/**
 * Patron Command: cada accion veterinaria se encapsula con una interfaz
 * uniforme ({@code ejecutar} / {@code completar}), de modo que la alarma pueda
 * dispararlas sin conocer su implementacion concreta.
 *
 * Es ademas el componente del patron Composite: {@link GrupoAcciones} implementa
 * esta misma interfaz para tratar a un grupo igual que a una accion individual.
 */
public interface IAccionVeterinaria {

    /** Ejecuta la accion (la alarma la dispara). */
    void ejecutar();

    /** El veterinario marca la accion como realizada, con un comentario. */
    void completar(String comentario);

    EstadoAccion getEstado();
}
