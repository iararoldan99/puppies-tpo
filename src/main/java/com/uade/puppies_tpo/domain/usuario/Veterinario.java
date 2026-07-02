package com.uade.puppies_tpo.domain.usuario;

import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.domain.alarma.ObservadorAlarma;

import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Usuario implements ObservadorAlarma {

    private String matricula;
    private final List<Alarma> alarmasNotificadas = new ArrayList<>();

    public Veterinario(String id, String nombre, String email, String telefono, String matricula) {
        super(id, nombre, email, telefono);
        this.matricula = matricula;
    }

    public void atenderAlarma(Alarma alarma, String comentario) {
        alarma.getGrupoAcciones().completar(comentario);
    }

    @Override
    public void onAlarmaDisparada(Alarma alarma) { alarmasNotificadas.add(alarma); }

    public String getMatricula() { return matricula; }
    public List<Alarma> getAlarmasNotificadas() { return List.copyOf(alarmasNotificadas); }
}
