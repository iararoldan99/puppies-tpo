package com.uade.puppies_tpo.domain.accion;

import com.uade.puppies_tpo.domain.enums.EstadoAccion;

/**
 * Base comun de las acciones concretas (Template Method): centraliza el manejo
 * del estado (PENDIENTE/COMPLETADA), el comentario y la marca de ejecucion, para
 * no duplicar esa logica en cada comando. Las subclases solo aportan su
 * {@link #descripcion()}.
 */
public abstract class AccionVeterinariaBase implements IAccionVeterinaria {

    private EstadoAccion estado = EstadoAccion.PENDIENTE;
    private String comentario;
    private boolean ejecutada = false;

    /** Texto descriptivo de la accion concreta. */
    protected abstract String descripcion();

    @Override
    public void ejecutar() {
        this.ejecutada = true;
    }

    @Override
    public void completar(String comentario) {
        this.comentario = comentario;
        this.estado = EstadoAccion.COMPLETADA;
    }

    @Override
    public EstadoAccion getEstado() {
        return estado;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean fueEjecutada() {
        return ejecutada;
    }

    @Override
    public String toString() {
        return descripcion();
    }
}
