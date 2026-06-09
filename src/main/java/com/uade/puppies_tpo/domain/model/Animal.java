package com.uade.puppies_tpo.domain.model;

import com.uade.puppies_tpo.domain.model.animalStates.EstadoAnimal;

public class Animal {

    private Long id;
    private String nombre;
    private FichaTecnicaAnimal fichaTecnica;
    private EstadoAnimal estado;

    public Animal(Long id, String nombre, FichaTecnicaAnimal fichaTecnica, EstadoAnimal estadoInicial) {
        this.id = id;
        this.nombre = nombre;
        this.fichaTecnica = fichaTecnica;
        this.estado = estadoInicial;
    }

    public void setEstado(EstadoAnimal estado) {
        this.estado = estado;
    }

    public void adoptar(Object cliente) {
        estado.adoptar(this, cliente);
    }

    public boolean puedeSerAdoptado() {
        return estado.puedeSerAdoptado();
    }

    public void finalizarTratamiento() {
        estado.finalizarTratamiento(this);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public FichaTecnicaAnimal getFichaTecnica() {
        return fichaTecnica;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }
}