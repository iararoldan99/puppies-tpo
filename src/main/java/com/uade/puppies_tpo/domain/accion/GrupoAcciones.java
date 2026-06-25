package com.uade.puppies_tpo.domain.accion;

import com.uade.puppies_tpo.domain.enums.EstadoAccion;

import java.util.ArrayList;
import java.util.List;

/**
 * Patron Composite: un grupo de acciones se trata igual que una accion
 * individual (implementa {@link IAccionVeterinaria}). Es el punto de entrada que
 * usa la alarma para disparar "el grupo de acciones" del enunciado de una sola
 * vez.
 */
public class GrupoAcciones implements IAccionVeterinaria {

    private final List<IAccionVeterinaria> acciones = new ArrayList<>();

    public void agregar(IAccionVeterinaria accion) {
        acciones.add(accion);
    }

    public List<IAccionVeterinaria> getAcciones() {
        return List.copyOf(acciones);
    }

    @Override
    public void ejecutar() {
        acciones.forEach(IAccionVeterinaria::ejecutar);
    }

    @Override
    public void completar(String comentario) {
        acciones.forEach(accion -> accion.completar(comentario));
    }

    /** El grupo esta COMPLETADA solo si tiene acciones y todas lo estan. */
    @Override
    public EstadoAccion getEstado() {
        if (acciones.isEmpty()) {
            return EstadoAccion.PENDIENTE;
        }
        boolean todasCompletadas = acciones.stream()
                .allMatch(accion -> accion.getEstado() == EstadoAccion.COMPLETADA);
        return todasCompletadas ? EstadoAccion.COMPLETADA : EstadoAccion.PENDIENTE;
    }
}
