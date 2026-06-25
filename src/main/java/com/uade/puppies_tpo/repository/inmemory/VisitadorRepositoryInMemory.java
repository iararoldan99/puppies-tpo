package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.usuario.Visitador;
import com.uade.puppies_tpo.repository.IVisitadorRepository;

/**
 * El id del visitador es la referencia externa al modulo de autenticacion
 * (String), por eso no se autogenera.
 */
public class VisitadorRepositoryInMemory extends InMemoryRepository<Visitador, String>
        implements IVisitadorRepository {

    @Override
    protected String idDe(Visitador entity) {
        return entity.obtenerId();
    }
}
