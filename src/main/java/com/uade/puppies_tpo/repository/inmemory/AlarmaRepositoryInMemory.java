package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.repository.IAlarmaRepository;

public class AlarmaRepositoryInMemory extends InMemoryLongRepository<Alarma>
        implements IAlarmaRepository {

    @Override
    protected Long idDe(Alarma entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(Alarma entity, Long id) {
        entity.setId(id);
    }
}
