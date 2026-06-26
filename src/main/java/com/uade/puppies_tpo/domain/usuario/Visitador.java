package com.uade.puppies_tpo.domain.usuario;

import com.uade.puppies_tpo.domain.adopcion.Encuesta;
import com.uade.puppies_tpo.domain.adopcion.Visita;

/**
 * Visitador: usuario responsable del seguimiento de una adopcion. Realiza las
 * visitas a domicilio y responde la encuesta sobre el animal.
 */
public class Visitador extends Usuario {

    private String zona;

    public Visitador(String id, String nombre, String email, String telefono, String zona) {
        super(id, nombre, email, telefono);
        this.zona = zona;
    }

    /** Realiza (finaliza) una visita al nuevo dueño. */
    public void realizarVisita(Visita visita) {
        visita.finalizar();
    }

    /** Carga la encuesta respondida en la visita correspondiente. */
    public void responderEncuesta(Visita visita, Encuesta encuesta) {
        visita.setEncuesta(encuesta);
    }

    public String getZona() {
        return zona;
    }
}
