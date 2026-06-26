package com.uade.puppies_tpo.domain.estado;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;

/**
 * El animal esta bajo tratamiento medico: no puede adoptarse hasta finalizarlo.
 * Aplica tanto a domesticos como a salvajes en recuperacion.
 */
public class EstadoEnTratamiento implements EstadoAnimal {

    @Override
    public boolean permiteAdopcion() {
        return false;
    }

    @Override
    public void iniciarTratamiento(Animal animal) {
        throw new IllegalStateException("El animal ya se encuentra en tratamiento.");
    }

    @Override
    public void finalizarTratamiento(Animal animal) {
        animal.cambiarEstado(new EstadoDisponible());
    }

    @Override
    public void adoptar(Animal animal, Cliente cliente) {
        throw new IllegalStateException("No se puede adoptar un animal en tratamiento.");
    }
}
