package com.uade.puppies_tpo.domain.registro;

import com.uade.puppies_tpo.domain.accion.IAccionVeterinaria;
import com.uade.puppies_tpo.domain.usuario.Veterinario;

import java.time.LocalDate;

/**
 * Registro de un tratamiento medico: ademas de lo comun, indica si el
 * tratamiento finalizo o no.
 */
public class RegistroTratamientoMedico extends RegistroAccion {

    private boolean finalizoTratamiento;

    public RegistroTratamientoMedico(IAccionVeterinaria accion, String comentario,
                                     Veterinario veterinario, LocalDate fecha) {
        super(accion, comentario, veterinario, fecha);
    }

    public boolean isFinalizoTratamiento() {
        return finalizoTratamiento;
    }

    public void setFinalizoTratamiento(boolean finalizoTratamiento) {
        this.finalizoTratamiento = finalizoTratamiento;
    }
}
