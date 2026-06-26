package com.uade.puppies_tpo.domain.alarma;

import com.uade.puppies_tpo.domain.accion.GrupoAcciones;
import com.uade.puppies_tpo.domain.accion.IAccionVeterinaria;
import com.uade.puppies_tpo.domain.enums.EstadoAlarmaEnum;
import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Alarma programada por un veterinario para el control periodico del animal.
 *
 * <ul>
 *   <li><b>Composite:</b> contiene un {@link GrupoAcciones} como punto de
 *       entrada, de modo que "el grupo de acciones" se dispara como una unidad.</li>
 *   <li><b>Observer (Subject):</b> mantiene la lista de veterinarios suscriptos
 *       y, al dispararse, notifica a todos.</li>
 * </ul>
 */
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

    /** Agrega una accion (o un grupo) al Composite que dispara esta alarma. */
    public void agregarAccion(IAccionVeterinaria accion) {
        grupoAcciones.agregar(accion);
    }

    /** Suscribe un observador (tipicamente un veterinario). */
    public void suscribir(ObservadorAlarma observador) {
        observadores.add(observador);
    }

    /**
     * Dispara la alarma: ejecuta el grupo de acciones, pasa a DISPARADA y
     * notifica a todos los veterinarios suscriptos.
     */
    public void disparar() {
        grupoAcciones.ejecutar();
        this.estado = EstadoAlarmaEnum.DISPARADA;
        observadores.forEach(observador -> observador.onAlarmaDisparada(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Id del animal al que pertenece la alarma (para registrar en su historial). */
    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public PeriodicidadAlarmaEnum getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(PeriodicidadAlarmaEnum periodicidad) {
        this.periodicidad = periodicidad;
    }

    public EstadoAlarmaEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoAlarmaEnum estado) {
        this.estado = estado;
    }

    public GrupoAcciones getGrupoAcciones() {
        return grupoAcciones;
    }

    public List<ObservadorAlarma> getObservadores() {
        return List.copyOf(observadores);
    }
}
