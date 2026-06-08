package com.uade.puppies_tpo.domain.model.state;

import com.uade.puppies_tpo.domain.model.Animal;

public interface EstadoAnimal {

    void adoptar(Animal animal, Object cliente);

    boolean puedeSerAdoptado();

    void finalizarTratamiento(Animal animal);
}