package com.uade.puppies_tpo.domain.accion;

/** Accion concreta: colocar vacuna. */
public class ColocarVacunaAccion extends AccionVeterinariaBase {

    @Override
    protected String descripcion() {
        return "Colocar vacuna";
    }
}
