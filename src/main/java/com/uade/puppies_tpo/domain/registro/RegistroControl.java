package com.uade.puppies_tpo.domain.registro;

import com.uade.puppies_tpo.domain.accion.IAccionVeterinaria;
import com.uade.puppies_tpo.domain.usuario.Veterinario;

import java.time.LocalDate;

/**
 * Registro de un control de rutina: incluye mediciones (peso, tamanio) y la
 * fecha del proximo control.
 */
public class RegistroControl extends RegistroAccion {

    private double pesoRegistrado;
    private double tamanioRegistrado;
    private LocalDate proximoControl;

    public RegistroControl(IAccionVeterinaria accion, String comentario,
                           Veterinario veterinario, LocalDate fecha) {
        super(accion, comentario, veterinario, fecha);
    }

    /** Hubo perdida de peso respecto del peso anterior conocido. */
    public boolean huboPerdidaDePeso(double pesoAnterior) {
        return pesoRegistrado < pesoAnterior;
    }

    /** Hay un proximo control agendado que todavia no llego. */
    public boolean tieneProximoControlPendiente() {
        return proximoControl != null && !proximoControl.isBefore(LocalDate.now());
    }

    public double getPesoRegistrado() {
        return pesoRegistrado;
    }

    public void setPesoRegistrado(double pesoRegistrado) {
        this.pesoRegistrado = pesoRegistrado;
    }

    public double getTamanioRegistrado() {
        return tamanioRegistrado;
    }

    public void setTamanioRegistrado(double tamanioRegistrado) {
        this.tamanioRegistrado = tamanioRegistrado;
    }

    public LocalDate getProximoControl() {
        return proximoControl;
    }

    public void setProximoControl(LocalDate proximoControl) {
        this.proximoControl = proximoControl;
    }
}
