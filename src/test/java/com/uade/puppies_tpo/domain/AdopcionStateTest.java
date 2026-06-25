package com.uade.puppies_tpo.domain;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.domain.enums.EstadoDeSalud;
import com.uade.puppies_tpo.domain.enums.Ocupacion;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Reglas de adoptabilidad y ciclo de vida del animal (patron State).
 */
class AdopcionStateTest {

    private Animal animalDomestico(EstadoDeSalud salud) {
        FichaTecnicaAnimal ficha = new FichaTecnicaAnimal(TipoDeAnimal.DOMESTICO, 0.4, 8.0, 3, salud);
        return new Animal(1L, "Firulais", ficha);
    }

    private Animal animalSalvaje() {
        FichaTecnicaAnimal ficha = new FichaTecnicaAnimal(TipoDeAnimal.SALVAJE, 0.3, 5.0, 2, EstadoDeSalud.SANO);
        return new Animal(2L, "Zorro", ficha);
    }

    private Cliente cliente() {
        return new Cliente(1L, "Ana Gomez", "Soltera", "ana@mail.com", "1122334455",
                Ocupacion.EMPLEADO, false, "Compania");
    }

    @Test
    void domesticoDisponiblePuedeSerAdoptado() {
        assertTrue(animalDomestico(EstadoDeSalud.SANO).puedeSerAdoptado());
    }

    @Test
    void animalEnTratamientoNoPuedeSerAdoptado() {
        Animal animal = animalDomestico(EstadoDeSalud.ENFERMO);
        animal.iniciarTratamiento();

        assertTrue(animal.estaEnTratamiento());
        assertFalse(animal.puedeSerAdoptado());
        assertThrows(IllegalStateException.class, () -> animal.adoptar(cliente()));
    }

    @Test
    void salvajeNuncaPuedeSerAdoptado() {
        assertFalse(animalSalvaje().puedeSerAdoptado());
    }

    @Test
    void salvajeSiPuedeEstarEnTratamiento() {
        // Correccion clave: tipo y ciclo son ejes ortogonales. Un salvaje puede
        // recuperarse (en tratamiento) aunque nunca sea adoptable.
        Animal salvaje = animalSalvaje();

        assertDoesNotThrow(salvaje::iniciarTratamiento);
        assertTrue(salvaje.estaEnTratamiento());
        assertFalse(salvaje.puedeSerAdoptado());
    }

    @Test
    void finalizarTratamientoVuelveADisponibleYHabilitaAdopcion() {
        Animal animal = animalDomestico(EstadoDeSalud.EN_OBSERVACION);
        animal.iniciarTratamiento();
        animal.finalizarTratamiento();

        assertFalse(animal.estaEnTratamiento());
        assertTrue(animal.puedeSerAdoptado());
    }

    @Test
    void adoptarAnimalDisponibleLoDejaAdoptadoYNoReadoptable() {
        Animal animal = animalDomestico(EstadoDeSalud.SANO);
        Cliente cliente = cliente();

        animal.adoptar(cliente);

        assertFalse(animal.puedeSerAdoptado());
        assertTrue(cliente.getAnimalesAdoptados().contains(animal));
        assertThrows(IllegalStateException.class, () -> animal.adoptar(cliente));
    }
}
