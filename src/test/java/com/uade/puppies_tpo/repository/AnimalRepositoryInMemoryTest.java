package com.uade.puppies_tpo.repository;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;
import com.uade.puppies_tpo.domain.enums.EstadoDeSalud;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import com.uade.puppies_tpo.repository.inmemory.AnimalRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Patron Repository: el repositorio in-memory cumple el contrato CRUD definido
 * por la interfaz (DIP: el test depende de IAnimalRepository, no de la impl).
 */
class AnimalRepositoryInMemoryTest {

    private final IAnimalRepository repo = new AnimalRepositoryInMemory();

    private Animal nuevoAnimal() {
        FichaTecnicaAnimal ficha = new FichaTecnicaAnimal("Perro", TipoDeAnimal.DOMESTICO, 0.4, 8.0, 3, EstadoDeSalud.SANO);
        return new Animal(null, "Firulais", ficha);
    }

    @Test
    void saveAsignaIdYPermiteRecuperar() {
        Animal guardado = repo.save(nuevoAnimal());

        assertNotNull(guardado.getId());
        assertTrue(repo.findById(guardado.getId()).isPresent());
        assertEquals("Firulais", repo.findById(guardado.getId()).orElseThrow().getNombre());
    }

    @Test
    void findAllDevuelveLoGuardadoYDeleteByIdLoQuita() {
        Animal a1 = repo.save(nuevoAnimal());
        Animal a2 = repo.save(nuevoAnimal());
        assertEquals(2, repo.findAll().size());

        repo.deleteById(a1.getId());

        assertEquals(1, repo.findAll().size());
        assertTrue(repo.findById(a1.getId()).isEmpty());
        assertTrue(repo.findById(a2.getId()).isPresent());
    }
}
