package com.uade.puppies_tpo.domain.model.state;

import com.uade.puppies_tpo.domain.model.Animal;

public class EstadoAdoptable implements EstadoAnimal {

    @Override
    public void adoptar(Animal animal, Object cliente) {
        animal.setEstado(new EstadoAdoptado());
        System.out.println("El animal " + animal.getNombre() + " ha sido adoptado exitosamente por: " + cliente.toString());
    }

    @Override
    public boolean puedeSerAdoptado() {
        return true;
    }

    @Override
    public void finalizarTratamiento(Animal animal) {
        throw new IllegalStateException("El animal no tiene un tratamiento médico activo.");
    }
}