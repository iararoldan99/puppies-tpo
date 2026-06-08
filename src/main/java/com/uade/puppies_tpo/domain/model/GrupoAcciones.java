package com.uade.puppies_tpo.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrupoAcciones implements IAccionVeterinaria {

    private List<IAccionVeterinaria> acciones;

    public GrupoAcciones() {
        this.acciones = new ArrayList<>();
    }

    public void agregar(IAccionVeterinaria accion) {
        this.acciones.add(accion);
    }

    @Override
    public void ejecutar() {
        for (IAccionVeterinaria accion : acciones) {
            accion.ejecutar();
        }
    }

    @Override
    public void completar(String comentario) {
        for (IAccionVeterinaria accion : acciones) {
            accion.completar(comentario);
        }
    }

    public List<IAccionVeterinaria> getAcciones() {
        return Collections.unmodifiableList(acciones);
    }
}