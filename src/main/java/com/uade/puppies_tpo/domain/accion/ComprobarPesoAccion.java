package com.uade.puppies_tpo.domain.accion;

/** Accion concreta: comprobar peso y tamanio. */
public class ComprobarPesoAccion extends AccionVeterinariaBase {

    @Override
    protected String descripcion() {
        return "Comprobar peso y tamanio";
    }
}
