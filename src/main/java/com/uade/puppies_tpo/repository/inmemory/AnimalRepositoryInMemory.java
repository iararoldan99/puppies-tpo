package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.repository.IAnimalRepository;

public class AnimalRepositoryInMemory extends InMemoryLongRepository<Animal>
        implements IAnimalRepository {

    @Override
    protected Long idDe(Animal entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(Animal entity, Long id) {
        entity.setId(id);
    }
}
