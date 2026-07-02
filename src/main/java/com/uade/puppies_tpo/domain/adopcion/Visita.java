package com.uade.puppies_tpo.domain.adopcion;

import com.uade.puppies_tpo.domain.cliente.Cliente;

import java.time.LocalDate;

public class Visita {

    private Long id;
    private Cliente cliente;
    private LocalDate fecha;
    private String rangoHorario;
    private boolean continuar;
    private Encuesta encuesta;

    public Visita(Cliente cliente, LocalDate fecha, String rangoHorario) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.rangoHorario = rangoHorario;
    }

    public void finalizar() {
        if (encuesta != null) {
            this.continuar = encuesta.debeMantenerVisitas();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getFecha() { return fecha; }
    public String getRangoHorario() { return rangoHorario; }
    public boolean isContinuar() { return continuar; }
    public Encuesta getEncuesta() { return encuesta; }
    public void setEncuesta(Encuesta encuesta) { this.encuesta = encuesta; }
}
