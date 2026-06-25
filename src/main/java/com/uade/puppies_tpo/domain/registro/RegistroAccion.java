package com.uade.puppies_tpo.domain.registro;

import com.uade.puppies_tpo.domain.accion.IAccionVeterinaria;
import com.uade.puppies_tpo.domain.usuario.Veterinario;

import java.time.LocalDate;

/**
 * Registro de algo realizado sobre el animal, que queda en su historial clinico.
 * Es abstracto: el enunciado distingue controles de rutina (con mediciones) de
 * tratamientos medicos (con marca de finalizacion).
 */
public abstract class RegistroAccion {

    private final IAccionVeterinaria accion;
    private final String comentario;
    private final Veterinario veterinario;
    private final LocalDate fecha;

    protected RegistroAccion(IAccionVeterinaria accion, String comentario,
                             Veterinario veterinario, LocalDate fecha) {
        this.accion = accion;
        this.comentario = comentario;
        this.veterinario = veterinario;
        this.fecha = fecha;
    }

    public IAccionVeterinaria getAccion() {
        return accion;
    }

    public String getComentario() {
        return comentario;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
