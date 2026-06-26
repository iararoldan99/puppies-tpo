package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.repository.IVisitaRepository;

public class VisitaRepositoryInMemory extends InMemoryLongRepository<Visita>
        implements IVisitaRepository {

    @Override
    protected Long idDe(Visita entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(Visita entity, Long id) {
        entity.setId(id);
    }
}
