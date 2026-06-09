package com.uade.puppies_tpo.domain.model;

import java.time.LocalDate;

public abstract class RegistroAccion {

    private LocalDate fecha;
    private String comentario;
    private IAccionVeterinaria accionRealizada;
    private Veterinario veterinario;

    public RegistroAccion(String comentario, IAccionVeterinaria accionRealizada, Veterinario veterinario) {
        this.fecha = LocalDate.now();
        this.comentario = comentario;
        this.accionRealizada = accionRealizada;
        this.veterinario = veterinario;
    }
    public void actualizarFicha(FichaTecnicaAnimal ficha) {}

    public LocalDate getFecha() {
        return fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public IAccionVeterinaria getAccionRealizada() {
        return accionRealizada;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }
}