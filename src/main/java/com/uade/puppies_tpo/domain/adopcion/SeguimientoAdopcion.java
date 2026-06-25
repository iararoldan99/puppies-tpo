package com.uade.puppies_tpo.domain.adopcion;

import com.uade.puppies_tpo.domain.recordatorio.IRecordatorio;
import com.uade.puppies_tpo.domain.usuario.Visitador;

import java.util.ArrayList;
import java.util.List;

/**
 * Seguimiento posterior a una adopcion. Coordina las visitas, conoce al
 * visitador responsable y delega el envio de recordatorios en el canal preferido
 * por el cliente (Strategy).
 */
public class SeguimientoAdopcion {

    private Long id;
    private Visitador visitadorAsociado;
    private final List<Visita> visitas = new ArrayList<>();
    private String cadencia;
    private IRecordatorio preferencia;
    private int diasAnticipacion;

    public SeguimientoAdopcion(Visitador visitadorAsociado, String cadencia,
                               IRecordatorio preferencia, int diasAnticipacion) {
        this.visitadorAsociado = visitadorAsociado;
        this.cadencia = cadencia;
        this.preferencia = preferencia;
        this.diasAnticipacion = diasAnticipacion;
    }

    public void agregarVisita(Visita visita) {
        visitas.add(visita);
    }

    /** Envia un recordatorio por el canal preferido del cliente. */
    public void enviarRecordatorio(String mensaje) {
        if (preferencia == null) {
            throw new IllegalStateException("No se configuro una preferencia de recordatorio.");
        }
        preferencia.recordar(mensaje);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visitador getVisitadorAsociado() {
        return visitadorAsociado;
    }

    public List<Visita> getVisitas() {
        return List.copyOf(visitas);
    }

    public String getCadencia() {
        return cadencia;
    }

    public IRecordatorio getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(IRecordatorio preferencia) {
        this.preferencia = preferencia;
    }

    public int getDiasAnticipacion() {
        return diasAnticipacion;
    }
}
