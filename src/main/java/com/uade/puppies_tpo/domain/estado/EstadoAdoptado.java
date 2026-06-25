package com.uade.puppies_tpo.domain.estado;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;

/**
 * El animal ya fue adoptado: estado terminal del ciclo de adoptabilidad.
 */
public class EstadoAdoptado implements EstadoAnimal {

    @Override
    public boolean permiteAdopcion() {
        return false;
    }

    @Override
    public void iniciarTratamiento(Animal animal) {
        throw new IllegalStateException("El animal ya fue adoptado.");
    }

    @Override
    public void finalizarTratamiento(Animal animal) {
        throw new IllegalStateException("El animal ya fue adoptado.");
    }

    @Override
    public void adoptar(Animal animal, Cliente cliente) {
        throw new IllegalStateException("El animal ya fue adoptado.");
    }
}
