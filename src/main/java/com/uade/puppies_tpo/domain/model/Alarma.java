package com.uade.puppies_tpo.domain.model;

import com.uade.puppies_tpo.domain.model.enums.EstadoAlarmaEnum;
import com.uade.puppies_tpo.domain.model.enums.PerioricidadAlarmaEnum;

public class Alarma {

    private Long id;
    private PerioricidadAlarmaEnum periodicidad;
    private EstadoAlarmaEnum estado;
    private IAccionVeterinaria accion;

    public Alarma(Long id, PerioricidadAlarmaEnum periodicidad, IAccionVeterinaria accion) {
        this.id = id;
        this.periodicidad = periodicidad;
        this.accion = accion;
        this.estado = EstadoAlarmaEnum.ACTIVA;
    }

    public void disparar() {
        if (this.estado == EstadoAlarmaEnum.INACTIVA) {
            throw new IllegalStateException("La alarma está inactiva y no puede ser disparada.");
        }
        this.estado = EstadoAlarmaEnum.DISPARADA;
        this.accion.ejecutar();
        System.out.println("Alarma disparada. Notificando a todos los veterinarios...");
    }

    public void agregarAccion(IAccionVeterinaria a) {
        if (this.accion instanceof GrupoAcciones) {
            ((GrupoAcciones) this.accion).agregar(a);
        } else {
            GrupoAcciones grupo = new GrupoAcciones();
            grupo.agregar(this.accion);
            grupo.agregar(a);
            this.accion = grupo;
        }
    }

    public Long getId() {
        return id;
    }

    public PerioricidadAlarmaEnum getPeriodicidad() {
        return periodicidad;
    }

    public EstadoAlarmaEnum getEstado() {
        return estado;
    }

    public IAccionVeterinaria getAccion() {
        return accion;
    }

    public void setEstado(EstadoAlarmaEnum estado) {
        this.estado = estado;
    }

    public void setPeriodicidad(PerioricidadAlarmaEnum periodicidad) {
        this.periodicidad = periodicidad;
    }
}
