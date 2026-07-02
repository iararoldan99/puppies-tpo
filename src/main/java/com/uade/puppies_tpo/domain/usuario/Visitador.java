package com.uade.puppies_tpo.domain.usuario;

import com.uade.puppies_tpo.domain.adopcion.Encuesta;
import com.uade.puppies_tpo.domain.adopcion.Visita;

public class Visitador extends Usuario {

    private String zona;

    public Visitador(String id, String nombre, String email, String telefono, String zona) {
        super(id, nombre, email, telefono);
        this.zona = zona;
    }

    public void realizarVisita(Visita visita) { visita.finalizar(); }
    public void responderEncuesta(Visita visita, Encuesta encuesta) { visita.setEncuesta(encuesta); }
    public String getZona() { return zona; }
}
