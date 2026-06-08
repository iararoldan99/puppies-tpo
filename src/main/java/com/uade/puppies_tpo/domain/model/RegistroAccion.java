package com.uade.puppies_tpo.domain.model;

import java.time.LocalDate;

public class RegistroAccion {

    private LocalDate fecha;
    private String comentario;
    private boolean finalizoTratamiento;
    private IAccionVeterinaria accionRealizada;

    public RegistroAccion(String comentario, boolean finalizoTratamiento, IAccionVeterinaria accionRealizada) {
        this.fecha = LocalDate.now();
        this.comentario = comentario;
        this.finalizoTratamiento = finalizoTratamiento;
        this.accionRealizada = accionRealizada;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean isFinalizoTratamiento() {
        return finalizoTratamiento;
    }

    public IAccionVeterinaria getAccionRealizada() {
        return accionRealizada;
    }
}