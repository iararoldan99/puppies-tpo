package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.repository.ISeguimientoAdopcionRepository;

public class SeguimientoRepositoryInMemory extends InMemoryLongRepository<SeguimientoAdopcion>
        implements ISeguimientoAdopcionRepository {

    @Override
    protected Long idDe(SeguimientoAdopcion entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(SeguimientoAdopcion entity, Long id) {
        entity.setId(id);
    }
}
