package com.uade.puppies_tpo.domain.model.animalStates;

import com.uade.puppies_tpo.domain.model.Animal;

public class EstadoAdoptado implements EstadoAnimal {

    @Override
    public void adoptar(Animal animal, Object cliente) {
        throw new IllegalStateException("El animal ya fue adoptado y no puede volver a ser adoptado.");
    }

    @Override
    public boolean puedeSerAdoptado() {
        return false;
    }

    @Override
    public void finalizarTratamiento(Animal animal) {
        throw new IllegalStateException("El animal ya fue adoptado y no tiene un tratamiento médico activo.");
    }
}