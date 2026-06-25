package com.uade.puppies_tpo.domain.enums;

/**
 * Clasificacion inmutable del animal segun su naturaleza.
 * Es un dato de la ficha, NO un estado del ciclo de vida: por eso se modela
 * como enum y no como una clase del patron State. Determina la adoptabilidad
 * junto con el estado actual (un salvaje nunca es adoptable; un domestico lo
 * es solo si su estado lo permite).
 */
public enum TipoDeAnimal {
    DOMESTICO,
    SALVAJE
}
