package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.adopcion.FichaAdopcion;
import com.uade.puppies_tpo.repository.IFichaAdopcionRepository;

public class FichaAdopcionRepositoryInMemory extends InMemoryLongRepository<FichaAdopcion>
        implements IFichaAdopcionRepository {

    @Override
    protected Long idDe(FichaAdopcion entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(FichaAdopcion entity, Long id) {
        entity.setId(id);
    }
}
