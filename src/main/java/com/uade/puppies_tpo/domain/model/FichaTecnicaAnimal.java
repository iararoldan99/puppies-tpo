package com.uade.puppies_tpo.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FichaTecnicaAnimal {

    private String especie;
    private Double altura;
    private Double peso;
    private int edadAproximada;
    private List<RegistroAccion> historialClinico;

    public FichaTecnicaAnimal(String especie, Double altura, Double peso, int edadAproximada) {
        this.especie = especie;
        this.altura = altura;
        this.peso = peso;
        this.edadAproximada = edadAproximada;
        this.historialClinico = new ArrayList<>();
    }

    public void agregarRegistro(RegistroAccion registro) {
        this.historialClinico.add(registro);
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
}