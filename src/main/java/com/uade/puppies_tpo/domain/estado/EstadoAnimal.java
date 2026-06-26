package com.uade.puppies_tpo.domain.estado;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;

/**
 * Patron State: encapsula el ciclo de vida del animal en cuanto a su
 * adoptabilidad. Cada estado concreto sabe responder si permite adopcion y como
 * reacciona a las transiciones (iniciar/finalizar tratamiento, adoptar).
 *
 * Importante (correccion de diseño): el State modela SOLO el ciclo
 * (Disponible / EnTratamiento / Adoptado). Que un animal sea salvaje o domestico
 * es un dato inmutable de la ficha ({@code TipoDeAnimal}), no un estado. Asi un
 * animal salvaje puede estar perfectamente "en tratamiento" sin colisionar con
 * este eje.
 */
public interface EstadoAnimal {

    /** Regla de adoptabilidad propia del estado (sin considerar el tipo). */
    boolean permiteAdopcion();

    void iniciarTratamiento(Animal animal);

    void finalizarTratamiento(Animal animal);

    void adoptar(Animal animal, Cliente cliente);
}
