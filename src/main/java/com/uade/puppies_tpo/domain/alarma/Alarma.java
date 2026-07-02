package com.uade.puppies_tpo.domain.alarma;

import com.uade.puppies_tpo.domain.accion.GrupoAcciones;
import com.uade.puppies_tpo.domain.accion.IAccionVeterinaria;
import com.uade.puppies_tpo.domain.enums.EstadoAlarmaEnum;
import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;

import java.util.ArrayList;
import java.util.List;

public class Alarma {

    private Long id;
    private Long animalId;
    private PeriodicidadAlarmaEnum periodicidad;
    private EstadoAlarmaEnum estado;
    private final GrupoAcciones grupoAcciones = new GrupoAcciones();
    private final List<ObservadorAlarma> observadores = new ArrayList<>();

    public Alarma(Long id, PeriodicidadAlarmaEnum periodicidad) {
        this.id = id;
        this.periodicidad = periodicidad;
        this.estado = EstadoAlarmaEnum.ACTIVA;
    }

    public void agregarAccion(IAccionVeterinaria accion) { grupoAcciones.agregar(accion); }
    public void suscribir(ObservadorAlarma observador) { observadores.add(observador); }

    public void disparar() {
        grupoAcciones.ejecutar();
        this.estado = EstadoAlarmaEnum.DISPARADA;
        observadores.forEach(o -> o.onAlarmaDisparada(this));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public PeriodicidadAlarmaEnum getPeriodicidad() { return periodicidad; }
    public void setPeriodicidad(PeriodicidadAlarmaEnum periodicidad) { this.periodicidad = periodicidad; }
    public EstadoAlarmaEnum getEstado() { return estado; }
    public void setEstado(EstadoAlarmaEnum estado) { this.estado = estado; }
    public GrupoAcciones getGrupoAcciones() { return grupoAcciones; }
    public List<ObservadorAlarma> getObservadores() { return List.copyOf(observadores); }
}
