package com.uade.puppies_tpo.domain.model.state;

import com.uade.puppies_tpo.domain.model.Animal;

public class EstadoEnTratamiento implements EstadoAnimal {

    @Override
    public void adoptar(Animal animal, Object cliente) {
        throw new IllegalStateException("El animal no puede ser adoptado porque se encuentra bajo tratamiento médico.");
    }

    @Override
    public boolean puedeSerAdoptado() {
        return false;
    }

    @Override
    public void finalizarTratamiento(Animal animal) {
        animal.setEstado(new EstadoAdoptable());
        System.out.println("Tratamiento finalizado. El animal " + animal.getNombre() + " ahora está disponible para adopción.");
    }
}