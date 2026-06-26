package com.uade.puppies_tpo.domain.estado;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;

/**
 * El animal esta disponible: su ciclo permite la adopcion (si ademas el tipo lo
 * permite) y puede entrar en tratamiento.
 */
public class EstadoDisponible implements EstadoAnimal {

    @Override
    public boolean permiteAdopcion() {
        return true;
    }

    @Override
    public void iniciarTratamiento(Animal animal) {
        animal.cambiarEstado(new EstadoEnTratamiento());
    }

    @Override
    public void finalizarTratamiento(Animal animal) {
        throw new IllegalStateException("El animal no se encuentra en tratamiento.");
    }

    @Override
    public void adoptar(Animal animal, Cliente cliente) {
        if (!cliente.puedeAdoptar()) {
            throw new IllegalStateException("El cliente alcanzo el maximo de animales adoptados.");
        }
        cliente.agregarAnimalAdoptado(animal);
        animal.cambiarEstado(new EstadoAdoptado());
    }
}
