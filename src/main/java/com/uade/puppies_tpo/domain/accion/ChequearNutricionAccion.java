package com.uade.puppies_tpo.domain.accion;

/** Accion concreta: chequear nutricion. */
public class ChequearNutricionAccion extends AccionVeterinariaBase {

    @Override
    protected String descripcion() {
        return "Chequear nutricion";
    }
}
