package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.usuario.Veterinario;
import com.uade.puppies_tpo.repository.IVeterinarioRepository;

/**
 * El id del veterinario es la referencia externa al modulo de autenticacion
 * (String), por eso no se autogenera.
 */
public class VeterinarioRepositoryInMemory extends InMemoryRepository<Veterinario, String>
        implements IVeterinarioRepository {

    @Override
    protected String idDe(Veterinario entity) {
        return entity.obtenerId();
    }
}
