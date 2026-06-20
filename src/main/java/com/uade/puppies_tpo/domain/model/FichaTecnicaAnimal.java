package com.uade.puppies_tpo.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uade.puppies_tpo.domain.model.exportador.IExportador;

public class FichaTecnicaAnimal {

    private Double altura;
    private Double peso;
    private int edadAproximada;
    private boolean enTratamientoMedico;
    private List<RegistroAccion> historialClinico;
    private List<Alarma> alarmas;
    private IExportador exportador;

    public FichaTecnicaAnimal(String especie, Double altura, Double peso, int edadAproximada) {
        this.especie = especie;
        this.altura = altura;
        this.peso = peso;
        this.edadAproximada = edadAproximada;
        this.enTratamientoMedico = false;
        this.historialClinico = new ArrayList<>();
        this.alarmas = new ArrayList<>();  
    }

    public void agregarRegistro(RegistroAccion nuevoRegistro) {
        this.historialClinico.add(nuevoRegistro);
        nuevoRegistro.actualizarFicha(this); 
    }
    
    public void agregarAlarma(Alarma alarma) {
        this.alarmas.add(alarma);
    }   

    public void setEnTratamientoMedico(boolean enTratamientoMedico) {
        this.enTratamientoMedico = enTratamientoMedico;
    }

    public String obtenerCondicionMedica() {
        return enTratamientoMedico ? "Necesita atención médica" : "Buen estado de salud";
    }

    public List<RegistroAccion> getHistorialClinico() {
        return Collections.unmodifiableList(historialClinico);
    }

    public String getEspecie() {
        return especie;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getPeso() {
        return peso;
    }

    public int getEdadAproximada() {
        return edadAproximada;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setEdadAproximada(int edadAproximada) {
        this.edadAproximada = edadAproximada;
    }
    public void setExportador(IExportador exportador) {
        this.exportador = exportador;
    }

    public void exportar() {
        this.exportador.exportar(this);
    }
}